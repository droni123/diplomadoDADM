package mx.com.idel.my.diplomado.entregables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import mx.com.idel.my.diplomado.R

class Entregable3MainActivity : AppCompatActivity() {

    private lateinit var sqlHelper: Entregable3AnimalSqlHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregable3_main)
        sqlHelper = Entregable3AnimalSqlHelper(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mostrarFragmento( Entregable3InicioFragment.newinstance(getData()) )
        val btnmenus = findViewById<BottomNavigationView>(R.id.e3menu)
        btnmenus.selectedItemId = R.id.e3inicio_menu
        btnmenus.setOnItemSelectedListener {
            when(it.itemId){
                R.id.e3inicio_menu -> {
                    mostrarFragmento( Entregable3InicioFragment.newinstance(getData()) )
                    return@setOnItemSelectedListener true
                }
                R.id.e3agregar_menu -> {
                    mostrarFragmento( Entregable3EdicionAnimalFragment() )
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }
    }
    private fun mostrarFragmento(fragment: Fragment){
        val iniciaFragment = supportFragmentManager.beginTransaction()
        iniciaFragment.replace(R.id.e3root, fragment)
        iniciaFragment.commit()
    }
    private fun getData() : ArrayList<Entregable3AnimalModel>{
        return sqlHelper.getAllAnimal()
    }

}