package mx.com.idel.entregablefinal.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import mx.com.idel.entregablefinal.R
import mx.com.idel.entregablefinal.databinding.ActivityMainBinding
import mx.com.idel.entregablefinal.view.fragment.LoadingFragmentDirections
import mx.com.idel.entregablefinal.viewmodel.StartViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController : NavController
    private val startViewModel : StartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inicializarConfig(this@MainActivity)
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }

    private fun inicializarConfig(context: Context){
        startViewModel.getConfiguracion(context)
        startViewModel.nombre.observe(this) {
            if (it.value.isNullOrEmpty()) {
                val accion = LoadingFragmentDirections.actionLoadingFragmentToPerfilConfigFragment()
                val navHostFragment = supportFragmentManager.findFragmentById(R.id.MainRoot) as NavHostFragment
                navHostFragment.navController.navigate(accion)
            } else {
                val vistaHome = Intent(this, AppnimalActivity::class.java)
                startActivity(vistaHome)
                finish()
                overridePendingTransition(0, R.drawable.fade_screen)
            }
        }
    }
}