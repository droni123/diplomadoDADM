package mx.com.idel.diplomado.view.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.com.idel.diplomado.R
import mx.com.idel.diplomado.databinding.ActivityUserListBinding
import mx.com.idel.diplomado.view.fragments.ListFragment

class UserListActivity : AppCompatActivity() {
    private lateinit var brinding : ActivityUserListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        brinding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(brinding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, ListFragment())
            .commit()
    }

}