package mx.com.idel.my.diplomado.ejerciciosclase.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.com.idel.my.diplomado.R

class FragmentoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
    }
}
//Crear vista con un fragment y llamar a otro activity intent
//creat lista en fragmento
//8 nimales
//Detectar cuando se le da clicl a esa lista
//Pasa a otra activisd con la informacion de ese elemento