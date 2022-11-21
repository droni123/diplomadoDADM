package mx.com.idel.entregablefinal.viewmodel

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.com.idel.entregablefinal.KEY_AVATAR_USER
import mx.com.idel.entregablefinal.KEY_NOMBRE_USER
import mx.com.idel.entregablefinal.KEY_TIEMPO_DE_ESPERA
import mx.com.idel.entregablefinal.model.repository.AnimalSqlHelper

class AppnimalViewModel : ViewModel()  {

    private lateinit var bdHelper: AnimalSqlHelper

    val nombre = MutableLiveData<String?>()
    val avatar = MutableLiveData<String?>()

    fun getConfiguracion(context: Context){
        bdHelper = AnimalSqlHelper(context)
        Handler(Looper.getMainLooper()).postDelayed({
            val xNombre = bdHelper.getConfig(KEY_NOMBRE_USER)
            val xAvatar = bdHelper.getConfig(KEY_AVATAR_USER)
            nombre.postValue(xNombre.value)
            avatar.postValue(xAvatar.value)
        }, KEY_TIEMPO_DE_ESPERA)
    }
}