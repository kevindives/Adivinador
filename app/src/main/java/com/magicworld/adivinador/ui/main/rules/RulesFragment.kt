package com.magicworld.adivinador.ui.main.rules

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.magicworld.adivinador.R
import com.magicworld.adivinador.viewmodel.MainViewModel


class RulesFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rules, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_menu) {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                viewModel.deleteAllData()
                Toast.makeText(context, "Successfully removed ", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete Maximum Score? ")
            builder.setMessage("Are you sure you want to delete Score Data?")
            builder.create().show()
        }
        return super.onOptionsItemSelected(item)
    }
}