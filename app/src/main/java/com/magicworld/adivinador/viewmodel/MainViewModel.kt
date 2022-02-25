package com.magicworld.adivinador.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.adivinador.data.repository.AdivinadorRepository
import com.magicworld.adivinador.model.PuntajeMaxLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val adivinadorRepository = AdivinadorRepository()

    private var numAttempts: MutableLiveData<Int> = MutableLiveData()
    val onNumAttemptsDone: LiveData<Int> = numAttempts

    private var condition: MutableLiveData<String> = MutableLiveData()
    val onConditionDone: LiveData<String> = condition

    private var scoreMax: MutableLiveData<Int> = MutableLiveData()
    val onScoreMaxDone: LiveData<Int> = scoreMax

    private var scoreMAxload:MutableLiveData<PuntajeMaxLocal> = MutableLiveData()
    val scoreMaxLoadedDone: LiveData<PuntajeMaxLocal> = scoreMAxload


    private var nOneRamdon = (1..10).random()
    private var nTwoRamdon = (1..10).random()
    private var attempts = 0

    fun jugar(
        numberOne: Int,
        numberTwo: Int,
        score: Int
    ) {
        println(nOneRamdon)
        println(nTwoRamdon)
        numAttempts.value = attempts  + 1
        attempts  += 1

        if ((numberOne == nOneRamdon && numberTwo == nTwoRamdon) || (numberOne == nTwoRamdon && numberTwo == nOneRamdon)) {

            if (score > attempts ) {
                scoreMax.value = attempts
            }
            condition.value = "GANASTE LO HAS LOGRADO"
            nOneRamdon = (1..10).random()
            nTwoRamdon = (1..10).random()
            numAttempts.value = 0
            attempts = 0

        } else if(numberOne == nOneRamdon || numberOne == nTwoRamdon){
            condition.value = "Estuvo cerca"
            nOneRamdon = (1..10).random()
        }
        else if (numberTwo == nOneRamdon || numberTwo == nTwoRamdon) {
            condition.value = "Sigue intentando"
            nTwoRamdon = (1..10).random()
        } else {
            condition.value = " Fallaste"
            nOneRamdon = (1..10).random()
            nTwoRamdon = (1..10).random()
        }

    }
    fun saveScoreMax(puntajeMaxLocal: PuntajeMaxLocal) {
        viewModelScope.launch(Dispatchers.IO) {
            adivinadorRepository.saveScoreMax(puntajeMaxLocal)
        }
    }

    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            adivinadorRepository.deleteAllData()
        }
    }

    fun loadScoreMax() {
        viewModelScope.launch(Dispatchers.IO) {
            scoreMAxload.postValue(adivinadorRepository.loadScoreMax())
        }
    }

    fun updateScoreMax(puntajeMaxLocal: PuntajeMaxLocal) {
        viewModelScope.launch(Dispatchers.IO) {
            adivinadorRepository.updateScoreMax(puntajeMaxLocal)
        }
    }

}