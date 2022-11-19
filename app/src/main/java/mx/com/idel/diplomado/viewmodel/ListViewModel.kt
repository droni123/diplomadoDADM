package mx.com.idel.diplomado.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.com.idel.diplomado.model.entities.UserModel
import mx.com.idel.diplomado.model.repository.UserRepository

class ListViewModel : ViewModel()  {

    val listaUsuarios = MutableLiveData<ArrayList<UserModel>>()

     fun getUserList(){
        val usuarios = UserRepository.getUserList()
        listaUsuarios.postValue(usuarios)
    }
}