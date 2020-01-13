package com.apps.alan.agenda.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mavi.core.data.Contacto

@Dao
interface ContactoDAO {

    @Insert
    fun insertContacto(contacto: Contacto)

    @Query("SELECT * FROM contactos")
    fun selectAllContactos():LiveData<List<Contacto>>

    @Query("SELECT * FROM contactos WHERE id = :id")
    fun selectContactoById(id:Int):LiveData<Contacto>

    @Query("UPDATE contactos SET nombre = :nombre, telefono = :telefono, cumple = :cumple, nota = :nota WHERE id = :id")
    fun updateContacto(nombre:String, telefono:Int, cumple:String, nota:String, id:Int)

    @Query("DELETE FROM contactos WHERE id = :id")
    fun deleteContacto(id:Int)

}