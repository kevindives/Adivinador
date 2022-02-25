package com.magicworld.adivinador.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.magicworld.adivinador.model.PuntajeMaxLocal

@Database(entities = [PuntajeMaxLocal::class], version = 1)
abstract class AdivinadorDatabase: RoomDatabase() {
    abstract fun AdivinadorDao():AdivinadorDao
}