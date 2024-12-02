package mx.dwtraining.droidbountyhunter

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import mx.dwtraining.droidbountyhunter.data.DatabaseBountyHunter
import mx.dwtraining.droidbountyhunter.databinding.ActivityAgregarBinding
import mx.dwtraining.droidbountyhunter.models.Fugitivo

class AgregarActivity : AppCompatActivity(){
    private lateinit var binding: ActivityAgregarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonGuardar.setOnClickListener{
            guardarFugitivoPresionado()
        }
    }

    private fun guardarFugitivoPresionado(){
        val nombre = binding.nombreFugitivoTextView.text.toString()
        if (nombre.isNotEmpty()){
            val database = DatabaseBountyHunter(this)
            database.insertarFugitivo(Fugitivo(0, nombre, 0))
            setResult(0)
            finish()
        }else{
            AlertDialog.Builder(this)
                .setTitle("Alerta")
                .setMessage("Favor de capturar el nombre del fugitivo.")
                .show()
        }
    }

}
