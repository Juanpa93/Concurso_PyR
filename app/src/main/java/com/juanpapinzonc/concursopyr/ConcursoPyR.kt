package com.juanpapinzonc.concursopyr

import android.app.Application
import androidx.room.Room

class ConcursoPyR : Application() {
    companion object{

lateinit var database : PreguntasDataBase
}

    override fun onCreate() {
        super.onCreate()

    database = Room.databaseBuilder(
            this,
            PreguntasDataBase::class.java,
            "mispreguntas_db")
            .allowMainThreadQueries()
            .build()
    }
}