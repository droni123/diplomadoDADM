package mx.com.idel.diplomado.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.com.idel.diplomado.model.entities.UserModel
import mx.com.idel.diplomado.model.repository.UserRepository

class ListViewModel : ViewModel()  {
    val listaUsuarios = MutableLiveData<ArrayList<UserModel>>()
    val loader = MutableLiveData<Boolean>()
    init {
        getUserList()
    }
    fun getUserList(){
        loader.postValue(true)
        Handler(Looper.getMainLooper()).postDelayed({
            val usuarios = UserRepository.getUserList()
            listaUsuarios.postValue(usuarios)
            loader.postValue(false)
        },3000)
    }
}