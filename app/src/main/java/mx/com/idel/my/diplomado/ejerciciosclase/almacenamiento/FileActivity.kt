package mx.com.idel.my.diplomado.ejerciciosclase.almacenamiento

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import mx.com.idel.my.diplomado.R

class FileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        val uImagen = findViewById<ImageView>(R.id.c6imgurl)
        var uURLimagen = "https://www.unaminternacional.unam.mx/archivos/img/home/fomento.jpg"
        Glide
            .with(this)
            .load(uURLimagen)
            .centerCrop()
            .into(uImagen)
        val btSave = findViewById<Button>(R.id.savefile)
        val etInfo = findViewById<EditText>(R.id.c5textfile)
        val filename = "test.txt"

        resources.openRawResource(R.raw.ejemplo_raw).use { strem ->
            val text = strem.bufferedReader().use {
                it.readText()
            }
            Toast.makeText(this,"Stream: ${text}",Toast.LENGTH_SHORT).show()

        }

        btSave.setOnClickListener {
            openFileOutput(filename,Context.MODE_PRIVATE).use { output ->
                var dato = "${etInfo.text}\n"
                output.write(dato.toByteArray())

                openFileInput(filename).use {
                    val read = it.bufferedReader().use {
                        it.readText()
                    }
                    Toast.makeText(this,"Guardado: ${read}",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}