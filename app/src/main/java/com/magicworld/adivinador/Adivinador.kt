package com.magicworld.adivinador

import android.app.Application
import androidx.room.Room
import com.magicworld.adivinador.data.local.AdivinadorDatabase

class Adivinador: Application() {
    companion object{
        lateinit var database: AdivinadorDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database= Room.databaseBuilder(
            this,
            AdivinadorDatabase::class.java,
            "adivinador_db"
        ).build()
    }

}