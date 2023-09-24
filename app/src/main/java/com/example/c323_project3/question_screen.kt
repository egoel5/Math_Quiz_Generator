package com.example.c323_project3

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.findNavController
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

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
    // declare TextViews that need to be changed later
    lateinit var firstNum : TextView
    lateinit var oper : TextView
    lateinit var secondNum : TextView

    // initialize num variables that will change every question
    var num1 = 0
    var num2 = 0
    var correctNum = 0
    var correctNumDec = 0.0
    var questionsDone = 0

    // initialize vars that will be populated by SafeArgs
    var correct = 0
    var questions = 1
    var difficulty = 1
    var operation = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_question_screen, container, false)
        // initialize button and EditText that user will input in
        val doneButton = view.findViewById<Button>(R.id.doneButton)
        val userInput = view.findViewById<EditText>(R.id.userInput)

        // Populate these values using SafeArgs from main_screen
        difficulty = question_screenArgs.fromBundle(requireArguments()).difficulty
        operation = question_screenArgs.fromBundle(requireArguments()).operation
        questions = question_screenArgs.fromBundle(requireArguments()).numOfQuestions

        // initialize TextViews that need to be changed
        firstNum = view.findViewById(R.id.firstNum)
        secondNum = view.findViewById(R.id.secondNum)
        oper = view.findViewById(R.id.operation)

        // method call to getNums() and doMath() on the initial creation of the Fragment
        genNums()
        doMath()

        /* onClickListener for the done button that either sends user to next screen or reloads
        another question */
        doneButton.setOnClickListener{
            // add 1 to var that stores number of questions user has answered
            questionsDone++

            /* if questionsDone == # of questions user chose, check if last answer was correct,
            and then initialize an action that passes on required values to result_screen
            and then navigate with that action.
             */
            if (questionsDone == questions) {
                if (userInput.text.toString() == ((correctNum.toString()))) {
                    correct++
                    showToast("Correct. Good Work!")
                    var mediaPlayer = MediaPlayer.create(context, R.raw.correct)
                    mediaPlayer.start()
                }
                else {
                    showToast("Wrong.")
                    var mediaPlayer = MediaPlayer.create(context, R.raw.wrong)
                    mediaPlayer.start()
                }

                val action = question_screenDirections.actionQuestionScreenToMainScreen()
                action.numCorrect = correct
                action.numQuestions = questions
                action.operation = operation
                action.restart = true
                view.findNavController()
                    .navigate(action)
            }
            else {
                val df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.DOWN
                val correctNumDecimal = df.format(correctNumDec)
                // check if userInput matches the correct answer
                if ((userInput.text.toString() == (correctNum.toString()))
                    ||
                    (userInput.text.toString() == (correctNumDecimal))) {
                    correct++
                    showToast("Correct. Good Work!")
                    var mediaPlayer = MediaPlayer.create(context, R.raw.correct)
                    mediaPlayer.start()
                }
                else {
                    showToast("Wrong.")
                    var mediaPlayer = MediaPlayer.create(context, R.raw.wrong)
                    mediaPlayer.start()
                }

                // reset TextView and generate new nums and get correct answer for new nums
                userInput.setText("")
                genNums()
                doMath()
            }
        }
        return view
    }

    fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(context, message , duration).show()
    }

    /**
     * This function generates the operands for the math problems based on difficulty user chose.
     * If difficulty == 1 (easy), operands are between 1 and 9.
     * If difficulty == 2 (medium), operands are between 1 and 24.
     * If difficulty == 3 (hard), operands are between 1 and 49.
     * It then changes the firstNum and secondNum TextViews to display these values accordingly.
     */
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

    /**
     * This function does the math to evaluate what the correct answer should be for each problem,
     * as well as set the oper TextView text to the appropriate operation based on what user chose.
     * If operation == 1 (addition), correctNum is num1 + num2
     * If operation == 2 (multiplication), correctNum is num1 * num2
     * If operation == 3 (division), correctNum is num1 / num2 (rounded to nearest whole number)
     * If operation == 4 (subtraction), correctNum is num1 - num2
     */
    fun doMath() {
        if (operation == 1) {
            correctNum = num1 + num2
            oper.text = "+"
        }
        else if (operation == 2) {
            correctNum = num1 * num2
            oper.text = "*"
        }
        else if (operation == 3) {
            correctNum = num1/num2
            Log.v("correctNum", "correctNum is $correctNum")
            oper.text = "/"
        }
        else if (operation == 4) {
            correctNum = num1 - num2
            oper.text = "-"
        }
    }
}
