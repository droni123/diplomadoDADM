package mx.com.idel.my.diplomado.entregables

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.com.idel.my.diplomado.R

class Entregable2PetAdapter(private val pets : ArrayList<Entregable2PetItem>,private val listener: Entreganle2petListener) : RecyclerView.Adapter<Entregable2PetAdapter.PetViewHolder>() {

    class PetViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        var nombre : TextView
        var imagen : ImageView
        init {
            nombre = vista.findViewById(R.id.e2NombrePet)
            imagen = vista.findViewById(R.id.e2iconoPet)
        }
    }
    //crear layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_pet,parent,false)
        return PetViewHolder(view)
    }
    //funcion que resicla / envia datos
    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.nombre.text = pets[position].nombre
        holder.imagen.setImageResource(getImageId(holder.itemView.context,pets[position].imagen))
        holder.itemView.setOnClickListener {
            listener.onItemSelected(pets[position])
        }
    }
    //Retorna datos a mostrar
    override fun getItemCount() = pets.size

    fun getImageId(context: Context, imageName: String): Int {
        return context.resources.getIdentifier( imageName , "drawable", context.packageName )
    }

}