package com.magicworld.adivinador.ui.main

import android.icu.lang.UCharacter
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magicworld.adivinador.databinding.MainFragmentBinding


class MainFragment : Fragment() {

    private lateinit var mainBinding : MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainBinding = MainFragmentBinding.inflate(inflater,container,false)
        return mainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        with(mainBinding){
            buttonJugar.setOnClickListener {
                if (numberOneEditText.text.toString() == "")
                    numberOneEditText.error= "Escriba un número"
                if (numberTwoEditText.text.toString()=="")
                    numberTwoEditText.error= "Escriba un número"
                else {
                    viewModel.jugar(
                        numberOneEditText.text.toString().toInt(),
                        numberTwoEditText.text.toString().toInt(),
                        puntajeTextView.text.toString().toInt()
                    )
                }
            }
        }
        viewModel.onEstadoDone.observe(viewLifecycleOwner,{result ->
            onEstadoDoneSubscribe(result)
        })
        viewModel.onNumIntentosDone.observe(viewLifecycleOwner,{result->
            onNumIntentosDoneSubscribe(result)
        })

        viewModel.onPuntajeMaxDone.observe(viewLifecycleOwner,{result->
            onPuntajeMaxDoneSubscribe(result)
        })

    }

    private fun onNumIntentosDoneSubscribe(result: Int) {
        mainBinding.intentosTextView.text = result.toString()
    }

    private fun onPuntajeMaxDoneSubscribe(result: Int) {
        mainBinding.puntajeTextView.text = result.toString()
    }

    private fun onEstadoDoneSubscribe(result: String?) {
        mainBinding.estadoJuego.text = result
    }

}