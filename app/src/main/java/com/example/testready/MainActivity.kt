package com.example.testready

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
fun MainScreen(modifier: Modifier = Modifier){
    Column(
        modifier = modifier.padding(20.dp)
    ) {
        Text("TestReady")
        Spacer(modifier = Modifier.height(20.dp))
        Text("Test Environments")
        Spacer(modifier = Modifier.height(20.dp))
        Text("SauceDemo QA")
        Text("https://www.saucedemo.com")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { }) {
            Text("Add Environment")
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