package dev.sanastasov.app.weight.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InsertWeight(
    modifier: Modifier = Modifier,
    onSubmitClicked: (String) -> Unit
) {
    var weightText by remember { mutableStateOf("") }

    // Custom input filter to allow only up to 2 decimal places
    val filterInput: (String) -> String = { input ->
        val regex = Regex("^\\d*\\.?\\d{0,2}$")
        if (input.isEmpty() || regex.matches(input)) input else weightText
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Enter your weight")

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = weightText,
                onValueChange = { newValue ->
                    weightText = filterInput(newValue)
                },
                label = { Text("Weight") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (weightText.isNotEmpty()) {
                        onSubmitClicked(weightText)
                        weightText = "" // Reset the input field
                    }
                },
                enabled = weightText.isNotEmpty()
            ) {
                Text("Submit")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InsertWeightPreview() {
    InsertWeight(
        onSubmitClicked = { /* Preview doesn't need to handle clicks */ }
    )
}

@Preview(showBackground = true)
@Composable
fun InsertWeightWithValuePreview() {
    var weightText by remember { mutableStateOf("75.5") }
    
    InsertWeight(
        onSubmitClicked = { 
            // Just for preview to show the enabled button state
            weightText = it
        }
    )
}