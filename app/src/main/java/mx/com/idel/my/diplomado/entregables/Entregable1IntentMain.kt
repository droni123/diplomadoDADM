package mx.com.idel.my.diplomado.entregables

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import mx.com.idel.my.diplomado.R

class Entregable1IntentMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregable1_intent_main)
        //BOTON
        var btnAccion = findViewById<Button>(R.id.e1v_btn)
        //INPUT'S
        var vtxtNombre = findViewById<EditText>(R.id.e1v_nombre)
        var vtxtApellido1 = findViewById<EditText>(R.id.e1v_apellido1)
        var vtxtApellido2 = findViewById<EditText>(R.id.e1v_apellido2)
        var vtxtEdad = findViewById<EditText>(R.id.e1v_edad)
        var vtxtNumero = findViewById<EditText>(R.id.e1v_tel)
        var vtxtCorreo = findViewById<EditText>(R.id.e1v_correo)
        btnAccion.setOnClickListener {
            //GET DATA
            var txtNombre = vtxtNombre.text.toString()
            var txtApellido1 = vtxtApellido1.text.toString()
            var txtApellido2 = vtxtApellido2.text.toString()
            var txtEdad = vtxtEdad.text.toString()
            var txtNumero = vtxtNumero.text.toString()
            var txtCorreo = vtxtCorreo.text.toString()

            //VALIDACION DATA
            var continua = true
            var mensaje = "${getString(R.string.e1lt_error)} "
            if(txtNombre.isEmpty()){
                mensaje += " ${getString(R.string.e1lt_nombre)}"
                continua = false
            }
            if(txtApellido1.isEmpty()){
                mensaje += " ${getString(R.string.e1lt_apellido1)}"
                continua = false
            }
            if(txtApellido2.isEmpty()){
                mensaje += " ${getString(R.string.e1lt_apellido2)}"
                continua = false
            }
            if(txtEdad.isEmpty()){
                mensaje += " ${getString(R.string.e1lt_edad)}"
                continua = false
            }
            if(txtNumero.isEmpty()){
                mensaje += " ${getString(R.string.e1lt_tel)}"
                continua = false
            }
            if(txtCorreo.isEmpty()) {
                mensaje += " ${getString(R.string.e1lt_email)}"
                continua = false
            }
            ///SI PASA LA VALIDACIÓN
            if(continua){
                var IntentoEntregable1 = Intent(this, Entregable1IntentResume::class.java).apply {
                    var datosPersonales = Entregable1IntentClassDatosP(txtNombre,txtApellido1,txtApellido2,txtEdad,txtNumero,txtCorreo)
                    putExtra("KEY_DATOSPERSONALES", datosPersonales)
                }
                startActivity(IntentoEntregable1)
            }else{ // SI NO PASA LA VALIDACION
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
            }


        }
    }
}