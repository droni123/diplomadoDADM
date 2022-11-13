package mx.com.idel.my.diplomado.entregables

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Entregable3AnimalSqlHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "entregable.db"
        private const val TABLE_ANIMAL = "animales"
        private const val ID = "id"
        private const val NOMBRE = "nombre"
        private const val IMAGEN = "imagen"
        private const val DUENO = "dueno"
        private const val GENERO = "genero"
        private const val ENFERMO = "enfermo"
        private const val DESCRIPCION = "descripcion"
    }
    override fun onCreate(database: SQLiteDatabase?) {
        val sqlCreate = "CREATE TABLE ${TABLE_ANIMAL} ( ${ID} INTEGER PRIMARY KEY AUTOINCREMENT, ${NOMBRE} TEXT, ${IMAGEN} TEXT, ${DUENO} TEXT, ${GENERO} BOOLEAN, ${ENFERMO} BOOLEAN, ${DESCRIPCION} TEXT)"
        database?.execSQL(sqlCreate)
    }
    override fun onUpgrade(database: SQLiteDatabase?, p1: Int, p2: Int) {
        val sqlUpdate = "DROP TABLE IF EXISTS ${TABLE_ANIMAL}"
        database?.execSQL(sqlUpdate)
        onCreate(database)
    }
    fun insertAnimal(animal: Entregable3AnimalModel): Long {
        val db = writableDatabase
        val contenido = ContentValues().apply {
            put(NOMBRE, animal.nombre)
            put(IMAGEN, animal.imagen)
            put(DUENO, animal.dueno)
            put(GENERO, animal.genero)
            put(ENFERMO, animal.enfermo)
            put(DESCRIPCION, animal.descripcion)
        }
        val resultado = db.insert(TABLE_ANIMAL, null, contenido)
        db.close()
        return resultado
    }
    fun updateAnimal(animal : Entregable3AnimalModel):Int{
        val db = writableDatabase
        val contenido = ContentValues().apply {
            put(NOMBRE, animal.nombre)
            put(IMAGEN, animal.imagen)
            put(DUENO, animal.dueno)
            put(GENERO, animal.genero)
            put(ENFERMO, animal.enfermo)
            put(DESCRIPCION, animal.descripcion)
        }
        val resultado = db.update(TABLE_ANIMAL,contenido,"${ID} = ?",arrayOf("${animal.id}"))
        db.close()
        return resultado
    }
    fun deleteAnimal(id:Int):Int{
        val db = writableDatabase
        val resultado = db.delete(TABLE_ANIMAL,"${ID} = ?", arrayOf("${id}"))
        db.close()
        return resultado
    }
    fun getAllAnimal():ArrayList<Entregable3AnimalModel>{
        val animales = arrayListOf<Entregable3AnimalModel>()
        val query = "SELECT * FROM ${TABLE_ANIMAL}"
        val db = readableDatabase
        val cursor : Cursor?
        try {
            cursor = db.rawQuery(query,null)
        } catch (e : Exception){
            e.printStackTrace()
            return animales
        }
        var rId:Int
        var rNombre:String
        var rImagen:String
        var rDueno:String
        var rGenero:Boolean
        var rEnfermo:Boolean
        var rDescripcion:String
        with(cursor){
            while (moveToNext()){
                //REVISAR
                rId = getInt(getColumnIndexOrThrow(ID))
                rNombre = getString(getColumnIndexOrThrow(NOMBRE))
                rImagen = getString(getColumnIndexOrThrow(IMAGEN))
                rDueno = getString(getColumnIndexOrThrow(DUENO))
                rGenero = getInt(getColumnIndexOrThrow(GENERO)) != 0
                rEnfermo = getInt(getColumnIndexOrThrow(ENFERMO)) != 0
                rDescripcion = getString(getColumnIndexOrThrow(DESCRIPCION))
                val animal = Entregable3AnimalModel(rId,rNombre,rImagen,rDueno,rGenero,rEnfermo,rDescripcion)
                animales.add(animal)
            }
        }
        cursor.close()
        return animales
    }
}