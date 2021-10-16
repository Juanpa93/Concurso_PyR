package com.juanpapinzonc.concursopyr

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Preguntas::class), version =1)
abstract class PreguntasDataBase : RoomDatabase() {

    abstract fun PreguntasDAO() : PreguntasDAO

}