package mx.com.idel.diplomado.view.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import mx.com.idel.diplomado.R
import mx.com.idel.diplomado.databinding.ActivityNavBinding

class NavActivity : AppCompatActivity() {

    private lateinit var brinding : ActivityNavBinding
    private lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        brinding = ActivityNavBinding.inflate(layoutInflater)
        setContentView(brinding.root)

        navController = findNavController(R.id.container)
        setupActionBarWithNavController(navController,null)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }

}