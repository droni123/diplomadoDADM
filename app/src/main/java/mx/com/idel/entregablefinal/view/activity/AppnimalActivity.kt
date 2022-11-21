package mx.com.idel.entregablefinal.view.activity

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import mx.com.idel.entregablefinal.R
import mx.com.idel.entregablefinal.databinding.ActivityAppnimalBinding
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import mx.com.idel.entregablefinal.viewmodel.AppnimalViewModel

class AppnimalActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAppnimalBinding

    private lateinit var navController : NavController
    private val appnimalViewModel : AppnimalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargardatosperfil(this@AppnimalActivity)

        binding.avatar.setOnClickListener {
            val vistaActivity = Intent(this@AppnimalActivity, PefilActivity::class.java)
            startActivity(vistaActivity)
        }
        binding.agregarAnimal.setOnClickListener {
            val vistaActivity = Intent(this@AppnimalActivity, EditarAnimalActivity::class.java)
            startActivity(vistaActivity)
        }
        binding.acercade.setOnClickListener {

            val spannable = SpannableStringBuilder(getString(R.string.creditos_datos))
            spannable.setSpan(StyleSpan(Typeface.BOLD), 15, 45, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)

            MaterialAlertDialogBuilder(this@AppnimalActivity,R.style.MaterialAlertDialog__Center)
                .setTitle(getString(R.string.creditos))
                .setMessage(spannable)
                .setPositiveButton(getString(R.string.aceptar),null)
                .show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }
    override fun onResume() {
        super.onResume()
        cargardatosperfil(this@AppnimalActivity)
    }
    private fun cargardatosperfil(context: Context) {
        appnimalViewModel.getConfiguracion(context)
        appnimalViewModel.nombre.observe(this) {
            if (!it.isNullOrEmpty()){
                val spannable = SpannableStringBuilder("${getString(R.string.bienvenido)}:\n${it}")
                spannable.setSpan(StyleSpan(Typeface.BOLD), 13, 16, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                spannable.setSpan(StyleSpan(Typeface.BOLD), 22, spannable.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                binding.nombreusuario.text = spannable
            }
        }
        appnimalViewModel.avatar.observe(this) {
            if (!it.isNullOrEmpty()){
            Glide.with(context)
                .load(it)
                .centerCrop()
                .circleCrop()
                .into(binding.avatar)
            }
        }
    }
}