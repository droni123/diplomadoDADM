package mx.com.idel.entregablefinal.model.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import mx.com.idel.entregablefinal.model.entities.AnimalModel
import mx.com.idel.entregablefinal.model.entities.ConfiguracionModel

class AnimalSqlHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "datosanimales.db"
    }
    object AnimalBD : BaseColumns {
        const val TABLE_NAME = "animales"
        const val ID = "id"
        const val NOMBRE = "nombre"
        const val IMAGEN = "imagen"
        const val DUENO = "dueno"
        const val PESO = "peso"
        const val GENERO = "genero"
        const val ENFERMO = "enfermo"
        const val DESCRIPCION = "descripcion"
    }
    object ConfigBD : BaseColumns {
        const val TABLE_NAME = "configuracion"
        const val ID = "id_config"
        const val KEY = "key"
        const val VALUE = "value"
        const val STATUS = "status"
    }
    override fun onCreate(database: SQLiteDatabase?) {
        val sqlCreateAnimal =
            "CREATE TABLE ${AnimalBD.TABLE_NAME} ("+
                "${AnimalBD.ID} INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "${AnimalBD.NOMBRE} TEXT,"+
                "${AnimalBD.IMAGEN} TEXT," +
                "${AnimalBD.DUENO} TEXT," +
                "${AnimalBD.PESO} REAL," +
                "${AnimalBD.GENERO} BOOLEAN," +
                "${AnimalBD.ENFERMO} BOOLEAN," +
                "${AnimalBD.DESCRIPCION} TEXT"+
            ")"

        val sqlCreatePerfil =
            "CREATE TABLE ${ConfigBD.TABLE_NAME} ("+
                "${ConfigBD.ID} INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "${ConfigBD.KEY} TEXT NOT NULL UNIQUE,"+
                "${ConfigBD.VALUE} TEXT,"+
                "${ConfigBD.STATUS} INTEGER DEFAULT 1 NOT NULL" +
            ")"
        database?.execSQL(sqlCreateAnimal)
        database?.execSQL(sqlCreatePerfil)
    }
    override fun onUpgrade(database: SQLiteDatabase?, p1: Int, p2: Int) {
        //DROP TABLA ANIMAL
        val sqlUpdateAnimal = "DROP TABLE IF EXISTS ${AnimalBD.TABLE_NAME}"
        //DROP PERFIL
        val sqlUpdatePerfil = "DROP TABLE IF EXISTS ${ConfigBD.TABLE_NAME}"
        database?.execSQL(sqlUpdateAnimal)
        database?.execSQL(sqlUpdatePerfil)
        onCreate(database)
    }
    fun savAnimal(data : AnimalModel):Int{
        val resultado: Int
        if(data.id != 0){
            resultado = updateAnimal(data)
        }else{
            resultado = insertAnimal(data).toInt()
        }
        return resultado
    }
    fun insertAnimal(animal: AnimalModel): Long {
        val db = writableDatabase
        val contenido = ContentValues().apply {
            put(AnimalBD.NOMBRE, animal.nombre)
            put(AnimalBD.IMAGEN, animal.imagen)
            put(AnimalBD.DUENO, animal.dueno)
            put(AnimalBD.PESO, animal.peso)
            put(AnimalBD.GENERO, animal.genero)
            put(AnimalBD.ENFERMO, animal.enfermo)
            put(AnimalBD.DESCRIPCION, animal.descripcion)
        }
        val resultado = db.insert(AnimalBD.TABLE_NAME, null, contenido)
        db.close()
        return resultado
    }
    fun updateAnimal(animal : AnimalModel):Int{
        val db = writableDatabase
        val contenido = ContentValues().apply {
            put(AnimalBD.NOMBRE, animal.nombre)
            put(AnimalBD.IMAGEN, animal.imagen)
            put(AnimalBD.DUENO, animal.dueno)
            put(AnimalBD.PESO, animal.peso)
            put(AnimalBD.GENERO, animal.genero)
            put(AnimalBD.ENFERMO, animal.enfermo)
            put(AnimalBD.DESCRIPCION, animal.descripcion)
        }
        val resultado = db.update(AnimalBD.TABLE_NAME,contenido,"${AnimalBD.ID} = ?",arrayOf("${animal.id}"))
        db.close()
        return resultado
    }
    fun deleteAnimal(id:Int):Int{
        val db = writableDatabase
        val resultado = db.delete(AnimalBD.TABLE_NAME,"${AnimalBD.ID} = ?", arrayOf("$id"))
        db.close()
        return resultado
    }
    fun getAllAnimal():ArrayList<AnimalModel>{
        val animales = ArrayList<AnimalModel>()
        val query = "SELECT * FROM ${AnimalBD.TABLE_NAME}"
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
        var rPeso:Double
        var rGenero:Boolean
        var rEnfermo:Boolean
        var rDescripcion:String
        with(cursor){
            while (moveToNext()){
                //REVISAR
                rId = getInt(getColumnIndexOrThrow(AnimalBD.ID))
                rNombre = getString(getColumnIndexOrThrow(AnimalBD.NOMBRE))
                rImagen = getString(getColumnIndexOrThrow(AnimalBD.IMAGEN))
                rDueno = getString(getColumnIndexOrThrow(AnimalBD.DUENO))
                rPeso = getDouble(getColumnIndexOrThrow(AnimalBD.PESO))
                rGenero = getInt(getColumnIndexOrThrow(AnimalBD.GENERO)) != 0
                rEnfermo = getInt(getColumnIndexOrThrow(AnimalBD.ENFERMO)) != 0
                rDescripcion = getString(getColumnIndexOrThrow(AnimalBD.DESCRIPCION))
                val animal = AnimalModel(rId,rNombre,rImagen,rDueno,rPeso,rGenero,rEnfermo,rDescripcion)
                animales.add(animal)
            }
        }
        cursor.close()
        db.close()
        return animales
    }
    /////CONFIGURACION
    fun savConfig(data : ConfiguracionModel):Int{
        val resultado: Int
        val buscando = getConfig(data.key,true)
        if(buscando.id_config != 0){
            resultado = updateConfig(data)
        }else{
            resultado = insertConfig(data).toInt()
        }
        return resultado
    }
    fun insertConfig(data : ConfiguracionModel): Long{
        val db = writableDatabase
        val contenido = ContentValues().apply {
            put(ConfigBD.KEY, data.key)
            put(ConfigBD.VALUE, data.value)
            put(ConfigBD.STATUS, 1)
        }
        val resultado = db.insert(ConfigBD.TABLE_NAME, null, contenido)
        db.close()
        return resultado
    }
    fun updateConfig(data : ConfiguracionModel): Int{
        val db = writableDatabase
        val contenido = ContentValues().apply {
            put(ConfigBD.VALUE, data.value)
            put(ConfigBD.STATUS, 1)
        }
        val resultado = db.update(ConfigBD.TABLE_NAME,contenido,"${ConfigBD.KEY} = ?",arrayOf(data.key))
        db.close()
        return resultado
    }
    fun delConfig(key:String):Int{
        val db = writableDatabase
        val contenido = ContentValues().apply {
            put(ConfigBD.STATUS, 0)
        }
        val resultado = db.update(ConfigBD.TABLE_NAME,contenido,"${ConfigBD.KEY} = ?",arrayOf(key))
        db.close()
        return resultado
    }
    fun getConfig(key:String,showAll:Boolean=false):ConfiguracionModel{
        var resultado = ConfiguracionModel(key=key)
        var query = ""
            query += "SELECT * FROM ${ConfigBD.TABLE_NAME} WHERE ${ConfigBD.KEY} = ?"
        if(!showAll){ query += " and ${ConfigBD.STATUS} = 1" }
        val db = readableDatabase
        val cursor : Cursor?
        try {
            cursor = db.rawQuery(query, arrayOf(key))
        } catch (e : Exception){
            e.printStackTrace()
            return resultado
        }
        var rid_config:Int
        var rkey:String
        var rvalue:String
        var rstatus:Int
        with(cursor){
            while (moveToNext()){
                rid_config = getInt(getColumnIndexOrThrow(ConfigBD.ID))
                rkey = getString(getColumnIndexOrThrow(ConfigBD.KEY))
                rvalue = getString(getColumnIndexOrThrow(ConfigBD.VALUE))
                rstatus = getInt(getColumnIndexOrThrow(ConfigBD.STATUS))
                resultado = ConfiguracionModel(rid_config,rkey,rvalue,rstatus)
            }
        }
        cursor.close()
        db.close()
        return resultado
    }
    fun getAllConfig(): ArrayList<ConfiguracionModel> {
        val resultado = arrayListOf<ConfiguracionModel>()
        val query = "SELECT * FROM ${ConfigBD.TABLE_NAME} WHERE ${ConfigBD.STATUS} = 1"
        val db = readableDatabase
        val cursor : Cursor?
        try {
            cursor = db.rawQuery(query,null)
        } catch (e : Exception){
            e.printStackTrace()
            return resultado
        }
        var rid_config:Int
        var rkey:String
        var rvalue:String
        var rstatus:Int
        with(cursor){
            while (moveToNext()){
                //REVISAR
                rid_config = getInt(getColumnIndexOrThrow(ConfigBD.ID))
                rkey = getString(getColumnIndexOrThrow(ConfigBD.KEY))
                rvalue = getString(getColumnIndexOrThrow(ConfigBD.VALUE))
                rstatus = getInt(getColumnIndexOrThrow(ConfigBD.STATUS))
                val con = ConfiguracionModel(rid_config,rkey,rvalue,rstatus)
                resultado.add(con)
            }
        }
        cursor.close()
        db.close()
        return resultado
    }
}