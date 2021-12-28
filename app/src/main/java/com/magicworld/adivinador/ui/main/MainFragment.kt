package com.magicworld.adivinador.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.magicworld.adivinador.databinding.MainFragmentBinding


class MainFragment : Fragment() {


    private lateinit var mainBinding : MainFragmentBinding
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
                        scoreTextView.text.toString().toInt()
                    )
                }
            }
            rulesTextView.setOnClickListener{
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToRulesFragment())
            }
        }
        viewModel.onConditionDone.observe(viewLifecycleOwner,{result ->
            onConditionDoneSubscribe(result)
        })
        viewModel.onNumAttemptsDone.observe(viewLifecycleOwner,{result->
            onNumAttemptsDoneSubscribe(result)
        })

        viewModel.onScoreMaxDone.observe(viewLifecycleOwner,{result->
            onScoreMaxDoneSubscribe(result)
        })

    }

    private fun onNumAttemptsDoneSubscribe(result: Int) {
        mainBinding.attemptsTextView.text = result.toString()
    }

    private fun onScoreMaxDoneSubscribe(result: Int) {
        mainBinding.scoreTextView.text = result.toString()
    }

    private fun onConditionDoneSubscribe(result: String?) {
        mainBinding.conditionGameTextView.text = result
    }

}