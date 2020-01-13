package com.apps.alan.agenda.presentation.ui.nuevo_contacto_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mavi.core.data.Contacto
import com.apps.alan.agenda.repository.DatabaseRepository

class ContactoViewModel constructor(application: Application) : AndroidViewModel(application) {

    private var databaseRepo:DatabaseRepository

    init{
        databaseRepo = DatabaseRepository(application)
    }

    fun insertContacto(contacto: Contacto){
        databaseRepo.insertContacto(contacto)
    }

    fun updateContacto(contacto: Contacto, id:Int){
        databaseRepo.updateContacto(contacto.nombre, contacto.telefono, contacto.cumple, contacto.nota, id)
    }

    fun getContacto(id: Int):LiveData<Contacto>{
        return databaseRepo.selectContactobyId(id)
    }
}