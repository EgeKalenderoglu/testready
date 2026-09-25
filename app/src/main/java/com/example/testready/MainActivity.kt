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
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

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

    val context = LocalContext.current

    val sharedPreferences = remember {
        context.getSharedPreferences("testready_data", Context.MODE_PRIVATE)
    }
    val showAddForm = remember { mutableStateOf(false) }
    val environmentName = remember { mutableStateOf("") }
    val baseUrl = remember { mutableStateOf("") }
    val selectedEnvironmentIndex = remember { mutableStateOf(-1) }
    
    val environmentNames = remember {
        val names = mutableStateListOf<String>()
        val count = sharedPreferences.getInt("environment_count", 0)

        if (count == 0) {
            names.add("SauceDemo QA")
        } else {
            for (i in 0 until count) {
                names.add(
                    sharedPreferences.getString("environment_name_$i", "") ?: ""
                )
            }
        }

        names
    }

    val environmentUrls = remember {
        val urls = mutableStateListOf<String>()
        val count = sharedPreferences.getInt("environment_count", 0)

        if (count == 0) {
            urls.add("https://www.saucedemo.com")
        } else {
            for (i in 0 until count) {
                urls.add(
                    sharedPreferences.getString("environment_url_$i", "") ?: ""
                )
            }
        }

        urls
    }
    val showAddCheckForm = remember { mutableStateOf(false) }
    val checkName = remember { mutableStateOf("") }
    val checkType = remember { mutableStateOf("") }
    val checkUrl = remember { mutableStateOf("") }

    val checkNames = remember {
        val names = mutableStateListOf<String>()
        val count = sharedPreferences.getInt("check_count", 0)

        for (i in 0 until count) {
            names.add(
                sharedPreferences.getString("check_name_$i", "") ?: ""
            )
        }

        names
    }

    val checkTypes = remember {
        val types = mutableStateListOf<String>()
        val count = sharedPreferences.getInt("check_count", 0)

        for (i in 0 until count) {
            types.add(
                sharedPreferences.getString("check_type_$i", "") ?: ""
            )
        }

        types
    }

    val checkEnvironmentIndexes = remember {
        val indexes = mutableStateListOf<Int>()
        val count = sharedPreferences.getInt("check_count", 0)

        for (i in 0 until count) {
            indexes.add(
                sharedPreferences.getInt("check_environment_$i", 0)
            )
        }

        indexes
    }

    val checkResults = remember {
        val results = mutableStateListOf<String>()
        val count = sharedPreferences.getInt("check_count", 0)

        for (i in 0 until count) {
            results.add(
                sharedPreferences.getString("check_result_$i", "Not Run") ?: "Not Run"
            )
        }

        results
    }

    val checkUrls = remember {
        val urls = mutableStateListOf<String>()
        val count = sharedPreferences.getInt("check_count", 0)

        for (i in 0 until count) {
            urls.add(
                sharedPreferences.getString("check_url_$i", "") ?: ""
            )
        }

        urls
    }

    val checkReasons = remember {
        val reasons = mutableStateListOf<String>()
        val count = sharedPreferences.getInt("check_count", 0)

        for (i in 0 until count) {
            reasons.add(
                sharedPreferences.getString("check_reason_$i", "") ?: ""
            )
        }

        reasons
    }
    val historyEnvironmentIndexes = remember {
        val indexes = mutableStateListOf<Int>()
        val count = sharedPreferences.getInt("history_count", 0)

        for (i in 0 until count) {
            indexes.add(
                sharedPreferences.getInt("history_environment_$i", 0)
            )
        }

        indexes
    }

    val historyResults = remember {
        val results = mutableStateListOf<String>()
        val count = sharedPreferences.getInt("history_count", 0)

        for (i in 0 until count) {
            results.add(
                sharedPreferences.getString("history_result_$i", "") ?: ""
            )
        }

        results
    }
    Column(
        modifier = modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        if (showAddForm.value == false && selectedEnvironmentIndex.value == -1) {

            Text(
                text = "TestReady",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "QA Environment Manager",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Test Environments",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(15.dp))

            for (i in environmentNames.indices) {

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = environmentNames[i],
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = environmentUrls[i],
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Button(
                                onClick = {
                                    selectedEnvironmentIndex.value = i
                                }
                            ) {
                                Text("Open")
                            }

                            OutlinedButton(
                                onClick = {

                                    for (j in checkEnvironmentIndexes.lastIndex downTo 0) {
                                        if (checkEnvironmentIndexes[j] == i) {
                                            checkNames.removeAt(j)
                                            checkTypes.removeAt(j)
                                            checkUrls.removeAt(j)
                                            checkResults.removeAt(j)
                                            checkReasons.removeAt(j)
                                            checkEnvironmentIndexes.removeAt(j)
                                        }
                                    }

                                    for (j in checkEnvironmentIndexes.indices) {
                                        if (checkEnvironmentIndexes[j] > i) {
                                            checkEnvironmentIndexes[j] =
                                                checkEnvironmentIndexes[j] - 1
                                        }
                                    }

                                    for (j in historyEnvironmentIndexes.lastIndex downTo 0) {
                                        if (historyEnvironmentIndexes[j] == i) {
                                            historyEnvironmentIndexes.removeAt(j)
                                            historyResults.removeAt(j)
                                        }
                                    }

                                    for (j in historyEnvironmentIndexes.indices) {
                                        if (historyEnvironmentIndexes[j] > i) {
                                            historyEnvironmentIndexes[j] =
                                                historyEnvironmentIndexes[j] - 1
                                        }
                                    }

                                    environmentNames.removeAt(i)
                                    environmentUrls.removeAt(i)

                                    val editor = sharedPreferences.edit()

                                    editor.putInt(
                                        "environment_count",
                                        environmentNames.size
                                    )

                                    for (j in environmentNames.indices) {
                                        editor.putString(
                                            "environment_name_$j",
                                            environmentNames[j]
                                        )

                                        editor.putString(
                                            "environment_url_$j",
                                            environmentUrls[j]
                                        )
                                    }

                                    editor.putInt("check_count", checkNames.size)

                                    for (j in checkNames.indices) {
                                        editor.putString("check_name_$j", checkNames[j])
                                        editor.putString("check_type_$j", checkTypes[j])
                                        editor.putString("check_url_$j", checkUrls[j])
                                        editor.putInt(
                                            "check_environment_$j",
                                            checkEnvironmentIndexes[j]
                                        )
                                        editor.putString(
                                            "check_result_$j",
                                            checkResults[j]
                                        )
                                        editor.putString(
                                            "check_reason_$j",
                                            checkReasons[j]
                                        )
                                    }

                                    editor.putInt(
                                        "history_count",
                                        historyResults.size
                                    )

                                    for (j in historyResults.indices) {
                                        editor.putInt(
                                            "history_environment_$j",
                                            historyEnvironmentIndexes[j]
                                        )

                                        editor.putString(
                                            "history_result_$j",
                                            historyResults[j]
                                        )
                                    }

                                    editor.apply()
                                }
                            ) {
                                Text("Delete")
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    showAddForm.value = true
                }
            ) {
                Text("+ Add Environment")
            }
        } else if (selectedEnvironmentIndex.value != -1 && showAddCheckForm.value == false) {

            Text(
                text = "TestReady",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = environmentNames[selectedEnvironmentIndex.value],
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = environmentUrls[selectedEnvironmentIndex.value],
                style = MaterialTheme.typography.bodyMedium
            )

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

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "Overall Status",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    if (hasChecks == false) {
                        Text("No Checks")
                    } else if (allPassed) {
                        Text("Ready to Test")
                    } else {
                        Text("Not Ready to Test")
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Checks",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            for (i in checkNames.indices) {
                if (checkEnvironmentIndexes[i] == selectedEnvironmentIndex.value) {

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = checkNames[i],
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(checkTypes[i])
                            Text(checkUrls[i])

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Status: ${checkResults[i]}",
                                fontWeight = FontWeight.Bold
                            )

                            if (checkResults[i] == "Failed") {
                                Text("Reason: ${checkReasons[i]}")
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    var foundCheck = false
                    var runPassed = true

                    for (i in checkNames.indices) {
                        if (checkEnvironmentIndexes[i] == selectedEnvironmentIndex.value) {

                            foundCheck = true

                            if (
                                checkUrls[i].startsWith("http://") ||
                                checkUrls[i].startsWith("https://")
                            ) {
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

                    val editor = sharedPreferences.edit()

                    for (i in checkNames.indices) {
                        editor.putString(
                            "check_result_$i",
                            checkResults[i]
                        )

                        editor.putString(
                            "check_reason_$i",
                            checkReasons[i]
                        )
                    }

                    editor.putInt(
                        "history_count",
                        historyResults.size
                    )

                    for (i in historyResults.indices) {
                        editor.putInt(
                            "history_environment_$i",
                            historyEnvironmentIndexes[i]
                        )

                        editor.putString(
                            "history_result_$i",
                            historyResults[i]
                        )
                    }

                    editor.apply()
                }
            ) {
                Text("Run Preflight")
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Preflight History",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            var foundHistory = false

            for (i in historyResults.indices) {
                if (historyEnvironmentIndexes[i] == selectedEnvironmentIndex.value) {

                    foundHistory = true

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = historyResults[i],
                            modifier = Modifier.padding(12.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            if (foundHistory == false) {
                Text("No previous runs")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = {
                        showAddCheckForm.value = true
                    }
                ) {
                    Text("+ Add Check")
                }

                OutlinedButton(
                    onClick = {
                        selectedEnvironmentIndex.value = -1
                    }
                ) {
                    Text("Back")
                }
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
                    val editor = sharedPreferences.edit()

                    editor.putInt("check_count", checkNames.size)

                    for (i in checkNames.indices) {
                        editor.putString("check_name_$i", checkNames[i])
                        editor.putString("check_type_$i", checkTypes[i])
                        editor.putString("check_url_$i", checkUrls[i])
                        editor.putInt("check_environment_$i", checkEnvironmentIndexes[i])
                        editor.putString("check_result_$i", checkResults[i])
                        editor.putString("check_reason_$i", checkReasons[i])
                    }

                    editor.apply()

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

                    val editor = sharedPreferences.edit()

                    editor.putInt("environment_count", environmentNames.size)

                    for (i in environmentNames.indices) {
                        editor.putString("environment_name_$i", environmentNames[i])
                        editor.putString("environment_url_$i", environmentUrls[i])
                    }

                    editor.apply()

                    environmentName.value = ""
                    baseUrl.value = ""
                    showAddForm.value = false
                }
            ) {
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