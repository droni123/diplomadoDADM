package mx.com.idel.my.diplomado.ejerciciosclase.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqlHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "user.db"
        private const val TABLE_USER = "tlb_user"
        private const val ID = "id"
        private const val NOMBRE = "nombre"
        private const val DESCRIPCION = "descripcion"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sqlCreate = "CREATE TABLE ${TABLE_USER} ( ${ID} INTEGER PRIMARY KEY AUTOINCREMENT, ${NOMBRE} TEXT, ${DESCRIPCION} TEXT)"
        db?.execSQL(sqlCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val sqlUpdate = "DROP TABLE IF EXISTS ${TABLE_USER}"
        db?.execSQL(sqlUpdate)
        onCreate(db)
    }

    fun insert(user: userSqlModel): Long {
        val ddb = writableDatabase
        val contenido = ContentValues().apply {
            put(NOMBRE, user.name)
            put(DESCRIPCION, user.descripcion)
        }
        val resultado = ddb.insert(TABLE_USER, null, contenido)
        ddb.close()
        return resultado
    }
    fun getAllUsers():ArrayList<userSqlModel>{
        var usuarios = arrayListOf<userSqlModel>()
        var query = "SELECT * FROM ${TABLE_USER}"
        var ddb = readableDatabase
        val cursor : Cursor?
        try {
            cursor = ddb.rawQuery(query,null)
        } catch (e : Exception){
            e.printStackTrace()
            return usuarios
        }
        var rId : Int
        var rNombre : String
        var rDescrpcion : String
        with(cursor){
            while (moveToNext()){
            //REVISAR
                rId = getInt(cursor.getColumnIndexOrThrow(ID))
                rNombre = getString(cursor.getColumnIndexOrThrow(NOMBRE))
                rDescrpcion = getString(cursor.getColumnIndexOrThrow(DESCRIPCION))

                var usuario = userSqlModel(rId,rNombre,rDescrpcion)
                usuarios.add(usuario)
            }
        }
        cursor.close()
        return usuarios
    }
    fun updateUsuario(user : userSqlModel):Int{
        val ddb = writableDatabase
        val contenedorValores = ContentValues().apply {
            put(NOMBRE, user.name)
            put(DESCRIPCION, user.descripcion)
        }
        val resultado = ddb.update(TABLE_USER,contenedorValores,"${ID} = ${user.id}",null)
        ddb.close()
        return resultado
        //ddb.update(TABLE_USER,contenedorValores,"${ID} = ?", arrayOf("${user.id}"))
    }
    fun deleteUser(id:Int):Int{
        val ddb = writableDatabase
        val resultado = ddb.delete(TABLE_USER,"${ID} = ?", arrayOf("${id}"))
        ddb.close()
        return resultado
    }
}