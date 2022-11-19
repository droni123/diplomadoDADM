package mx.com.idel.diplomado.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.com.idel.diplomado.databinding.UserItemBinding
import mx.com.idel.diplomado.model.entities.UserModel

class UserAdapter(private val items : ArrayList<UserModel>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var onItemClick: ((UserModel) -> Unit)? = null

    class UserViewHolder(val brinding : UserItemBinding) : RecyclerView.ViewHolder(brinding.root) {
        fun bind(user : UserModel,onItemClick: ((UserModel) -> Unit)?) {
            brinding.nombre.text = "Nombre: ${user.nombre}"
            brinding.apellido.text = "Apellido: ${user.apellido}"
            brinding.edad.text = "Edad: ${user.edad}"
            Glide
                .with(brinding.root)
                .load(user.image)
                .centerCrop()
                .into(brinding.imagen)

            brinding.userCard.setOnClickListener{
                onItemClick?.invoke(user)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val brinding = UserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(brinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(items[position],onItemClick)
    }

    override fun getItemCount() = items.size

    fun updateDataItems(newItems:ArrayList<UserModel>){
        items.clear()
        items.addAll(newItems)
    }

}