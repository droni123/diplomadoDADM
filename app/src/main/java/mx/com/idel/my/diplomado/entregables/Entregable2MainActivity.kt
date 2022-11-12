package mx.com.idel.my.diplomado.entregables

import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import mx.com.idel.my.diplomado.R

class Entregable2MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregable2_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //ANIMACION
        /*
         var animacion = findViewById<ImageView>(R.id.animacion)
        animacion.setImageResource(R.drawable.loadc)
        val anima = animacion.drawable as AnimatedVectorDrawable
        anima.start()
        */
        //END
        //inicializa el fragment
        supportFragmentManager.beginTransaction()
            .add(R.id.e2ContenedorFragment, Entregable2ListaPetFragment.newinstance( getData() ))
            .commit()
    }
    private fun getData() : ArrayList<Entregable2PetItem>{
        var data =arrayListOf<Entregable2PetItem>()
        data.add(Entregable2PetItem("Odie","perro_1","Idel","M",false,"Hay mucha química entre su dueño y el"))
        data.add(Entregable2PetItem("Pongo","perro_9","Eileen","M",false,"Jugeton y gracioso"))
        data.add(Entregable2PetItem("Snoopy","perro_2","Paco","E",false,"Leal con su dueña"))
        data.add(Entregable2PetItem("Slinky","perro_3","Itzel","E",false,"Compañerá inseparable de su dueña"))
        data.add(Entregable2PetItem("Toto","perro_4","Elvira","M",true,"Es muy grande de edad"))
        data.add(Entregable2PetItem("Balto","perro_5","Yesenia","E",false,"Le gusta dar besos"))
        data.add(Entregable2PetItem("Marley","perro_6","Narciso","M",false,"Le gusta recibir a su dueño moviendo la cola"))
        data.add(Entregable2PetItem("Bolt","perro_7","Maria","M",true,"Es muy obediente y serio"))
        data.add(Entregable2PetItem("Golfo","perro_8","Poncho","E",true,"Se hace amigo de todos"))
        data.add(Entregable2PetItem("Beethoven","perro_10","Luis","M",false,"Protector, no deja de ladrar"))
        return data
    }
}