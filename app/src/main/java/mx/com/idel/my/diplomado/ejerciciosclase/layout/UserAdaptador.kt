package mx.com.idel.my.diplomado.ejerciciosclase.layout

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.com.idel.my.diplomado.R

class UserAdaptador(private val items : ArrayList<UserItem>) : RecyclerView.Adapter<UserAdaptador.UserViewHolder>(){
    class UserViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        var nombre : TextView
        var imagen : ImageView
        init {
            nombre = view.findViewById(R.id.c3textrecycle)
            imagen = view.findViewById(R.id.c3imgrecycle)
        }
    }
    //crear layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_user,parent,false)
        return UserViewHolder(view)
    }
    //funcion que resicla
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.nombre.text = items[position].nombre
        //holder.imagen = items[position].image
    }
    //Retorna datos a mostrar
    override fun getItemCount() = items.size

}