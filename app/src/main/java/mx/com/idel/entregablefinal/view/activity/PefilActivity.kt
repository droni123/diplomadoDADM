package mx.com.idel.entregablefinal.view.activity

import android.content.Context
import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import mx.com.idel.entregablefinal.R
import mx.com.idel.entregablefinal.databinding.ActivityPefilBinding
import mx.com.idel.entregablefinal.viewmodel.AppnimalViewModel
import mx.com.idel.entregablefinal.viewmodel.PerfilConfigViewModel

class PefilActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPefilBinding
    private val PerfilViewModel : PerfilConfigViewModel by viewModels()
    private val appnimalViewModel : AppnimalViewModel by viewModels()
    private lateinit var anima: AnimatedVectorDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPefilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargardatosperfil(this@PefilActivity)
        binding.perfilSave.setOnClickListener {
            SaveData(this@PefilActivity)
        }
    }
    private fun cargardatosperfil(context: Context) {
        appnimalViewModel.getConfiguracion(context)
        appnimalViewModel.nombre.observe(this) {
            if (!it.isNullOrEmpty()){
                binding.inputnombre.setText(it)
            }
        }
        appnimalViewModel.avatar.observe(this) {
            if (!it.isNullOrEmpty()){
                Glide.with(context)
                    .load(it)
                    .centerCrop()
                    .circleCrop()
                    .into(binding.icono)
                binding.inputavatar.setText(it)
            }
        }
    }
    private fun SaveData(contexto: Context){
        val nombre = binding.inputnombre.text.toString()
        val avatar = binding.inputavatar.text.toString()
        var mensaje = ""
        if(nombre.isEmpty()){
            mensaje += "\n${getString( R.string.error_falta , getString(R.string.cuser_nombre ) )}"
        }
        if(avatar.isEmpty()){
            mensaje += "\n${getString( R.string.error_falta , getString(R.string.cuser_imagen) )}"
        }else{
            if(!URLUtil.isValidUrl(avatar)){
                mensaje += "\n${getString(R.string.error_url, getString(R.string.cuser_imagen) )}"
            }
        }
        if(mensaje === ""){

            HiddeoOrShowItems(false)
            PerfilViewModel.saveConfiguracion(contexto,nombre,avatar)
            PerfilViewModel.status.observe(this){
                if(it > -1){
                    finish()
                }else{
                    HiddeoOrShowItems()
                    Toast.makeText(contexto, "${getString(R.string.error_onSave)}", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            mensaje = "${getString(R.string.error_form, mensaje )}"
            Toast.makeText(contexto, mensaje, Toast.LENGTH_SHORT).show()
        }
    }
    private fun HiddeoOrShowItems(show:Boolean = true){
        if(show) {
            binding.nombre.visibility = View.VISIBLE
            binding.avatar.visibility = View.VISIBLE
            binding.perfilSave.visibility = View.VISIBLE
            binding.icono.setImageResource(R.drawable.ico)
        }else{
            binding.nombre.visibility = View.INVISIBLE
            binding.avatar.visibility = View.INVISIBLE
            binding.perfilSave.visibility = View.INVISIBLE
            binding.icono.setImageResource(R.drawable.load)
            anima = binding.icono.drawable as AnimatedVectorDrawable
            anima.start()
        }
    }

}