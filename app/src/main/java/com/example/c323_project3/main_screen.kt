package com.example.c323_project3

import android.graphics.Color
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
import kotlin.properties.Delegates

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
    // declare vars that need to be changed based on user input & passed using SafeArgs
    var numQuestions = 1
    var difficulty = 1
    var operation = 1
    var restarted = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_screen, container, false)

        /* initialize user input features, including start button, all radio buttons, less/more
        button and TextView that changes based on the less/more button
         */
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
        var tvInfo = view.findViewById<TextView>(R.id.tvInfo)

        // var checks if main_screen has been reopened or not, false by default
        restarted = main_screenArgs.fromBundle(requireArguments()).restart
        // if it has restarted, obtain the values from question_screen
        if (restarted) {
            val correctNum = main_screenArgs.fromBundle(requireArguments()).numCorrect
            val totalQuestions = main_screenArgs.fromBundle(requireArguments()).numQuestions
            // if user has 80% or better score, change the tvInfo to the correct text
            if ((correctNum / totalQuestions) >= .8) {
                when (operation) {
                    1 ->
                        tvInfo.text =
                            "You got $correctNum out of $totalQuestions correct in addition. Good Work!"

                    2 ->
                        tvInfo.text =
                            "You got $correctNum out of $totalQuestions correct in multiplication. Good Work!"

                    3 ->
                        tvInfo.text =
                            "You got $correctNum out of $totalQuestions correct in division. Good Work!"

                    4 ->
                        tvInfo.text =
                            "You got $correctNum out of $totalQuestions correct in subtraction. Good Work!"
                }
            } else {
                // else change textColor to red and then set the tvInfo text to the appropriate text
                tvInfo.setTextColor(Color.parseColor("#db1760"))
                when (operation) {
                    1 ->
                        tvInfo.text =
                            "You got $correctNum out of $totalQuestions correct in addition. You need to practice more!"

                    2 ->
                        tvInfo.text =
                            "You got $correctNum out of $totalQuestions correct in multiplication. You need to practice more!"

                    3 ->
                        tvInfo.text =
                            "You got $correctNum out of $totalQuestions correct in division. You need to practice more!"

                    4 ->
                        tvInfo.text =
                            "You got $correctNum out of $totalQuestions correct in subtraction. You need to practice more!"
                }
            }
        }

        /* onClickListener for less button, as long as numQuestions is more than 1, it will reduce
        numQuestions by 1 and set tvNumQ's text to the new numQuestions
         */
        lessButton.setOnClickListener {
            if (numQuestions != 1) {
                numQuestions -= 1
            }
            tvNumQ.text = numQuestions.toString()
        }

        // onClickListener for more button, will add 1 to numQuestions and update tvNumQ's text
        moreButton.setOnClickListener {
            numQuestions += 1
            tvNumQ.text = numQuestions.toString()
        }

        /* onClickListener for startButton, based on what RadioButton is checked, sets difficulty
         * and operation equal to the necessary int value.
         */
        startButton.setOnClickListener {
            restarted = true
            Log.v("restart?", restarted.toString())
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

            /* declare action that passes difficulty, operation, and numQuestions to question_screen
             * using SafeArgs
             */
            val action = main_screenDirections.startClick(difficulty, operation, numQuestions)
            view.findNavController()
                .navigate(action)
        }
        return view
    }
}