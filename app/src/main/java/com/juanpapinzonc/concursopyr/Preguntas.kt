package com.juanpapinzonc.concursopyr

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="tabla_preguntas")
class Preguntas(
    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name ="id") val id: Int,
    @ColumnInfo(name ="numeroPregunta") val numeroPregunta: String,
    @ColumnInfo(name ="pregunta") val pregunta: String = "",
    @ColumnInfo(name ="opcion_uno") val opcionUno: String="",
    @ColumnInfo(name ="opcion_dos") val opcionDos: String = "",
    @ColumnInfo(name ="opcion_tres") val opcionTres: String="",
    @ColumnInfo(name ="opcion_cuatro") val opcionCuatro: String = "",
    @ColumnInfo(name ="opcion_correcta") val opcionCorrecta: String = ""


)
