package mx.com.idel.my.diplomado.ejerciciosclase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import mx.com.idel.my.diplomado.R

class ExplisitoDetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explisito_detalle)

        var sNombre = findViewById<TextView>(R.id.iNombre)
        var sApellido = findViewById<TextView>(R.id.iApellido)
        var sEdad = findViewById<TextView>(R.id.iEdad)

        intent.extras?.let { bundle ->
            if (bundle.containsKey("KEY_NAME")) {
                var vName = bundle.getString("KEY_NAME")
                sNombre.text = vName
            }
            if (bundle.containsKey("KEY_APELLIDO")) {
                var vApellido = bundle.getString("KEY_APELLIDO")
                sApellido.text = vApellido
            }
            if (bundle.containsKey("KEY_EDAD")) {
                var vEdad = bundle.getInt("KEY_EDAD")
                sEdad.text = "$vEdad"
            }
            if (bundle.containsKey("KEY_USER")) {
                var vUsuario = bundle.getSerializable("KEY_USER") as Usuario
                sNombre.text = vUsuario.name
                sApellido.text = vUsuario.apellido
                sEdad.text = "${vUsuario.edad}"
            }
            if (bundle.containsKey("KEY_ET_NAME")) {
                var vetName = bundle.getInt("KEY_ET_NAME")
                Toast.makeText(this,vetName,Toast.LENGTH_LONG).show()
            }
        } ?: showInformacionVacia()
    }

    private fun showInformacionVacia() {
        Toast.makeText(this,"Extras vacio",Toast.LENGTH_SHORT).show()
    }
}