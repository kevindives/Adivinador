package com.magicworld.adivinador.data.repository

import com.magicworld.adivinador.Adivinador
import com.magicworld.adivinador.data.local.AdivinadorDao
import com.magicworld.adivinador.model.PuntajeMaxLocal

class AdivinadorRepository {

    private val adivinadorDao:AdivinadorDao = Adivinador.database.AdivinadorDao()

    suspend fun saveScoreMax(puntajeMaxLocal: PuntajeMaxLocal) {
        adivinadorDao.createPuntajeMax(puntajeMaxLocal)
    }

    fun loadScoreMax():PuntajeMaxLocal {
       return adivinadorDao.loadScoreMax()
    }

    suspend fun updateScoreMax(puntajeMaxLocal: PuntajeMaxLocal) {
        adivinadorDao.updateScoreMAx(puntajeMaxLocal)
    }

    suspend fun deleteAllData() {
        adivinadorDao.deleteAllData()
    }

}