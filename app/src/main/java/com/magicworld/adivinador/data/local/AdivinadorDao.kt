package com.magicworld.adivinador.data.local

import androidx.room.*
import com.magicworld.adivinador.model.PuntajeMaxLocal

@Dao
interface AdivinadorDao {
    @Insert
    suspend fun createPuntajeMax(puntajeMaxLocal: PuntajeMaxLocal)

    @Query("SELECT * FROM adivnidor_table ORDER BY id ASC")
    fun loadScoreMax():PuntajeMaxLocal

    @Update
    suspend fun updateScoreMAx(puntajeMaxLocal: PuntajeMaxLocal)

    @Delete
    fun deletePuntajeMax(puntajeMaxLocal: PuntajeMaxLocal)

    @Query ("DELETE FROM Adivnidor_table")
    suspend fun deleteAllData()
}