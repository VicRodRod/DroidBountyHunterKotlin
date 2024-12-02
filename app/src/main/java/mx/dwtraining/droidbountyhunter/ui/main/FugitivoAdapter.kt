package mx.dwtraining.droidbountyhunter.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import mx.dwtraining.droidbountyhunter.R
import mx.dwtraining.droidbountyhunter.models.Fugitivo

class FugitivoAdapter(context: Context, private val fugitivos: Array<Fugitivo>) : ArrayAdapter<Fugitivo>(context, R.layout.item_fugitivo_list, fugitivos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_fugitivo_list, parent, false)

        val fugitive = fugitivos[position] // Se obtiene la pocision

        /** Foto **/
        val imgFugitivo = view.findViewById<ImageView>(R.id.img_fugitivo)
        if (!fugitive.photo.isNullOrEmpty()){
            Glide.with(context)
                .load(fugitive.photo)
                .into(imgFugitivo)
        }
        /** Nombre **/
        val txtName = view.findViewById<TextView>(R.id.txt_name)
        txtName.text = fugitive.name
        /** Fecha **/
        val txtDate = view.findViewById<TextView>(R.id.txt_date)
        txtDate.text = fugitive.capture_date
        return view
    }
}