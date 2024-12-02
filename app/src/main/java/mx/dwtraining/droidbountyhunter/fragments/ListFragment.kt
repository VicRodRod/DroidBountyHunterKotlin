package mx.dwtraining.droidbountyhunter.fragments

import ApiClient
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mx.dwtraining.droidbountyhunter.data.DatabaseBountyHunter
import mx.dwtraining.droidbountyhunter.databinding.FragmentListBinding
import mx.dwtraining.droidbountyhunter.models.Fugitivo
import mx.dwtraining.droidbountyhunter.network.NetworkHelper.ERR_NAME_NOT_RESOLVED
import mx.dwtraining.droidbountyhunter.network.NetworkHelper.manageError
import mx.dwtraining.droidbountyhunter.ui.main.FugitivoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


const val SECTION_NUMBER : String = "section_number"



class ListFragment(private val listener: FugitivoListener) : Fragment()  {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
// Se hace referencia al Fragment generado por XML en los Layouts y
// se instancia en una View...
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val modo = requireArguments().getInt(SECTION_NUMBER)
        actualizarDatos(modo)
        binding.listaFugitivosCapturados.setOnItemClickListener { _, view, position, id ->
            val fugitivoList = binding.listaFugitivosCapturados.tag as Array<Fugitivo>
            listener.fugitivoSeleccionado(fugitivoList[position])
        }
    }


    /*private fun actualizarDatos(modo: Int) {
        val database = DatabaseBountyHunter(requireContext())
        val fugitivos = database.obtenerFugitivos(modo)
        if (fugitivos.isNotEmpty()){
            val values = ArrayList<String?>()
            fugitivos.mapTo(values){ it.name }
            val adaptador = ArrayAdapter<String>(requireContext(), R.layout.item_fugitivo_list,
                values)
            binding.listaFugitivosCapturados.adapter = adaptador
            binding.listaFugitivosCapturados.tag = fugitivos
        }
    }*/

    private fun actualizarDatos(modo: Int) {
        val database = DatabaseBountyHunter(requireContext())
        val fugitivos = database.obtenerFugitivos(modo)
        if (fugitivos.isNotEmpty()){ // La base de datos no se encuentra vacia
            val adaptador = FugitivoAdapter(requireContext(), fugitivos)
            binding.listaFugitivosCapturados.adapter = adaptador
            binding.listaFugitivosCapturados.tag = fugitivos
        } else { // La base de datos se encuentra vacía
            if (modo == 0) {
                showProgressBar()
                val call = ApiClient.apiService.getFugitivos()
                call.enqueue(object : Callback<List<Fugitivo>> {
                    override fun onResponse(call: Call<List<Fugitivo>>, response:
                    Response<List<Fugitivo>>
                    ) {
                        hideProgressBar()
                        if (response.isSuccessful) {
                            val fugitivoList = response.body()
                            fugitivoList?.toList()?.forEach {
                                database.insertarFugitivo(it)
                            }
                            actualizarDatos(modo)
                        } else {// Handle error
                            manageError(requireContext(), response.code(), response.message())
                        }
                    }
                    override fun onFailure(call: Call<List<Fugitivo>>, t: Throwable) {
                        // Handle failure
                        t.printStackTrace()
                        hideProgressBar()
                        manageError(requireContext(), ERR_NAME_NOT_RESOLVED,
                            t.message.toString()

                        )
                        Log.d("Mitag", t.message.toString());
                    }
                })
            }
        }
    }

    private fun showProgressBar() {
        binding.progressIndicator.visibility = View.VISIBLE
    }
    private fun hideProgressBar() {
        binding.progressIndicator.visibility = View.GONE
    }



}

interface FugitivoListener {
    fun fugitivoSeleccionado(fugitivo: Fugitivo)
}