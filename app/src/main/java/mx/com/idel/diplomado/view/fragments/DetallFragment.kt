package mx.com.idel.diplomado.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.com.idel.diplomado.R
import mx.com.idel.diplomado.databinding.FragmentDetallBinding
import mx.com.idel.diplomado.model.entities.UserModel


class DetallFragment : Fragment(R.layout.fragment_detall) {
    private lateinit var binding : FragmentDetallBinding

    private var user : UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getSerializable(ARG_USER) as UserModel?
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetallBinding.bind(view)
        user?.let {
            val name = requireActivity().getString(R.string.detalle_name, it.nombre)
            val apellido = requireContext().getString(R.string.detalle_apellido, it.apellido)
            val edad = requireContext().getString(R.string.detalle_edad, it.edad.toString())
            binding.nombreR.text = name
            binding.apellidoR.text = apellido
            binding.edadR.text = edad
        }
    }
    companion object{
        private  const val ARG_USER = "ARG_USER"
        fun newInstance(user:UserModel):DetallFragment{
            return DetallFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_USER,user)
                }
            }
        }
    }
}