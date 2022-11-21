package mx.com.idel.entregablefinal.viewmodel

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.com.idel.entregablefinal.KEY_TIEMPO_DE_ESPERA
import mx.com.idel.entregablefinal.model.entities.AnimalModel
import mx.com.idel.entregablefinal.model.repository.AnimalSqlHelper

class ListViewModel : ViewModel()  {

    private lateinit var bdHelper: AnimalSqlHelper

    val listaAnimales = MutableLiveData<ArrayList<AnimalModel>>()
    val loader = MutableLiveData<Boolean>()

    val statusdell = MutableLiveData<Int>()

    val statussave = MutableLiveData<Int>()

    fun getAnimalList(context: Context){
        bdHelper = AnimalSqlHelper(context)
        loader.postValue(true)
        Handler(Looper.getMainLooper()).postDelayed({
            val animales = bdHelper.getAllAnimal()
            listaAnimales.postValue(animales)
            loader.postValue(false)
        }, KEY_TIEMPO_DE_ESPERA)
    }

    fun dellAnimal(context: Context,id: Int){
        bdHelper = AnimalSqlHelper(context)
        Handler(Looper.getMainLooper()).postDelayed({
            val estado = bdHelper.deleteAnimal(id)
            statusdell.postValue(estado)
        }, KEY_TIEMPO_DE_ESPERA)
    }

    fun saveAnimal(context: Context,animal: AnimalModel){
        bdHelper = AnimalSqlHelper(context)
        Handler(Looper.getMainLooper()).postDelayed({
            val estadosave = bdHelper.savAnimal(animal)
            statussave.postValue(estadosave)
        }, KEY_TIEMPO_DE_ESPERA)
    }
}