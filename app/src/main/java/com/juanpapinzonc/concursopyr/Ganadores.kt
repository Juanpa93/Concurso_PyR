package com.juanpapinzonc.concursopyr

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="tabla_ganadores")
class Ganadores (
    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name ="id") val id: Int,
    @ColumnInfo(name ="nombre_Ganador") val nombreGanador: String,
    @ColumnInfo(name ="documento_Ganador") val documentoGanador: String,
    @ColumnInfo(name ="premio_Ganador") val premioGanador: String,
    @ColumnInfo(name ="Es_campeon?") val esCampeon: Boolean = false,
    @ColumnInfo(name ="cargar_Ganador") val cargarGanador: Int = 0
        )