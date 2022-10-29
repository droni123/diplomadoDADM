package mx.com.idel.my.diplomado.ejerciciosclase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import mx.com.idel.my.diplomado.R

class IntentoExplisito : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intento_explisito)
        //boton
        var botonIntentoExplisito = findViewById<Button>(R.id.btnsend)
        var inpNombre = findViewById<EditText>(R.id.inputNombre)
        botonIntentoExplisito.setOnClickListener {
            var textoNombre = inpNombre.text.toString()
            if (textoNombre.isEmpty()) {
                Toast.makeText(this, "Campo nombre vacio", Toast.LENGTH_SHORT).show()
            } else {
                var intentExplisito = Intent(this, ExplisitoDetalleActivity::class.java).apply {

                    putExtra("KEY_NAME", "Idelfonso")
                    putExtra("KEY_APELLIDO", "De la Cruz")
                    putExtra("KEY_EDAD", 31)

                    var datosUsuario = Usuario("Eileen", "Soto", 30)
                    putExtra("KEY_USER", datosUsuario)

                    putExtra("KEY_ET_NAME",textoNombre)
                }
                startActivity(intentExplisito)
            }
        }
    }
}