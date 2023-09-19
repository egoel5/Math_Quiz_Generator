package com.example.c323_project3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [question_screen.newInstance] factory method to
 * create an instance of this fragment.
 */
class question_screen : Fragment() {

    lateinit var firstNum : TextView
    lateinit var oper : TextView
    lateinit var secondNum : TextView

    var num1 = 0
    var num2 = 0
    var correctNum = 0

    var questionsDone = 0
    var questions = 3
    val difficulty = 1
    val operation = 1
    var correct = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_question_screen, container, false)
        val doneButton = view.findViewById<Button>(R.id.doneButton)
        val userInput = view.findViewById<EditText>(R.id.userInput)
        firstNum = view.findViewById(R.id.firstNum)
        secondNum = view.findViewById(R.id.secondNum)
        oper = view.findViewById(R.id.operation)
        genNums()
        doMath()
        doneButton.setOnClickListener{
            questionsDone++
            if (questionsDone == questions) {
                view.findNavController()
                    .navigate(R.id.action_question_screen_to_result_screen)
            }
            else {
                if (userInput.text.equals(correctNum.toString())) {
                    correct++
                }
                userInput.setText("")
                genNums()
                doMath()
            }
        }
        return view
    }

    fun genNums() {
        if (difficulty == 1) {
            num1 = (1..9).random()
            firstNum.text = num1.toString()
            num2 = (1..9).random()
            secondNum.text = num2.toString()
        }
        else if (difficulty == 2) {
            num1 = (1..24).random()
            firstNum.text = num1.toString()
            num2 = (1..24).random()
            secondNum.text = num2.toString()
        }
        else if (difficulty == 3) {
            num1 = (1..49).random()
            firstNum.text = num1.toString()
            num2 = (1..49).random()
            secondNum.text = num2.toString()
        }
    }

    fun doMath() {
        if (operation == 1) {
            correctNum = num1 + num2
        }
        else if (operation == 2) {
            correctNum = num1 * num2
        }
        else if (operation == 3) {
            correctNum = num1 / num2
        }
        else if (operation == 4) {
            correctNum = num1 - num2
        }
    }

}