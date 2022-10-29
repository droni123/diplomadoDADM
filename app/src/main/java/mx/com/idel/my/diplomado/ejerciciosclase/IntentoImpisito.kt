package mx.com.idel.my.diplomado.ejerciciosclase

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import mx.com.idel.my.diplomado.R

class IntentoImpisito : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intento_impisito)
        //boton
        var botonVista = findViewById<Button>(R.id.btn_sed)

        botonVista.setOnClickListener {
            //creacion de intento
            var enviarCorreo = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("icruz@global.unam.mx"))
                putExtra(Intent.EXTRA_SUBJECT,"Esto es un intento implisito")
                putExtra(Intent.EXTRA_TEXT,"Este es el cuerpo del correo")
            }
            startActivity(Intent.createChooser(enviarCorreo,"Hola"))
        }

    }
}