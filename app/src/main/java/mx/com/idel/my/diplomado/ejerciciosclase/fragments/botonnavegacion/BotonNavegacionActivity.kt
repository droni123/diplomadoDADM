package mx.com.idel.my.diplomado.ejerciciosclase.fragments.botonnavegacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import mx.com.idel.my.diplomado.R


class BotonNavegacionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boton_navegacion)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var btnmenus = findViewById<BottomNavigationView>(R.id.c5menu)
        btnmenus.selectedItemId = R.id.c5opcion_2
        mostrarFragmento(Opcion2Fragment())
        btnmenus.setOnItemSelectedListener {
            when(it.itemId){
                R.id.c5opcion_1 -> {
                    mostrarFragmento(Opcion1Fragment())
                    return@setOnItemSelectedListener true
                }
                R.id.c5opcion_2 -> {
                    mostrarFragmento(Opcion2Fragment())
                    return@setOnItemSelectedListener true
                }
                R.id.c5opcion_3 -> {
                    mostrarFragmento(Opcion3Fragment())
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }
    }
    private fun mostrarFragmento(fragment: Fragment){
        var iniciaFragment = supportFragmentManager.beginTransaction()
        iniciaFragment.replace(R.id.c5root, fragment)
        iniciaFragment.commit()
    }
}