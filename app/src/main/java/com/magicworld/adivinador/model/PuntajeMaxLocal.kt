package com.magicworld.adivinador.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Adivnidor_table")
data class PuntajeMaxLocal (
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") val id : Int,
    @ColumnInfo(name = "puntaje") val puntaje: Int = 0
    )