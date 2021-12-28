package com.magicworld.adivinador.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    private val numAttempts: MutableLiveData<Int> = MutableLiveData()
    val onNumAttemptsDone: LiveData<Int> = numAttempts

    private val condition: MutableLiveData<String> = MutableLiveData()
    val onConditionDone: LiveData<String> = condition

    private val scoreMax: MutableLiveData<Int> = MutableLiveData()
    val onScoreMaxDone: LiveData<Int> = scoreMax

    var nOneRamdon = (1..10).random()
    var nTwoRamdon = (1..10).random()
    var Attempts = 0

    fun jugar(
        numberOne: Int,
        numberTwo: Int,
        score: Int
    ) {
        numAttempts.value = Attempts  + 1
        Attempts  += 1

        if ((numberOne == nOneRamdon && numberTwo == nTwoRamdon) || (numberOne == nTwoRamdon && numberTwo == nOneRamdon)) {

            if (score > Attempts ) {
                scoreMax.value = Attempts
            }
            condition.value = "GANASTE LO HAS LOGRADO"
            nOneRamdon = (1..10).random()
            nTwoRamdon = (1..10).random()
            numAttempts.value = 0
            Attempts = 0

        } else if(numberOne == nOneRamdon || numberOne == nTwoRamdon){
            condition.value = "Estuvo cerca"
        }
        else if (numberTwo == nOneRamdon || numberTwo == nTwoRamdon) {
            condition.value = "Sigue intentando"
        } else {
            condition.value = " Fallaste"
        }

    }
}