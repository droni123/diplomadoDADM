package mx.com.idel.my.diplomado.ejerciciosclase

import java.io.Serializable

data class Usuario (
    val name : String,
    val apellido : String,
    val edad : Int
): Serializable {

}