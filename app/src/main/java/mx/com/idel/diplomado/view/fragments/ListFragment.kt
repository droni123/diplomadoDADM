package mx.com.idel.diplomado.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.com.idel.diplomado.R
import mx.com.idel.diplomado.databinding.FragmentListBinding
import mx.com.idel.diplomado.view.adapters.UserAdapter
import mx.com.idel.diplomado.viewmodel.ListViewModel

class ListFragment : Fragment(R.layout.user_item) {

    private val userViewModel : ListViewModel by viewModels()

    private lateinit var userAdapter: UserAdapter

    //private lateinit var brinding : FragmentListBinding
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        userAdapter = UserAdapter(arrayListOf())

        userAdapter.onItemClick = {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container,DetallFragment.newInstance(it))
                .addToBackStack("DetallFragment")
                .commit()
        }

        binding.listaUser.layoutManager = LinearLayoutManager(view.context,RecyclerView.VERTICAL,false)
        binding.listaUser.adapter = userAdapter

        userViewModel.getUserList()

        userViewModel.listaUsuarios.observe(viewLifecycleOwner, Observer {
            userAdapter.updateDataItems(it)
        })
        
        return view
    }
/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        brinding = FragmentListBinding.bind(view)

        userAdapter = UserAdapter(arrayListOf())

        brinding.listaUser.layoutManager = LinearLayoutManager(view.context,RecyclerView.VERTICAL,false)
        brinding.listaUser.adapter = userAdapter

        userViewModel.getUserList()

        userViewModel.listaUsuarios.observe(viewLifecycleOwner, Observer {
            userAdapter.updateDataItems(it)
        })
    }
*/

}