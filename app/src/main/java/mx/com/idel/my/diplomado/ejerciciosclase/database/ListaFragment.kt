package mx.com.idel.my.diplomado.ejerciciosclase.database

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.com.idel.my.diplomado.R
import mx.com.idel.my.diplomado.entregables.Entregable2PetAdapter

class ListaFragment : Fragment() {
    private lateinit var sqlHelper: SqlHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sqlHelper = SqlHelper(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_lista, container, false)
        //Se declara RecyclerView
        val listaU = vista.findViewById<RecyclerView>(R.id.RecyclerViewUser)
        //Se declara Adapter
        val userAdapter = UsuarioAdapter(getData())
        //Se declara layoutManager
        listaU.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        //Se envia adaptados a lista
        listaU.adapter = userAdapter
        return vista
    }
    fun getData(): ArrayList<userSqlModel>{
        var data = sqlHelper.getAllUsers()
        return data
    }

}