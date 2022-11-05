package mx.com.idel.my.diplomado.ejerciciosclase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import mx.com.idel.my.diplomado.R

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_second, container, false)
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val tvViewText = view.findViewById<TextView>(R.id.c4ftv2)
        tvViewText.setOnClickListener {
            //si es denteo del activity: parentFragmentManager
            parentFragmentManager.beginTransaction()
                .replace(R.id.c4contenedorfragmentos,FirstFragment.newinstance("NOMBRE DE EJEMPLO"))
                .commit()
        }
        return view
    }

}