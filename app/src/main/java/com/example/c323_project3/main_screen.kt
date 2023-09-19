package com.example.c323_project3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.navigation.findNavController
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [main_screen.newInstance] factory method to
 * create an instance of this fragment.
 */
class main_screen : Fragment() {

    var numQuestions = 1
    var difficulty = 1
    var operation = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_screen, container, false)
        val startButton = view.findViewById<Button>(R.id.startButton)
        val easy = view.findViewById<RadioButton>(R.id.easy)
        val medium = view.findViewById<RadioButton>(R.id.medium)
        val hard = view.findViewById<RadioButton>(R.id.hard)
        val add = view.findViewById<RadioButton>(R.id.add)
        val subtract = view.findViewById<RadioButton>(R.id.subtract)
        val multi = view.findViewById<RadioButton>(R.id.multiply)
        val div = view.findViewById<RadioButton>(R.id.divide)
        val lessButton = view.findViewById<Button>(R.id.lessButton)
        val moreButton = view.findViewById<Button>(R.id.moreButton)
        var tvNumQ = view.findViewById<TextView>(R.id.tvNumQ)

        startButton.setOnClickListener {
            if (easy.isChecked) {
                difficulty = 1
            }
            if (medium.isChecked) {
                difficulty = 2
            }
            if (hard.isChecked) {
                difficulty = 3
            }
            if (add.isChecked) {
                operation = 1
            }
            if (multi.isChecked) {
                operation = 2
            }
            if (div.isChecked) {
                operation = 3
            }
            if (subtract.isChecked) {
                operation = 4
            }
            view.findNavController()
                .navigate(R.id.start_click)
        }

        lessButton.setOnClickListener {
            if (numQuestions != 1) {
                numQuestions -= 1
            }
            tvNumQ.text = numQuestions.toString()
        }

        moreButton.setOnClickListener {
            numQuestions += 1
            tvNumQ.text = numQuestions.toString()
        }
        return view
    }
}