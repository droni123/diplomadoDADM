package mx.com.idel.my.diplomado.entregables

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import mx.com.idel.my.diplomado.R
import mx.com.idel.my.diplomado.ejerciciosclase.database.SqlHelper

class Entregable3DetalleAnimalFragment : Fragment() {
    lateinit var animal : Entregable3AnimalModel
    private lateinit var sqlHelper: Entregable3AnimalSqlHelper
    companion object{
        fun newinstance(animal : Entregable3AnimalModel) : Entregable3DetalleAnimalFragment{
            return  Entregable3DetalleAnimalFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("KEY_ANIMAL_DETALLE",animal)
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sqlHelper = Entregable3AnimalSqlHelper(requireContext())
        arguments?.let{
            animal = it.getSerializable("KEY_ANIMAL_DETALLE") as Entregable3AnimalModel
        }?: run {
            animal = Entregable3AnimalModel()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_entregable3_detalle_animal, container, false)
        val aNombre = vista.findViewById<TextView>(R.id.e3eNombreAnimal)
        val img = vista.findViewById<ImageView>(R.id.e3RiconoAnimal)
        val aDueno = vista.findViewById<TextView>(R.id.e3RDuenoAnimal)
        val aEnfermo = vista.findViewById<CheckBox>(R.id.e3REnfermoAnimal)
        val aGenero = vista.findViewById<RadioGroup>(R.id.e3RGeneroAnimal)
        val aDescripcion = vista.findViewById<TextView>(R.id.e3RDescripcionAnimal)
        aNombre.text = animal.nombre
        Glide.with(vista.context)
            .load(animal.imagen)
            .centerCrop()
            .into(img)
        aDueno.text = HtmlCompat.fromHtml("<b>${getText(R.string.e2dueno)}</b>: ${animal.dueno}",HtmlCompat.FROM_HTML_MODE_LEGACY)
        aEnfermo.isEnabled = false
        aEnfermo.isChecked = animal.enfermo
        for (i in 0 until aGenero.childCount) {
            (aGenero.getChildAt(i) as RadioButton).isEnabled = false
        }
        when(animal.genero){
            true -> aGenero.check(R.id.e3REAnimal)
            false -> aGenero.check(R.id.e3RMAnimal)
        }
        aDescripcion.text = HtmlCompat.fromHtml("<b>${getText(R.string.e2descripcion)}</b>: ${animal.descripcion}",HtmlCompat.FROM_HTML_MODE_LEGACY)
        val btn_edit = vista.findViewById<Button>(R.id.e3btneditar)
        val btn_dell = vista.findViewById<Button>(R.id.e3btnborrar)
        btn_edit.setOnClickListener {
            Toast.makeText(context,"SE EDITA: ${animal.nombre}",Toast.LENGTH_SHORT).show()
            activity?.let {
                it.supportFragmentManager.beginTransaction()
                    .replace(R.id.e3root, Entregable3EdicionAnimalFragment.newinstance(animal))
                    .commit()
            }
        }
        btn_dell.setOnClickListener {
            val resultado = sqlHelper.deleteAnimal(animal.id)
            if (resultado > -1){
                if(resultado == 0){
                    Toast.makeText(context,"NO EXISTE REGISTRO",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"SE ELIMINO: ${animal.nombre}",Toast.LENGTH_SHORT).show()
                    activity?.let {
                        it.supportFragmentManager.beginTransaction()
                            .replace(R.id.e3root, Entregable3InicioFragment.newinstance(getData()))
                            .commit()
                    }
                }
            }else{
                Toast.makeText(context,"NO ELIMINADO",Toast.LENGTH_SHORT).show()
            }
        }
        return vista
    }
    fun getData() : ArrayList<Entregable3AnimalModel>{
        return sqlHelper.getAllAnimal()
    }
}