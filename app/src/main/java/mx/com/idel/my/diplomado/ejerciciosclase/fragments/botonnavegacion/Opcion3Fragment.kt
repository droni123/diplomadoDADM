package mx.com.idel.my.diplomado.ejerciciosclase.fragments.botonnavegacion

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import mx.com.idel.my.diplomado.R

class Opcion3Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_opcion3, container, false)
        //ANIMACION
        var animacion = vista.findViewById<ImageView>(R.id.loadIng)
        animacion.setImageResource(R.drawable.loadc)
        val anima = animacion.drawable as AnimatedVectorDrawable
        anima.start()

        return vista
    }

}