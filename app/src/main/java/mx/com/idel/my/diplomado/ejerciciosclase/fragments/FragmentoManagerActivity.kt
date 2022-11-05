package mx.com.idel.my.diplomado.ejerciciosclase.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import mx.com.idel.my.diplomado.R

class FragmentoManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragmento_manager)
        //si es denteo del activity: supportFragmentManager


        supportFragmentManager.beginTransaction()
            .add(R.id.c4contenedorfragmentos,FirstFragment.newinstance("NOMBRE DE EJEMPLO"))
            .commit()



    }
}