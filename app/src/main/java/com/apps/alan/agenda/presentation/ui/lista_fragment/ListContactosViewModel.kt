package com.apps.alan.agenda.presentation.ui.lista_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mavi.core.data.Contacto
import com.apps.alan.agenda.repository.DatabaseRepository

class ListContactosViewModel constructor(application: Application) : AndroidViewModel(application) {

    private var databaseRepo: DatabaseRepository
    private var allContactos:LiveData<List<Contacto>>

    init{
        databaseRepo = DatabaseRepository(application)
        allContactos = databaseRepo.getAllContactos()
    }

    fun getAllContactos():LiveData<List<Contacto>>{
        return allContactos
    }

    fun deleteContacto(id:Int){
        databaseRepo.deleteContacto(id)
    }
}