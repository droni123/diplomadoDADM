package mx.com.idel.my.diplomado.entregables

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.com.idel.my.diplomado.R

class Entregable3AnimalAdapter(private val animales : ArrayList<Entregable3AnimalModel>,private val listener : Entregable3AnimalListener) : RecyclerView.Adapter<Entregable3AnimalAdapter.AnimalViewHolder>() {
    class AnimalViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        var nombre : TextView
        var imagen : ImageView
        init {
            nombre = vista.findViewById(R.id.e3NombreAnimal)
            imagen = vista.findViewById(R.id.e3iconoAnimal)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_animal,parent,false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.nombre.text = animales[position].nombre
        val urlImagen = animales[position].imagen
        Glide.with(holder.itemView.context)
            .load(urlImagen)
            .centerCrop()
            .into(holder.imagen)
        holder.itemView.setOnClickListener {
            listener.onItemSelected(animales[position])
        }
    }
    override fun getItemCount() = animales.size

}