package mx.com.idel.entregablefinal.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.com.idel.entregablefinal.databinding.RecyclerviewItemAnimalBinding
import mx.com.idel.entregablefinal.model.entities.AnimalModel


class AnimalAdapter(private var items : ArrayList<AnimalModel>) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    var onItemClick: ((AnimalModel) -> Unit)? = null

    class AnimalViewHolder(val brinding : RecyclerviewItemAnimalBinding) : RecyclerView.ViewHolder(brinding.root) {
        fun  bind(animal : AnimalModel,onItemClick: ((AnimalModel) -> Unit)?){
            //brinding.NombreAnimal.text = animal.nombre
            brinding.animal = animal
            Glide
                .with(brinding.root)
                .load(animal.imagen)
                .centerCrop()
                .into(brinding.imagenAnimal)
            brinding.cardview.setOnClickListener{
                onItemClick?.invoke(animal)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val brinding = RecyclerviewItemAnimalBinding.inflate( LayoutInflater.from(parent.context),parent,false )
        return AnimalViewHolder(brinding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.bind(items[position],onItemClick)
    }

    override fun getItemCount() = items.size

    fun updateDataItems(newList : ArrayList<AnimalModel>){
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}