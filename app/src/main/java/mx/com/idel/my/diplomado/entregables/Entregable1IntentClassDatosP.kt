package mx.com.idel.my.diplomado.entregables

import java.io.Serializable

data class Entregable1IntentClassDatosP (
    val nombre : String,
    val apellido1 : String,
    val apellido2 : String,
    val edad : String,
    var tel : String,
    var correo : String
): Serializable {

}