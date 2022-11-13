package mx.com.idel.my.diplomado.entregables

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.com.idel.my.diplomado.R

class Entregable3InicioFragment : Fragment(), Entregable3AnimalListener {
    lateinit var listaAnimales : ArrayList<Entregable3AnimalModel>
    companion object{
        fun newinstance(listaA : ArrayList<Entregable3AnimalModel>) : Entregable3InicioFragment{
            return  Entregable3InicioFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("KEY_ANIMALES_OBTENIDOS",listaA)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            listaAnimales = it.getSerializable("KEY_ANIMALES_OBTENIDOS") as ArrayList<Entregable3AnimalModel>
        }?: run {
            listaAnimales = arrayListOf<Entregable3AnimalModel>()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_entregable3_inicio, container, false)
        val listaAnimal = vista.findViewById<RecyclerView>(R.id.c3RecyclerViewAnimal)
        val animalAdaptador = Entregable3AnimalAdapter(listaAnimales,this)
        listaAnimal.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        listaAnimal.adapter = animalAdaptador
        if(listaAnimales.size > 0){
            val sinDatos = vista.findViewById<TextView>(R.id.e3sinDatos)
            sinDatos.visibility = INVISIBLE
        }
        return vista
    }

    override fun onItemSelected(animal: Entregable3AnimalModel) {
        Toast.makeText(context,"${getString(R.string.e2visualizando)}: ${animal.nombre}",Toast.LENGTH_SHORT).show()
        activity?.let {
            it.supportFragmentManager.beginTransaction()
                .replace(R.id.e3root, Entregable3DetalleAnimalFragment.newinstance(animal))
                .commit()
        }
    }


}