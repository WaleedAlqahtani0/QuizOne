package com.example.quizone

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.quizone.model.QuizItem

class QuizeViewModel: ViewModel() {
    var quizeList: MutableList<QuizItem> = mutableListOf(
        QuizItem(
            "What is the capital city of Saudi Arabia",
            listOf<String>("Riyadh","Jeddah","Abha","Makkah"),
            "Riyadh"
        ),
        QuizItem(
            "What is the capital of Germany?",
             listOf("Paris", "London", "Berlin", "Madrid"),
             "Berlin",
        ),
        QuizItem(
            "What is the capital of Spain?",
             listOf("Paris", "London", "Berlin", "Madrid"),
             "Madrid",

        ),

    )

    val score = mutableStateOf("")

    fun checkAnswer(quizItem: QuizItem, answer:String){
        quizeList = quizeList.map {
            if(it == quizItem&& answer == it.correctChoice){
                it.copy(isAnswerCorrect = true)
            }else if(it == quizItem&& answer != it.correctChoice){
                it.copy(isAnswerCorrect = false)

            }else{
                it
            }
        }.toMutableList()
    }

    fun OnSubmit(){

        val numOfCorrect = quizeList.count { it.isAnswerCorrect }
        score.value= "You got ${numOfCorrect} out of ${quizeList.size}"

    }

}