package com.juanpapinzonc.concursopyr

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Ganadores::class), version =1)
abstract class GanadoresDataBase : RoomDatabase() {

    abstract fun GanadoresDAO() : GanadoresDAO

}