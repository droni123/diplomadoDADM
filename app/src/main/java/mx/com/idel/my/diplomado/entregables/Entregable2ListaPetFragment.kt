package mx.com.idel.my.diplomado.entregables

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.com.idel.my.diplomado.R

class Entregable2ListaPetFragment : Fragment(),Entreganle2petListener {
    lateinit var listaMascotas : ArrayList<Entregable2PetItem>
    companion object{
        fun newinstance(listaM : ArrayList<Entregable2PetItem>) : Entregable2ListaPetFragment{
            return Entregable2ListaPetFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("KEY_MASCOTAS_OBTENIDAS",listaM)
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            listaMascotas = it.getSerializable("KEY_MASCOTAS_OBTENIDAS") as ArrayList<Entregable2PetItem>
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_entregable2_lista_pet, container, false)
        //Se declara RecyclerView
        val listaPet = vista.findViewById<RecyclerView>(R.id.e2RecyclerViewPet)
        //Se declara Adapter
        val petAdapter = Entregable2PetAdapter(listaMascotas,this)
        //Se declara layoutManager
        listaPet.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        //Se envia adaptados a lista
        listaPet.adapter = petAdapter
        return vista
    }
    override fun onItemSelected(pet: Entregable2PetItem) {
        Toast.makeText(this.context,"${getString(R.string.e2visualizando)}: ${pet.nombre}", Toast.LENGTH_SHORT).show()
        var vistaDetallePet = Intent(requireContext(),Entregable2DetallePetActivity::class.java).apply {
            putExtra("KEY_DATOSPET", pet )
        }
        startActivity(vistaDetallePet)
    }
    /*
    private fun getData() : ArrayList<Entregable2PetItem>{
        var data = arrayListOf<Entregable2PetItem>()
        data.add(Entregable2PetItem("Odie","perro_1","Idel","M",false,"Hay mucha química entre su dueño y el"))
        data.add(Entregable2PetItem("Pongo","perro_9","Eileen","M",false,"Jugeton y gracioso"))
        data.add(Entregable2PetItem("Snoopy","perro_2","Paco","E",false,"Leal con su dueña"))
        data.add(Entregable2PetItem("Slinky","perro_3","Itzel","E",false,"Compañerá inseparable de su dueña"))
        data.add(Entregable2PetItem("Toto","perro_4","Elvira","M",true,"Es muy grande de edad"))
        data.add(Entregable2PetItem("Balto","perro_5","Yesenia","E",false,"Le gusta dar besos"))
        data.add(Entregable2PetItem("Marley","perro_6","Narciso","M",false,"Le gusta recibir a su dueño moviendo la cola"))
        data.add(Entregable2PetItem("Bolt","perro_7","Maria","M",true,"Es muy obediente y serio"))
        data.add(Entregable2PetItem("Golfo","perro_8","Poncho","E",true,"Se hace amigo de todos"))
        data.add(Entregable2PetItem("Beethoven","perro_10","Luis","M",false,"Protector, no deja de ladrar"))
        return data
    }
    */
}