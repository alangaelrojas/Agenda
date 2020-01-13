package com.mavi.core.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apps.alan.agenda.utils.Constants

@Entity(tableName = Constants.TABLE_CONTACTO)
data class Contacto(



    @ColumnInfo(name = "nombre")
    var nombre:String,

    @ColumnInfo(name = "telefono")
    var telefono:Int,

    @ColumnInfo(name = "cumple")
    var cumple:String,

    @ColumnInfo(name = "nota")
    var nota:String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int = 0
}