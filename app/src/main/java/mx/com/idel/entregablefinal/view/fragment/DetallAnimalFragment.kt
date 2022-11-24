package mx.com.idel.entregablefinal.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import mx.com.idel.entregablefinal.KEY_ANIMAL_A_EDITAR
import mx.com.idel.entregablefinal.R
import mx.com.idel.entregablefinal.databinding.FragmentDetallAnimalBinding
import mx.com.idel.entregablefinal.model.entities.AnimalModel
import mx.com.idel.entregablefinal.view.activity.EditarAnimalActivity
import mx.com.idel.entregablefinal.viewmodel.ListViewModel


class DetallAnimalFragment : Fragment() {

    private var _binding: FragmentDetallAnimalBinding? = null
    private val binding get() = _binding!!

    private var animal : AnimalModel? = null
    private val args : DetallAnimalFragmentArgs by navArgs()

    private val listaViewModel : ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetallAnimalBinding.inflate(inflater, container, false)
        val view = binding.root
        animal = args.animal
        binding.NombreAnimal.text = animal?.nombre
        Glide.with(binding.root)
            .load(animal?.imagen)
            .placeholder(R.drawable.icono)
            .centerCrop()
            .circleCrop()
            .into(binding.imagenAnimal)
        binding.duenoAnimal.text = HtmlCompat.fromHtml("<b>${getText(R.string.dueno)}</b>: ${animal?.dueno}",HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.pesoAnimal.text = HtmlCompat.fromHtml("<b>${getText(R.string.peso)}</b>: ${animal?.peso}kg",HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.enfermoAnimal.isEnabled = false
        binding.enfermoAnimal.isChecked = animal?.enfermo == true
        for (i in 0 until binding.generoAnimal.childCount) {
            binding.generoAnimal.getChildAt(i).isEnabled = false
        }
        when(animal?.genero == true){
            true -> binding.generoAnimal.check(R.id.HAnimal)
            false -> binding.generoAnimal.check(R.id.MAnimal)
        }
        binding.descripcionAnimal.text = HtmlCompat.fromHtml("<b>${getText(R.string.descripcion)}</b>: ${animal?.descripcion}",HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.btneditar.setOnClickListener {
            val intentEditar = Intent(context,EditarAnimalActivity::class.java).apply {
                putExtra(KEY_ANIMAL_A_EDITAR, animal)
            }
            startActivity(intentEditar)
            findNavController().navigateUp()
        }
        binding.btnborrar.setOnClickListener {
            animal?.let {
                listaViewModel.dellAnimal(it.id)
                listaViewModel.statusdell.observe(viewLifecycleOwner){
                    if (it > -1){
                        if(it != 0){
                            Toast.makeText(context,"SE ELIMINO: ${animal?.nombre}",Toast.LENGTH_SHORT).show()
                            findNavController().navigateUp()
                        }else{
                            Toast.makeText(context,"NO EXISTE REGISTRO",Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(context,"NO ELIMINADO",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        return view
    }

}