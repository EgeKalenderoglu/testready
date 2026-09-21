package com.example.testready

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testready.ui.theme.TestReadyTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.ui.unit.dp
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TestReadyTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val showAddForm = remember { mutableStateOf(false) }
    val environmentName = remember { mutableStateOf("") }
    val baseUrl = remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(20.dp)
    ) {
        if (showAddForm.value == false) {
            Text("TestReady")
            Spacer(modifier = Modifier.height(20.dp))
            Text("Test Environments")
            Spacer(modifier = Modifier.height(20.dp))
            Text("SauceDemo QA")
            Text("https://www.saucedemo.com")
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    showAddForm.value = true
                }) {
                Text("Add Environment")
            }
        } else {
            Text("Add Environment")
            Spacer(modifier = Modifier.height(20.dp))
            Text("Environment Name")

            OutlinedTextField(
                value = environmentName.value,
                onValueChange = {
                    environmentName.value = it
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text("Base URL")

            OutlinedTextField(
                value = baseUrl.value,
                onValueChange = {
                    baseUrl.value = it
                }
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { }
            ) {
                Text("Save")
            }
            Button(
                onClick = {
                    showAddForm.value = false
                }
            ) {
                Text("Cancel")

            }
        }
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun TestReadyPreview() {
        TestReadyTheme {
            MainScreen()
        }
    }