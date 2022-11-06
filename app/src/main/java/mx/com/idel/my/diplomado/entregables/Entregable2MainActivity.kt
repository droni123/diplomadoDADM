package mx.com.idel.my.diplomado.entregables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.com.idel.my.diplomado.R
import mx.com.idel.my.diplomado.ejerciciosclase.fragments.FirstFragment

class Entregable2MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregable2_main)
        //inicializa el fragment
        supportFragmentManager.beginTransaction()
            .add(R.id.e2ContenedorFragment, Entregable2ListaPetFragment() ) //.newinstance("DATA")
            .commit()
    }
}