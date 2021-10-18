package com.juanpapinzonc.concursopyr

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GanadoresDAO {
    @Insert
    fun crearGanadores(ganadores: Ganadores)

    @Query("  SELECT * FROM tabla_ganadores WHERE documento_Ganador LIKE :defInterfaceDAO")
    fun buscarGanadores(defInterfaceDAO: String) : Ganadores

    @Query("  SELECT * FROM tabla_ganadores WHERE cargar_Ganador LIKE :defInterfaceDAO")
    fun buscarGanadores2(defInterfaceDAO: Int) : Ganadores

    @Update
    fun actualizarGanadores(ganadores: Ganadores)
}