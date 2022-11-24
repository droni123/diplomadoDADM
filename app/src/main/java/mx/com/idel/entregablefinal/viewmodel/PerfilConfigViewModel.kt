package mx.com.idel.entregablefinal.viewmodel

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.com.idel.entregablefinal.KEY_AVATAR_USER
import mx.com.idel.entregablefinal.KEY_NOMBRE_USER
import mx.com.idel.entregablefinal.KEY_TIEMPO_DE_ESPERA
import mx.com.idel.entregablefinal.model.entities.ConfiguracionModel
import mx.com.idel.entregablefinal.model.repository.AnimalSqlHelper

class PerfilConfigViewModel : ViewModel()  {

    private lateinit var bdHelper: AnimalSqlHelper

    val status = MutableLiveData<Int>()

    fun saveConfiguracion(nombre:String,avatar:String){
        bdHelper = AnimalSqlHelper()
        Handler(Looper.getMainLooper()).postDelayed({
            val nombre_to_save = ConfiguracionModel(key = KEY_NOMBRE_USER, value = nombre)
            val avatar_to_save = ConfiguracionModel(key = KEY_AVATAR_USER, value = avatar)
            var resultado = bdHelper.savConfig(avatar_to_save) //SE GUARDA
            var res = -1
            if(resultado > -1){
                var datos = bdHelper.getConfig(KEY_AVATAR_USER) //SE CONFIRMA ALMACENAMIENTO
                if(!datos.value.isNullOrEmpty()){
                    resultado = bdHelper.savConfig(nombre_to_save) //SE GUARDA
                    if(resultado > -1){
                        datos = bdHelper.getConfig(KEY_NOMBRE_USER) //SE CONFIRMA ALMACENAMIENTO
                        if(!datos.value.isNullOrEmpty()){
                            res = resultado
                        }
                    }
                }
            }
            status.postValue(res)
        }, KEY_TIEMPO_DE_ESPERA)
    }
}