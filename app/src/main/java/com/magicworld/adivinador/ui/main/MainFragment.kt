package com.magicworld.adivinador.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.magicworld.adivinador.R
import com.magicworld.adivinador.databinding.MainFragmentBinding
import com.magicworld.adivinador.model.PuntajeMaxLocal
import com.magicworld.adivinador.viewmodel.MainViewModel


class MainFragment : Fragment() {


    private lateinit var mainBinding : MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private var idScore = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainBinding = MainFragmentBinding.inflate(inflater,container,false)
        return mainBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.rules_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.rules_menu)
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToRulesFragment())
        return super.onOptionsItemSelected(item)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel.loadScoreMax()

        with(mainBinding){

            buttonJugar.setOnClickListener {
                if (numberOneEditText.text.isEmpty() || numberTwoEditText.text.isEmpty())
                    numberOneEditText.error= "Escriba un número"
                else {
                    viewModel.jugar(
                        numberOneEditText.text.toString().toInt(),
                        numberTwoEditText.text.toString().toInt(),
                        scoreTextView.text.toString().toInt()
                    )
                }
                numberOneEditText.setText("")
                numberTwoEditText.setText("")
            }


        }
        viewModel.onConditionDone.observe(viewLifecycleOwner) { result ->
            onConditionDoneSubscribe(result)
        }
        viewModel.onNumAttemptsDone.observe(viewLifecycleOwner) { result ->
            onNumAttemptsDoneSubscribe(result)
        }

        viewModel.onScoreMaxDone.observe(viewLifecycleOwner) { result ->
            onScoreMaxDoneSubscribe(result)
        }
        viewModel.scoreMaxLoadedDone.observe(viewLifecycleOwner){result ->
            onScoreMaxLoadedDOneSubscribe(result)
        }

    }

    override fun onStop() {
        overWriteData()
        super.onStop()
    }

    private fun onScoreMaxLoadedDOneSubscribe(result: PuntajeMaxLocal?) {

        if (result == null){
            crearPuntajeMax()
            viewModel.loadScoreMax()
        }else{
            idScore =result.id
            mainBinding.scoreTextView.text = result.puntaje.toString()
        }
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

    private fun crearPuntajeMax() {
        val puntajeMaxLocal = PuntajeMaxLocal(0,30)
        viewModel.saveScoreMax(puntajeMaxLocal)
    }

    private fun overWriteData(){
        val puntajeMax= mainBinding.scoreTextView.text.toString().toInt()
        val newScoreMax = PuntajeMaxLocal(idScore,puntajeMax)
        viewModel.updateScoreMax(newScoreMax)
    }

}