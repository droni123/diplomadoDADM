package mx.com.idel.my.diplomado.entregables

import java.io.Serializable

data class Entregable2PetItem (
    var nombre:String,
    var imagen:String,
    var dueno:String,
    var genero:String,
    var enfermo:Boolean,
    var descripcion:String
): Serializable {

}