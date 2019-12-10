package com.apps.alan.agenda.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.apps.alan.agenda.utils.Constants

@Database(entities = arrayOf(Contacto::class), version = 1, exportSchema = false)
abstract class database : RoomDatabase() {
    abstract fun contactoDAO():ContactoDAO

    companion object{
        private var INSTANCE:database? = null

        fun getInstance(context: Context):database{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext, database::class.java, Constants.DB_CONTACTO)
                    .build();
            }
            return INSTANCE as database
        }
    }
}