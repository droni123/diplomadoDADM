package mx.com.idel.entregablefinal.model.entities

import java.io.Serializable

data class ConfiguracionModel(
    val id_config : Int ?= 0,
    val key : String,
    val value : String ? = null,
    val status : Int ?= 0
): Serializable {

}