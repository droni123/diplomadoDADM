package mx.com.idel.my.diplomado.ejerciciosclase.fragments

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import mx.com.idel.my.diplomado.R


class FirstFragment : Fragment() {
    var nombre : String? = null
    companion object{
        fun newinstance(nombre : String) : FirstFragment{
            return FirstFragment().apply {
                arguments = Bundle().apply {
                    putString("ARG_NOMBRE",nombre)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            nombre = it.getString("ARG_NOMBRE")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_first, container, false)
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val tvViewText = view.findViewById<TextView>(R.id.c4ftv1)
        tvViewText.text = nombre
        tvViewText.setOnClickListener {
            //si es denteo del activity: parentFragmentManager
            parentFragmentManager.beginTransaction()
                .replace(R.id.c4contenedorfragmentos,SecondFragment())
                .commit()
        }
        return view
    }


}