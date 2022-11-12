package mx.com.idel.my.diplomado.ejerciciosclase.database

import android.media.MediaDrm.LogMessage
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import mx.com.idel.my.diplomado.R


class SqliteActivity : AppCompatActivity() {

    private lateinit var sqlHelper: SqlHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        sqlHelper = SqlHelper(this)

        var nombre = findViewById<EditText>(R.id.c6etNombre)
        var descripcion = findViewById<EditText>(R.id.c6etDescripcion)
        var btnsave = findViewById<Button>(R.id.c6btnSave)

        mostrarFragmento(ListaFragment())

        btnsave.setOnClickListener {
            if(nombre.text.toString().isEmpty() || descripcion.text.toString().isEmpty()){
                Toast.makeText(this,"INGRESAR INFORMACIÓN",Toast.LENGTH_SHORT).show()
            }else{
                val usuario = userSqlModel(name = nombre.text.toString(), descripcion = descripcion.text.toString())
                val resultado = sqlHelper.insert(usuario)
                if (resultado > -1){
                    mostrarFragmento(ListaFragment())
                    Toast.makeText(this,"INFORMACIÓN GUARDADA",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"INFORMACIÓN NO GUARDADA",Toast.LENGTH_SHORT).show()
                }
            }
        }
        var btnShoe = findViewById<Button>(R.id.c6btnShow)
        btnShoe.setOnClickListener {
            mostrarFragmento(ListaFragment())
            var lista = sqlHelper.getAllUsers()
            Log.e("DRONI",lista.toString())
        }

        var btnUpd = findViewById<Button>(R.id.c6btnUpdate)
        btnUpd.setOnClickListener {
            val usuarioup = userSqlModel(1, "IDEL", "INFORMACION ACRUALIZADA")
            var resultado = sqlHelper.updateUsuario(usuarioup)
            if (resultado > -1){
                mostrarFragmento(ListaFragment())
                Toast.makeText(this,"INFORMACIÓN ACTUALIZADA",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"INFORMACIÓN NO ACTUALIZADA",Toast.LENGTH_SHORT).show()
            }
        }
        var btndell = findViewById<Button>(R.id.c6btnDell)
        btndell.setOnClickListener {
            var resultado = sqlHelper.deleteUser(4)
            if (resultado > -1){
                if(resultado == 0){
                    Toast.makeText(this,"NO EXISTE REGISTRO",Toast.LENGTH_SHORT).show()
                }else{
                    mostrarFragmento(ListaFragment())
                    Toast.makeText(this,"ELIMINADO",Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this,"NO ELIMINADO",Toast.LENGTH_SHORT).show()
            }
        }


    }
    private fun mostrarFragmento(fragment: Fragment){
        var iniciaFragment = supportFragmentManager.beginTransaction()
        iniciaFragment.replace(R.id.e2ContenedorListaFragment, fragment)
        iniciaFragment.commit()
    }
}