package mx.com.idel.my.diplomado.ejerciciosclase.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import mx.com.idel.my.diplomado.R

class RelativeLayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relative_layout)
        var cbCredito = findViewById<CheckBox>(R.id.c3checkbox)
        var btnSave = findViewById<Button>(R.id.c3btnenviar)
        var btnGrup = findViewById<RadioGroup>(R.id.c3guporadio)
        var spiner = findViewById<Spinner>(R.id.c3spiner)
        //CHECKBOX
        cbCredito.isChecked = true
        cbCredito.setOnCheckedChangeListener { checkbox, isChequed ->
            btnSave.isEnabled = isChequed != false
            Toast.makeText(this,"isChequed: $isChequed",Toast.LENGTH_SHORT).show()
        }
        //RADIOBUTTON
        btnGrup.check(R.id.c3rF)
        btnGrup.setOnCheckedChangeListener { _, id ->
            val RbSeleccionado = when(id){
                R.id.c3rM -> getString(R.string.c3radiobtnM)
                R.id.c3rF -> getString(R.string.c3radiobtnF)
                else -> getString(R.string.c3otro)
            }
            Toast.makeText(this,"RadioButton: $RbSeleccionado",Toast.LENGTH_SHORT).show()
        }
        /*
        btnGrup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(parent: RadioGroup?, id: Int) {
                val RbSeleccionado = when(id){
                    R.id.c3rM -> getString(R.string.c3radiobtnM)
                    R.id.c3rF -> getString(R.string.c3radiobtnF)
                    else -> getString(R.string.c3otro)
                }
                Toast.makeText(this@RelativeLayout,"RadioButton: $RbSeleccionado",Toast.LENGTH_SHORT).show()
            }
        })
        */
        //SPINER
        var datos = arrayListOf("Elemento 1","Elemento 2","Elemento 3","Elemento 4","Elemento 5","Elemento 6","Elemento 7","Elemento 8","Elemento 9","Elemento 10","Elemento 11")
        var adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_item,datos)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //val listaSeleccionado = parent?.getItemIdAtPosition(position)
                var listaSeleccionado = datos[position]
                Toast.makeText(this@RelativeLayout,"Item: $listaSeleccionado",Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        spiner.adapter = adaptador
        //RecyclerView

        //BUTON CLICK
        btnSave.setOnClickListener {
            var isChequed = cbCredito.isChecked
            val RbSeleccionado = when(btnGrup.checkedRadioButtonId){
                R.id.c3rM -> getString(R.string.c3radiobtnM)
                R.id.c3rF -> getString(R.string.c3radiobtnF)
                else -> getString(R.string.c3otro)
            }
            var itemSelecionado = spiner.selectedItemPosition
            var spinerItem = datos[itemSelecionado]
            Toast.makeText(this,"isChequed: $isChequed\nRadioButton: $RbSeleccionado\nItem: $spinerItem",Toast.LENGTH_SHORT).show()
        }
    }
}