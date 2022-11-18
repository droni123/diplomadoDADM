package mx.com.idel.diplomado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.com.idel.diplomado.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var brinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        brinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(brinding.root)

        brinding.textoUno.text = getString(R.string.texto1)
        brinding.textoDos.text = getString(R.string.texto2)
        brinding.textoTres.text = getString(R.string.texto3)

    }
}