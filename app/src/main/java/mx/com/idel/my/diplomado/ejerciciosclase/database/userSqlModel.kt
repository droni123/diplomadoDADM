package mx.com.idel.my.diplomado.ejerciciosclase.database

import java.io.Serializable

data class userSqlModel(
    val id: Int = 0,
    val name : String = "NOMBRE DEMO",
    val descripcion : String = "DESCRIPCION DEMO"
): Serializable {

}