package mx.com.idel.my.diplomado.ejerciciosclase.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.com.idel.my.diplomado.R
import mx.com.idel.my.diplomado.ejerciciosclase.layout.RecyclerItemListener
import mx.com.idel.my.diplomado.ejerciciosclase.layout.UserItem
import mx.com.idel.my.diplomado.ejerciciosclase.layout.UserViewHolder2
import mx.com.idel.my.diplomado.entregables.Entregable2PetAdapter

class UsuarioAdapter(private val items : ArrayList<userSqlModel>) : RecyclerView.Adapter<UsuarioAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombre : TextView
        var descripcion : TextView
        init {
            nombre = view.findViewById(R.id.c3textrecycle)
            descripcion = view.findViewById(R.id.c3textdescripcion)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_user,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.nombre.text = items[position].name
        holder.descripcion.text = items[position].descripcion
    }

    override fun getItemCount() = items.size

    fun update(news: ArrayList<userSqlModel>){
        items.clear()
        items.addAll(news)
        notifyDataSetChanged()
    }
}