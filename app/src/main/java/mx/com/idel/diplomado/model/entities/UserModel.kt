package mx.com.idel.diplomado.model.entities

import java.io.Serializable

data class UserModel(
    val nombre : String,
    val apellido : String,
    val edad : Int
): Serializable {
}