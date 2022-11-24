package mx.com.idel.entregablefinal.viewmodel

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.com.idel.entregablefinal.KEY_NOMBRE_USER
import mx.com.idel.entregablefinal.KEY_TIEMPO_DE_ESPERA_STAR
import mx.com.idel.entregablefinal.model.entities.ConfiguracionModel
import mx.com.idel.entregablefinal.model.repository.AnimalSqlHelper

class StartViewModel : ViewModel()  {

    private lateinit var bdHelper: AnimalSqlHelper

    val nombre = MutableLiveData<ConfiguracionModel>()

    fun getConfiguracion(){
        bdHelper = AnimalSqlHelper()
        Handler(Looper.getMainLooper()).postDelayed({
            val n = bdHelper.getConfig(KEY_NOMBRE_USER)
            nombre.postValue(n)
        }, KEY_TIEMPO_DE_ESPERA_STAR)
    }
}