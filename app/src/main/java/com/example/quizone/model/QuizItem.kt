package com.example.quizone.model

data class QuizItem (
    val question: String,
    val answerList:List<String>,
    val correctChoice:String,
    val isAnswerCorrect:Boolean= false
)
{

}