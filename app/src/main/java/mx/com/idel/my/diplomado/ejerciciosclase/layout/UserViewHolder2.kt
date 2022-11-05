package mx.com.idel.my.diplomado.ejerciciosclase.layout

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.com.idel.my.diplomado.R

class UserViewHolder2(view: View) : RecyclerView.ViewHolder(view) {
    var nombre : TextView
    var imagen : ImageView
    init {
        nombre = view.findViewById(R.id.c3textrecycle)
        imagen = view.findViewById(R.id.c3imgrecycle)
    }
}