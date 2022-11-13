package mx.com.idel.my.diplomado.entregables

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.com.idel.my.diplomado.R
import mx.com.idel.my.diplomado.ejerciciosclase.database.ListaFragment
import mx.com.idel.my.diplomado.ejerciciosclase.database.userSqlModel

class Entregable3EdicionAnimalFragment : Fragment() {
    lateinit var animal : Entregable3AnimalModel
    private lateinit var sqlHelper: Entregable3AnimalSqlHelper
    companion object{
        fun newinstance(animal : Entregable3AnimalModel) : Entregable3EdicionAnimalFragment{
            return  Entregable3EdicionAnimalFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("KEY_ANIMAL_A_EDITAR",animal)
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sqlHelper = Entregable3AnimalSqlHelper(requireContext())
        arguments?.let{
            animal = it.getSerializable("KEY_ANIMAL_A_EDITAR") as Entregable3AnimalModel
        }?: run {
            animal = Entregable3AnimalModel()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_entregable3_edicion_animal, container, false)
        //Toast.makeText(context,"ID: ${animal.id}", Toast.LENGTH_SHORT).show()
        val titulo = vista.findViewById<TextView>(R.id.e3eEditar)
        val nombre = vista.findViewById<EditText>(R.id.e3eNombre)
        val imagen = vista.findViewById<EditText>(R.id.e3eImagen)
        val dueno = vista.findViewById<EditText>(R.id.e3eDueno)
        val genero = vista.findViewById<RadioGroup>(R.id.e3eGeneroAnimal)
        val enfermo = vista.findViewById<CheckBox>(R.id.e3eEnfermoAnimal)
        val descrpcion = vista.findViewById<EditText>(R.id.e3eDescripcionAnimal)
        if(animal.id != 0){
            titulo.text = "${getText(R.string.e3Editar)}"
            nombre.setText(animal.nombre)
            imagen.setText(animal.imagen)
            dueno.setText(animal.dueno)
            when(animal.genero){
                true -> genero.check(R.id.e3eHAnimal)
                false -> genero.check(R.id.e3eMAnimal)
            }
            enfermo.isChecked = animal.enfermo
            descrpcion.setText(animal.descripcion)
        }
        val btn_save =  vista.findViewById<Button>(R.id.e3guardar)
        btn_save.setOnClickListener {
            //GET DATA
            val txtNombre = nombre.text.toString()
            val txtImagen = imagen.text.toString()
            val txtDueno = dueno.text.toString()
            val txtDescripcion = descrpcion.text.toString()
            val valGenero = genero.checkedRadioButtonId
            val valEnfermo = enfermo.isChecked
            var continua = true
            var datGenero = true
            //VALIDACION DATA
            var mensaje = "${getString(R.string.e1lt_error)} "
            if(txtNombre.isEmpty()){
                mensaje += " ${getString(R.string.e1lt_nombre)}"
                continua = false
            }
            if(txtImagen.isEmpty()){
                mensaje += " ${getString(R.string.e3Imagen)}"
                continua = false
            }else{
                if(!URLUtil.isValidUrl(txtImagen)){
                    mensaje += " ${getString(R.string.e3urlinvalida)}"
                    continua = false
                }
            }
            if(txtDueno.isEmpty()){
                mensaje += " ${getString(R.string.e2dueno)}"
                continua = false
            }
            if(txtDescripcion.isEmpty()){
                mensaje += " ${getString(R.string.e2descripcion)}"
                continua = false
            }
            if(valGenero < 0){
                mensaje += " ${getString(R.string.e3genero)}"
                continua = false
            }else{
                datGenero = when(valGenero){
                    R.id.e3REAnimal -> true
                    else -> false
                }
            }
            ///SI PASA LA VALIDACIÓN
            if(continua){
                val animalToSave = Entregable3AnimalModel(
                    animal.id,
                    txtNombre,
                    txtImagen,
                    txtDueno,
                    datGenero,
                    valEnfermo,
                    txtDescripcion
                )
                //val resultado = sqlHelper.insert(usuario)
                var resultado = -1
                if(animal.id != 0){
                    resultado = sqlHelper.updateAnimal(animalToSave)
                }else{
                    val insertar = sqlHelper.insertAnimal(animalToSave)
                    if (insertar > -1){ resultado = 1 }
                }
                if (resultado > -1){
                    Toast.makeText(context,"${animal.nombre} GUARDADO",Toast.LENGTH_SHORT).show()
                    activity?.let {
                        it.supportFragmentManager.beginTransaction()
                            .replace(R.id.e3root, Entregable3InicioFragment.newinstance(getData()))
                            .commit()
                    }
                }else{
                    Toast.makeText(context,"ANIMAL NO GUARDADO",Toast.LENGTH_SHORT).show()
                }
            }else{ // SI NO PASA LA VALIDACION
                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            }
        }
        return vista
    }
    fun getData() : ArrayList<Entregable3AnimalModel>{
        return sqlHelper.getAllAnimal()
    }
}