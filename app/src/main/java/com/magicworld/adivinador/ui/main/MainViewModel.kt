package com.magicworld.adivinador.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {


    private val numIntentos: MutableLiveData<Int> = MutableLiveData()
    val onNumIntentosDone: LiveData<Int> = numIntentos

    private val estado: MutableLiveData<String> = MutableLiveData()
    val onEstadoDone: LiveData<String> = estado

    private val puntajeMax: MutableLiveData<Int> = MutableLiveData()
    val onPuntajeMaxDone: LiveData<Int> = puntajeMax

    var nUnoAleatorio = (1..10).random()
    var nDOSAleatorio = (1..10).random()
    var intentos = 0

    fun jugar(
        numeroUno: Int,
        numberDos: Int,
        puntaje: Int
    ) {
        Log.d("valor", nUnoAleatorio.toString())
        Log.d("valor", nDOSAleatorio.toString())
        numIntentos.value = intentos + 1
        intentos += 1

        if ((numeroUno == nUnoAleatorio && numberDos == nDOSAleatorio) || (numeroUno == nDOSAleatorio && numberDos == nUnoAleatorio)) {

            if (puntaje > intentos) {
                puntajeMax.value = intentos
            }
            estado.value = "GANASTE LO HAS LOGRADO"
            nUnoAleatorio = (1..10).random()
            nDOSAleatorio = (1..10).random()
            numIntentos.value = 0
            intentos = 0

        } else if(numeroUno == nUnoAleatorio || numeroUno == nDOSAleatorio){
            estado.value = "Estuvo cerca"
        }
        else if (numberDos == nUnoAleatorio || numberDos == nDOSAleatorio) {
            estado.value = "Sigue intentando"
        } else {
            estado.value = " Fallaste"
        }

    }
}