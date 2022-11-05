package mx.com.idel.my.diplomado.ejerciciosclase.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.com.idel.my.diplomado.R

class RecyclerViewActivity : AppCompatActivity(),RecyclerItemListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val list = findViewById<RecyclerView>(R.id.c3lista)
        val userAdapter = UserAdaptador(getData(),this)
        list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        //list.layoutManager = GridLayoutManager(this,2)
        list.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        list.itemAnimator = DefaultItemAnimator()
        list.adapter = userAdapter
    }
    private fun getData() : ArrayList<UserItem>{
        var data = arrayListOf<UserItem>()
        data.add(UserItem("Idelfonso 1",""))
        data.add(UserItem("Eileen",""))
        data.add(UserItem("Itzel",""))
        data.add(UserItem("Elvira",""))
        data.add(UserItem("De la Cruz",""))
        data.add(UserItem("Hernández",""))
        data.add(UserItem("Soto",""))
        data.add(UserItem("Bermidez",""))
        data.add(UserItem("Otro Nombre",""))
        data.add(UserItem("Yesenia",""))
        data.add(UserItem("Narciso",""))
        data.add(UserItem("Maria",""))
        data.add(UserItem("Francisca",""))
        data.add(UserItem("Valdéz",""))
        data.add(UserItem("Márquez",""))
        data.add(UserItem("Pancho",""))
        data.add(UserItem("Juan",""))
        data.add(UserItem("Antonio",""))
        return data
    }

    override fun onItemSelected(user: UserItem) {
        Toast.makeText(this,"Touch en: ${user.nombre}", Toast.LENGTH_SHORT).show()
    }
}