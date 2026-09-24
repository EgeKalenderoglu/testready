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
import androidx.compose.runtime.mutableStateListOf

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
    val selectedEnvironmentIndex = remember { mutableStateOf(-1) }

    val environmentNames = remember {
        mutableStateListOf("SauceDemo QA")
    }
    val environmentUrls = remember {
        mutableStateListOf("https://www.saucedemo.com")
    }
    val showAddCheckForm = remember { mutableStateOf(false) }
    val checkName = remember { mutableStateOf("") }
    val checkType = remember { mutableStateOf("") }
    val checkUrl = remember { mutableStateOf("") }

    val checkNames = remember {
        mutableStateListOf<String>()
    }

    val checkTypes = remember {
        mutableStateListOf<String>()
    }

    val checkEnvironmentIndexes = remember {
        mutableStateListOf<Int>()
    }

    val checkResults = remember {
        mutableStateListOf<String>()
    }

    val checkUrls = remember {
        mutableStateListOf<String>()
    }

    val checkReasons = remember {
        mutableStateListOf<String>()
    }

    val historyEnvironmentIndexes = remember {
        mutableStateListOf<Int>()
    }

    val historyResults = remember {
        mutableStateListOf<String>()
    }
    Column(
        modifier = modifier.padding(20.dp)
    ) {
        if (showAddForm.value == false && selectedEnvironmentIndex.value == -1) {
            Text("TestReady")
            Spacer(modifier = Modifier.height(20.dp))
            Text("Test Environments")
            Spacer(modifier = Modifier.height(20.dp))
            for (i in environmentNames.indices) {
                Text(environmentNames[i])
                Text(environmentUrls[i])
                Button(
                    onClick = {
                        selectedEnvironmentIndex.value = i
                    }) {
                    Text("Open")
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    showAddForm.value = true
                }) {
                Text("Add Environment")
            }
        } else if (selectedEnvironmentIndex.value != -1 && showAddCheckForm.value == false) {

            Text("TestReady")
            Spacer(modifier = Modifier.height(20.dp))

            Text(environmentNames[selectedEnvironmentIndex.value])
            Text(environmentUrls[selectedEnvironmentIndex.value])
            var hasChecks = false
            var allPassed = true

            for (i in checkNames.indices) {
                if (checkEnvironmentIndexes[i] == selectedEnvironmentIndex.value) {
                    hasChecks = true

                    if (checkResults[i] != "Passed") {
                        allPassed = false
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (hasChecks == false) {
                Text("Overall Status: No Checks")
            } else if (allPassed) {
                Text("Overall Status: Ready to Test")
            } else {
                Text("Overall Status: Not Ready to Test")
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text("Checks")
            Spacer(modifier = Modifier.height(10.dp))

            for (i in checkNames.indices) {
                if (checkEnvironmentIndexes[i] == selectedEnvironmentIndex.value) {
                    Text(checkNames[i])
                    Text(checkTypes[i])
                    Text(checkUrls[i])
                    Text("Status: ${checkResults[i]}")
                    if (checkResults[i] == "Failed") {
                        Text("Reason: ${checkReasons[i]}")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
            Button(
                onClick = {
                    var foundCheck = false
                    var runPassed = true
                    for (i in checkNames.indices) {
                        if (checkEnvironmentIndexes[i] == selectedEnvironmentIndex.value) {

                            foundCheck = true

                            if (checkUrls[i].startsWith("http://") || checkUrls[i].startsWith("https://")) {
                                checkResults[i] = "Passed"
                                checkReasons[i] = ""
                            } else {
                                checkResults[i] = "Failed"
                                checkReasons[i] = "Invalid URL"
                                runPassed = false
                            }
                        }
                    }
                    if (foundCheck) {
                        historyEnvironmentIndexes.add(selectedEnvironmentIndex.value)

                        if (runPassed) {
                            historyResults.add("Ready to Test")
                        } else {
                            historyResults.add("Not Ready to Test")
                        }
                    }
                }) {
                Text("Run Preflight")
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text("Preflight History")
            Spacer(modifier = Modifier.height(10.dp))

            var foundHistory = false

            for (i in historyResults.indices) {
                if (historyEnvironmentIndexes[i] == selectedEnvironmentIndex.value) {
                    foundHistory = true
                    Text(historyResults[i])
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
            if (foundHistory == false) {
                Text("No previous runs")
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    showAddCheckForm.value = true
                }) {
                Text("Add Check")
            }


            Button(
                onClick = {
                    selectedEnvironmentIndex.value = -1
                }) {
                Text("Back")
            }
        } else if (showAddCheckForm.value) {
            Text("Add Check")
            Spacer(modifier = Modifier.height(20.dp))
            Text("Check Name")
            OutlinedTextField(
                value = checkName.value, onValueChange = {
                    checkName.value = it
                })
            Spacer(modifier = Modifier.height(10.dp))
            Text("Check Type")
            OutlinedTextField(
                value = checkType.value, onValueChange = {
                    checkType.value = it
                })

            Spacer(modifier = Modifier.height(10.dp))

            Text("Check URL")

            OutlinedTextField(
                value = checkUrl.value, onValueChange = {
                    checkUrl.value = it
                })

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    checkNames.add(checkName.value)
                    checkTypes.add(checkType.value)
                    checkUrls.add(checkUrl.value)
                    checkEnvironmentIndexes.add(selectedEnvironmentIndex.value)
                    checkResults.add("Not Run")
                    checkReasons.add("")

                    checkName.value = ""
                    checkType.value = ""
                    checkUrl.value = ""
                    showAddCheckForm.value = false
                }) {
                Text("Save Check")
            }

            Button(
                onClick = {
                    showAddCheckForm.value = false
                }) {
                Text("Cancel")
            }
        } else {
            Text("Add Environment")
            Spacer(modifier = Modifier.height(20.dp))
            Text("Environment Name")

            OutlinedTextField(
                value = environmentName.value, onValueChange = {
                    environmentName.value = it
                })
            Spacer(modifier = Modifier.height(10.dp))
            Text("Base URL")

            OutlinedTextField(
                value = baseUrl.value, onValueChange = {
                    baseUrl.value = it
                })
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    environmentNames.add(environmentName.value)
                    environmentUrls.add(baseUrl.value)
                    environmentName.value = ""
                    baseUrl.value = ""
                    showAddForm.value = false
                }) {
                Text("Save")
            }
            Button(
                onClick = {
                    showAddForm.value = false
                }) {
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