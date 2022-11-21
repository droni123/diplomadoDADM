package mx.com.idel.entregablefinal.view.activity

import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import android.widget.Toast
import androidx.activity.viewModels
import mx.com.idel.entregablefinal.KEY_ANIMAL_A_EDITAR
import mx.com.idel.entregablefinal.R
import mx.com.idel.entregablefinal.databinding.ActivityEditarAnimalBinding
import mx.com.idel.entregablefinal.model.entities.AnimalModel
import mx.com.idel.entregablefinal.viewmodel.ListViewModel

class EditarAnimalActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditarAnimalBinding

    lateinit var animal : AnimalModel
    private lateinit var anima: AnimatedVectorDrawable
    private val listaViewModel : ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.icono.setImageResource(R.drawable.load)
        anima = binding.icono.drawable as AnimatedVectorDrawable

        intent.extras?.let { bundle ->
            if (bundle.containsKey(KEY_ANIMAL_A_EDITAR)) {
                animal = bundle.getSerializable(KEY_ANIMAL_A_EDITAR) as AnimalModel
            }else{
                animal = AnimalModel()
            }
        }?: run {
            animal = AnimalModel()
        }
        if(animal.id != 0){
            binding.textoAgregar.text = "${getText(R.string.editar)}"
            binding.inputnombre.setText(animal.nombre)
            binding.inputimagen.setText(animal.imagen)
            binding.inputdueno.setText(animal.dueno)
            binding.inputpeso.setText(animal.peso.toString())
            when(animal.genero){
                true -> binding.GeneroAnimal.check(R.id.HAnimal)
                false -> binding.GeneroAnimal.check(R.id.MAnimal)
            }
            binding.enfermoAnimal.isChecked = animal.enfermo
            binding.inputdescripcion.setText(animal.descripcion)
        }

        binding.guardar.setOnClickListener {
            //GET DATA
            val txtNombre = binding.inputnombre.text.toString()
            val txtImagen = binding.inputimagen.text.toString()
            val txtDueno = binding.inputdueno.text.toString()
            val txtPeso = binding.inputpeso.text.toString()
            val txtDescripcion = binding.inputdescripcion.text.toString()
            val valGenero = binding.GeneroAnimal.checkedRadioButtonId
            val valEnfermo = binding.enfermoAnimal.isChecked

            var datGenero = true
            //VALIDACION DATA
            var mensaje = ""
            if(txtNombre.isEmpty()){
                mensaje += " ${getString(R.string.nombre)}"
            }
            if(txtImagen.isEmpty()){
                mensaje += " ${getString(R.string.imagen)}"
            }else{
                if(!URLUtil.isValidUrl(txtImagen)){
                    mensaje += " ${getString(R.string.error_url, getString(R.string.imagen))}"
                }
            }
            if(txtPeso.isEmpty()){
                mensaje += " ${getString(R.string.peso)}"
            }
            if(txtDueno.isEmpty()){
                mensaje += " ${getString(R.string.dueno)}"
            }
            if(txtDescripcion.isEmpty()){
                mensaje += " ${getString(R.string.descripcion)}"
            }
            if(valGenero < 0){
                mensaje += " ${getString(R.string.genero)}"
            }else{
                datGenero = when(valGenero){
                    R.id.HAnimal -> true
                    else -> false
                }
            }
            ///SI PASA LA VALIDACIÓN
            if(mensaje === ""){
                HiddeoOrShowItems(false)
                val animalToSave = AnimalModel(animal.id, txtNombre, txtImagen, txtDueno, txtPeso.toDouble(), datGenero, valEnfermo, txtDescripcion)
                listaViewModel.saveAnimal(this@EditarAnimalActivity,animalToSave)
                listaViewModel.statussave.observe(this){
                    if (it > -1){
                        Toast.makeText(this@EditarAnimalActivity,"${animal.nombre} GUARDADO",Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(this@EditarAnimalActivity,"ANIMAL NO GUARDADO",Toast.LENGTH_SHORT).show()
                        HiddeoOrShowItems(true)
                    }
                }
            }else{ // SI NO PASA LA VALIDACION
                mensaje = getString(R.string.error,mensaje)
                Toast.makeText(this@EditarAnimalActivity, mensaje, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun HiddeoOrShowItems(show:Boolean = true){
        if(show) {
            binding.textoAgregar.visibility = View.VISIBLE
            binding.nombre.visibility = View.VISIBLE
            binding.imagen.visibility = View.VISIBLE
            binding.dueno.visibility = View.VISIBLE
            binding.peso.visibility = View.VISIBLE
            binding.GeneroAnimal.visibility = View.VISIBLE
            binding.enfermoAnimal.visibility = View.VISIBLE
            binding.descripcion.visibility = View.VISIBLE
            binding.icono.visibility = View.INVISIBLE
            binding.guardar.visibility = View.VISIBLE
            anima.stop()
        }else{
            binding.textoAgregar.visibility = View.INVISIBLE
            binding.nombre.visibility = View.INVISIBLE
            binding.imagen.visibility = View.INVISIBLE
            binding.dueno.visibility = View.INVISIBLE
            binding.peso.visibility = View.INVISIBLE
            binding.GeneroAnimal.visibility = View.INVISIBLE
            binding.enfermoAnimal.visibility = View.INVISIBLE
            binding.descripcion.visibility = View.INVISIBLE
            binding.icono.visibility = View.VISIBLE
            binding.guardar.visibility = View.INVISIBLE
            anima.start()

        }
    }
}