package com.myapp.les_13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapp.les_13.ui.theme.Les_13Theme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Les_13Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val isLoading = viewModel.isLoading.collectAsState()
                    val userResponse = viewModel.userResponse.collectAsState()
                    val coroutine = rememberCoroutineScope()
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Our user: ${userResponse.value}")
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(onClick = { viewModel.getUser() }) {
                            Text(text = "Get Users")
                        }
                    }
                    if (isLoading.value) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(0.5f)),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
            }
        }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Les_13Theme {
        Greeting("Android")
    }
}

/*
Який HTTP-запит використовується для отримання даних з сервера?
GET

Що таке HTTP-протокол?
Протокол для передачі даних у мережі Інтернет

Які бібліотеки можна використовуватися для спрощення роботи з мережевими запитами в Android?

Що таке JSON?

Формат даних для обміну інформацією між сервером та клієнтом

Яка ціль обробки помилок мережі та сервера?
Забезпечити безперервну роботу додатку, незалежно від помилок.

 */