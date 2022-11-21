package mx.com.idel.entregablefinal.view.fragment

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.com.idel.entregablefinal.databinding.FragmentListAnimalsBinding
import mx.com.idel.entregablefinal.model.entities.AnimalModel
import mx.com.idel.entregablefinal.view.adapter.AnimalAdapter
import mx.com.idel.entregablefinal.viewmodel.ListViewModel

class ListAnimalsFragment : Fragment() {

    private var _binding: FragmentListAnimalsBinding? = null
    private val binding get() = _binding!!

    private val listaViewModel : ListViewModel by viewModels()

    private lateinit var animalAdapter: AnimalAdapter

    private lateinit var anima: AnimatedVectorDrawable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListAnimalsBinding.inflate(inflater, container, false)
        val view = binding.root

        anima = binding.loaddatos.drawable as AnimatedVectorDrawable

        val demo = ArrayList<AnimalModel>()
        animalAdapter = AnimalAdapter(demo)

        binding.listaAnimales.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL,false)
        binding.listaAnimales.adapter = animalAdapter

        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = false
            listaViewModel.getAnimalList(view.context)
        }
        listaViewModel.loader.observe(viewLifecycleOwner) {
            binding.loaddatos.visibility = if (it == true) View.VISIBLE else View.INVISIBLE
            binding.sinDatos.visibility = if (it == false && animalAdapter.itemCount <= 0) View.VISIBLE else View.INVISIBLE
            binding.listaAnimales.visibility = if (it == false && animalAdapter.itemCount > 0) View.VISIBLE else View.INVISIBLE
            if (it == true) anima.start() else anima.stop()
        }
        listaViewModel.listaAnimales.observe(viewLifecycleOwner, Observer {
            animalAdapter.updateDataItems(it)
            animalAdapter.itemCount
        })
        animalAdapter.onItemClick = {
            val accion = ListAnimalsFragmentDirections.actionListAnimalsFragmentToDetallAnimalFragment(it)
            findNavController().navigate(accion)
        }
        listaViewModel.getAnimalList(view.context)
        return view
    }

    override fun onResume() {
        super.onResume()
        activity?.let{
            listaViewModel.getAnimalList(it)
        }
    }

}