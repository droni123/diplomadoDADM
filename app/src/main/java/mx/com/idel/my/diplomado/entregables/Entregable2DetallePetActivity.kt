package mx.com.idel.my.diplomado.entregables

import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import mx.com.idel.my.diplomado.R


class Entregable2DetallePetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregable2_detalle_pet)
        var pNombre = findViewById<TextView>(R.id.e2RNombrePer)
        var pImagen = findViewById<ImageView>(R.id.e2RiconoPet)
        var pDueno = findViewById<TextView>(R.id.e2RDuenoPet)
        var pEnfermo = findViewById<CheckBox>(R.id.e2REnfermoPet)
        var pGenero = findViewById<RadioGroup>(R.id.e2RGeneroPet)
        var pDescripcion = findViewById<TextView>(R.id.e2RDescripcionPet)

        for (i in 0 until pGenero.childCount) {
            (pGenero.getChildAt(i) as RadioButton).isEnabled = false
        }
        pEnfermo.isEnabled = false

        intent.extras?.let { bundle ->
            if (bundle.containsKey("KEY_DATOSPET")) {
                var dPet = bundle.getSerializable("KEY_DATOSPET") as Entregable2PetItem
                pNombre.text = dPet.nombre
                pImagen.setImageResource(getImageId(this,dPet.imagen))
                pDueno.text = HtmlCompat.fromHtml("<b>${getText(R.string.e2dueno)}</b>: ${dPet.dueno}",HtmlCompat.FROM_HTML_MODE_LEGACY)
                when(dPet.genero){
                    "E" -> pGenero.check(R.id.e2REPet)
                    "M" -> pGenero.check(R.id.e2RMPet)
                }
                pEnfermo.isChecked = dPet.enfermo
                pDescripcion.text = HtmlCompat.fromHtml("<b>${getText(R.string.e2descripcion)}</b>: ${dPet.descripcion}",HtmlCompat.FROM_HTML_MODE_LEGACY)
            }
        }
        //boton
        var botonRegresar = findViewById<Button>(R.id.e2regresar)
        botonRegresar.setOnClickListener {
            finish()
        }
    }
    fun getImageId(context: Context, imageName: String): Int {
        return context.resources.getIdentifier( imageName , "drawable", context.packageName )
    }
}