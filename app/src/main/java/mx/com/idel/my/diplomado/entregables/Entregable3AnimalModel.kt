package mx.com.idel.my.diplomado.entregables

import java.io.Serializable

data class Entregable3AnimalModel(
    var id:Int = 0,
    var nombre:String = "Nombre de animal",
    var imagen:String = "https://concepto.de/wp-content/uploads/2022/05/animales-e1653765030720.jpg",
    var dueno:String = "Pancho pantera",
    var genero:Boolean = false,
    var enfermo:Boolean = false,
    var descripcion:String = "Esta es una descripción de ejemplo"
): Serializable {
}