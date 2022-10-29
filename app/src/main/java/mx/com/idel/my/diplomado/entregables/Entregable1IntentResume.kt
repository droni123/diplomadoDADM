package mx.com.idel.my.diplomado.entregables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import mx.com.idel.my.diplomado.R

class Entregable1IntentResume : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregable1_intent_resume)

        var rNombre = findViewById<TextView>(R.id.e1vr_nombre)
        var rEdad = findViewById<TextView>(R.id.e1vr_edad)
        var rNumero = findViewById<TextView>(R.id.e1vr_tel)
        var rCorreo = findViewById<TextView>(R.id.e1vr_correo)

        intent.extras?.let { bundle ->

            if (bundle.containsKey("KEY_DATOSPERSONALES")) {
                var vDatosPer = bundle.getSerializable("KEY_DATOSPERSONALES") as Entregable1IntentClassDatosP
                rNombre.text = "${getString(R.string.e1lt_completo)}: ${vDatosPer.nombre} ${vDatosPer.apellido1} ${vDatosPer.apellido2}"
                rEdad.text = "${getString(R.string.e1lt_edad)}: ${vDatosPer.edad}"
                rNumero.text = "${getString(R.string.e1lt_tel)}: ${vDatosPer.tel}"
                rCorreo.text = "${getString(R.string.e1lt_email)}: ${vDatosPer.correo}"

            }

        } ?: sinDatosRecibidos()

        //boton
        var botonRegresar = findViewById<Button>(R.id.e1vr_btn)
        botonRegresar.setOnClickListener {
            finish()
        }


    }

    private fun sinDatosRecibidos() {
        Toast.makeText(this,"${getString(R.string.e1lt_sindata)}",Toast.LENGTH_SHORT).show()
    }
}