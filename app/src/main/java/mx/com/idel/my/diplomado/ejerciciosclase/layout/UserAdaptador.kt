package mx.com.idel.my.diplomado.ejerciciosclase.layout

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.com.idel.my.diplomado.R

class UserAdaptador(private val items : ArrayList<UserItem>,private val listener: RecyclerItemListener) : RecyclerView.Adapter<UserViewHolder2>(){
    /*class UserViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        var nombre : TextView
        var imagen : ImageView
        init {
            nombre = view.findViewById(R.id.c3textrecycle)
            imagen = view.findViewById(R.id.c3imgrecycle)
        }
    }*/
    //crear layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder2 {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_user,parent,false)
        return UserViewHolder2(view)
    }
    //funcion que resicla / envia datos
    override fun onBindViewHolder(holder: UserViewHolder2, position: Int) {
        holder.nombre.text = items[position].nombre
        holder.itemView.setOnClickListener {
            listener.onItemSelected(items[position])
        }
        //holder.imagen = items[position].image
    }
    //Retorna datos a mostrar
    override fun getItemCount() = items.size

}