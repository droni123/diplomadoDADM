package mx.com.idel.diplomado.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.com.idel.diplomado.R
import mx.com.idel.diplomado.databinding.FragmentListBinding
import mx.com.idel.diplomado.view.adapters.UserAdapter
import mx.com.idel.diplomado.viewmodel.ListViewModel

class ListFragment : Fragment(R.layout.fragment_list) {

    private val userViewModel : ListViewModel by viewModels()

    private lateinit var userAdapter: UserAdapter

    //private lateinit var binding : FragmentListBinding

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.refresh.setOnRefreshListener {
            userViewModel.getUserList()
            binding.refresh.isRefreshing = false
        }
        userAdapter = UserAdapter(arrayListOf())

        userAdapter.onItemClick = {
            val action = ListFragmentDirections.actionListFragmentToDetallFragment(it)
            findNavController().navigate(action)
/*
            parentFragmentManager.beginTransaction()
                .replace(R.id.container,DetallFragment.newInstance(it))
                .addToBackStack("DetallFragment")
                .commit()
*/
        }

        binding.listaUser.layoutManager = LinearLayoutManager(view.context,RecyclerView.VERTICAL,false)
        binding.listaUser.adapter = userAdapter

        userViewModel.listaUsuarios.observe(viewLifecycleOwner, Observer {
            userAdapter.updateDataItems(it)
        })

        userViewModel.loader.observe(viewLifecycleOwner) {
            binding.loader.visibility = if (it == true) View.VISIBLE else View.INVISIBLE
            binding.listaUser.visibility = if (it == false) View.VISIBLE else View.INVISIBLE
        }
        userViewModel.getUserList()
        return view
    }
/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)
        binding.refresh.setOnRefreshListener {
            userViewModel.getUserList()
            binding.refresh.isRefreshing = false
        }
        userAdapter = UserAdapter(arrayListOf())
        userAdapter.onItemClick = {
            val action = ListFragmentDirections.actionListFragmentToDetallFragment(it)
            findNavController().navigate(action)
        }
        binding.listaUser.layoutManager = LinearLayoutManager(view.context,RecyclerView.VERTICAL,false)
        binding.listaUser.adapter = userAdapter

        userViewModel.listaUsuarios.observe(viewLifecycleOwner, Observer {
            userAdapter.updateDataItems(it)
        })
        userViewModel.loader.observe(viewLifecycleOwner) {
            binding.loader.visibility = if (it == true) View.VISIBLE else View.INVISIBLE
            binding.listaUser.visibility = if (it == false) View.VISIBLE else View.INVISIBLE
        }
        //userViewModel.getUserList()
    }
*/

}