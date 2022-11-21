package mx.com.idel.entregablefinal.view.fragment

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Toast
import androidx.fragment.app.viewModels
import mx.com.idel.entregablefinal.R
import mx.com.idel.entregablefinal.databinding.ActivityPefilBinding
import mx.com.idel.entregablefinal.view.activity.AppnimalActivity
import mx.com.idel.entregablefinal.viewmodel.PerfilConfigViewModel

class PerfilConfigFragment : Fragment() {

    private var _binding: ActivityPefilBinding? = null
    private val binding get() = _binding!!

    private val PerfilViewModel : PerfilConfigViewModel by viewModels()

    private lateinit var anima: AnimatedVectorDrawable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityPefilBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.perfilSave.setOnClickListener {
            activity?.let {
                SaveData(it)
            }
        }
        return view
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
            PerfilViewModel.status.observe(viewLifecycleOwner){
                if(it > -1){
                    activity?.let{
                        val vistaHome = Intent(it, AppnimalActivity::class.java)
                        startActivity(vistaHome)
                        it.finish()
                        it.overridePendingTransition(0, R.drawable.fade_screen)
                    }
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