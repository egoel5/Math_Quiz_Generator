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
    var numQuestions = 1
    var numCorrect = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_result_screen, container, false)
        val restartButton = view.findViewById<Button>(R.id.restartButton)
        val correct = view.findViewById<TextView>(R.id.correct)
        val questions = view.findViewById<TextView>(R.id.questions)

        numCorrect = result_screenArgs.fromBundle(requireArguments()).numCorrect
        numQuestions = result_screenArgs.fromBundle(requireArguments()).numQuestions

        correct.text = numCorrect.toString()
        questions.text = numQuestions.toString()

        restartButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_result_screen_to_main_screen)
        }
        return view
    }
}