package com.example.quizone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizone.ui.theme.QuizOneTheme
import kotlinx.coroutines.selects.select

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizOneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   Column (Modifier.padding(16.dp)){
                    Quiz()
                   }
                }
            }
        }
    }
}


@Composable
fun Quiz(
    viewModel: QuizeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
){
    LazyColumn{(Modifier.padding(16.dp))
        items(viewModel.quizeList){quizItem ->
            Text(text = quizItem.question)
            val selectedOption = rememberSaveable { mutableStateOf("") }
            quizItem.answerList.forEach{answer->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedOption.value = answer
                        viewModel.checkAnswer(quizItem, answer)
                    }, verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = answer == selectedOption.value,
                        onClick = {selectedOption.value =answer
                            viewModel.checkAnswer(quizItem,answer)
                        })
                    Text(text = answer)
                }
            }
        }
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(onClick = {
                            viewModel.OnSubmit()
                }) {
                    Text(text = "Sumbit")
                }
            }
        }
            item {
            Text(text = viewModel.score.value)
         }
    }
}

