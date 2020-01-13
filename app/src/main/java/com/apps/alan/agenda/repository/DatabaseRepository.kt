package com.apps.alan.agenda.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.mavi.core.data.Contacto
import com.apps.alan.agenda.db.ContactoDAO
import com.apps.alan.agenda.db.database
import org.jetbrains.anko.doAsync

class DatabaseRepository constructor(application: Application) {

    private var contactoDao:ContactoDAO
    private var allContactos:LiveData<List<Contacto>>

    init{
        val database: database = database.getInstance(application)
        contactoDao = database.contactoDAO()
        allContactos = contactoDao.selectAllContactos()
    }

    fun insertContacto(contacto: Contacto){
        doAsync{
            contactoDao.insertContacto(contacto)
        }
    }

    fun getAllContactos():LiveData<List<Contacto>>{
        return allContactos
    }

    fun selectContactobyId(id:Int):LiveData<Contacto>{
        return contactoDao.selectContactoById(id)
    }

    fun updateContacto(nombre:String, telefono:Int, cumple:String, nota:String, id:Int){
        doAsync {
            contactoDao.updateContacto(nombre, telefono, cumple, nota, id)
        }
    }

    fun deleteContacto(id:Int){
        doAsync {
            contactoDao.deleteContacto(id)
        }
    }
}