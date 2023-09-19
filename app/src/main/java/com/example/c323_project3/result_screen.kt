package com.example.c323_project3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [result_screen.newInstance] factory method to
 * create an instance of this fragment.
 */
class result_screen : Fragment() {
    // Declare numQuestions and numCorrect variables to be populated with safeArgs
    var numQuestions = 1
    var numCorrect = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_result_screen, container, false)
        // Declare restart button and TextViews to be populated
        val restartButton = view.findViewById<Button>(R.id.restartButton)
        val correct = view.findViewById<TextView>(R.id.correct)
        val questions = view.findViewById<TextView>(R.id.questions)

        // populate values with SafeArgs from question_screen
        numCorrect = result_screenArgs.fromBundle(requireArguments()).numCorrect
        numQuestions = result_screenArgs.fromBundle(requireArguments()).numQuestions

        /* set TextView text to the required values based on how many questions the user selected
        and how many they got right */
        correct.text = numCorrect.toString()
        questions.text = numQuestions.toString()

        // set onClickListener to navigate back to main screen when restart button is pressed
        restartButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_result_screen_to_main_screen)
        }
        return view
    }
}