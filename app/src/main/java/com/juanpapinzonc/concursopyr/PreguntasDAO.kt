package com.juanpapinzonc.concursopyr

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PreguntasDAO {
    @Insert
    fun crearPreguntas(preguntas: Preguntas)

    @Query("  SELECT * FROM tabla_preguntas WHERE numeroPregunta LIKE :defInterfaceDAO")
    fun buscarPreguntas(defInterfaceDAO: String) : Preguntas

    @Update
    fun actualizarPreguntas(preguntas: Preguntas)
}