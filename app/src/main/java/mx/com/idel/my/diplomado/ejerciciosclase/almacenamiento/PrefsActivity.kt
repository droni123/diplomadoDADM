package mx.com.idel.my.diplomado.ejerciciosclase.almacenamiento

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import mx.com.idel.my.diplomado.R

class PrefsActivity : AppCompatActivity() {

    private lateinit var edit : EditText
    private lateinit var show : TextView
    private lateinit var btn_save : Button
    private lateinit var btn_dell : Button

    private lateinit var prefs : SharedPreferences
    private var PREFS_NAME = "da_idel_tmp"
    private var SHARE_NAME = "da_idel_sharename"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prefs)

        prefs = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)

        edit = findViewById<EditText>(R.id.c5inTexto)
        show = findViewById<TextView>(R.id.c5ouTexto)
        btn_save = findViewById<Button>(R.id.c5btnSave)
        btn_dell = findViewById<Button>(R.id.c5btnDell)

        configView()

        btn_save.setOnClickListener {
            prefs.edit().putString(SHARE_NAME,edit.text.toString()).apply()
            configView()
        }
        btn_dell.setOnClickListener {
            prefs.edit().remove(SHARE_NAME).apply()
            configView()
        }
    }
    private fun showinfo(){
        edit.visibility = View.INVISIBLE
        btn_save.visibility = View.INVISIBLE
        show.visibility = View.VISIBLE
        btn_dell.visibility = View.VISIBLE

        show.text = "Hola: ${prefs.getString(SHARE_NAME,"")}"
    }
    private fun delinfo(){
        edit.visibility = View.VISIBLE
        btn_save.visibility = View.VISIBLE
        show.visibility = View.INVISIBLE
        btn_dell.visibility = View.INVISIBLE

        show.text = prefs.getString(SHARE_NAME,"")
    }
    private  fun isInfoSave(): Boolean {
        val myName = prefs.getString(SHARE_NAME,"")
        return myName?.isNotEmpty() == true
    }
    private fun configView(){
        if(isInfoSave()) showinfo() else delinfo()
    }
}