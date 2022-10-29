package mx.com.idel.my.diplomado.ejerciciosclase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import mx.com.idel.my.diplomado.R

class MainActivity : AppCompatActivity() {
    private var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        //crea | construye | arranca app
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onStart() {
        //inicia app
        super.onStart()
        Log.e(TAG, "${getString(R.string.entre_en)} onStart")
    }
    override fun onResume() {
        //activa app
        super.onResume()
        Log.e(TAG,"${getString(R.string.entre_en)} onResume")
    }
    override fun onPause() {
        //pausa app
        super.onPause()
        Log.e(TAG,"${getString(R.string.entre_en)} onPause")
    }
    override fun onRestart() {
        //restaura app
        super.onRestart()
        Log.e(TAG,"${getString(R.string.entre_en)} onRestart")
    }
    override fun onDestroy() {
        //destruye app
        super.onDestroy()
        Log.e(TAG,"${getString(R.string.entre_en)} onDestroy")
    }

}