package com.juanpapinzonc.concursopyr

import android.app.Application
import androidx.room.Room

class ConcursoPyR : Application() {
    companion object{

lateinit var database : PreguntasDataBase
lateinit var database2 : GanadoresDataBase
}

    override fun onCreate() {
        super.onCreate()

    database = Room.databaseBuilder(
            this,
            PreguntasDataBase::class.java,
            "mispreguntas_db")
            .allowMainThreadQueries()
            .build()
        database2 = Room.databaseBuilder(
            this,
            GanadoresDataBase::class.java,
            "misganadores_db")
            .allowMainThreadQueries()
            .build()
    }
}