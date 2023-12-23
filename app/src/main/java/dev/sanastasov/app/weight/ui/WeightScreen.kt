package dev.sanastasov.app.weight.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.sanastasov.app.weight.domain.Weight

@Composable
fun WeightScreen(state: WeightViewState) {
    when (state) {
        WeightViewState.Loading -> CircularProgressIndicator()
        is WeightViewState.Content -> WeightContent(state)
    }
}

@Composable
fun WeightContent(state: WeightViewState.Content) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(state.items) {
            WeightItem(it)
        }
    }
}

@Composable
fun WeightItem(item: WeightViewStateItem) {
    when (item) {
        WeightViewStateItem.AddWeight -> AddWeightItem()
        is WeightViewStateItem.WeekAverage -> WeekAverageItem(item)
    }
}

@Composable
fun AddWeightItem() {
    Card {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Text(text = "No weight added for today!")

            Spacer(modifier = Modifier.size(4.dp))

            Button(onClick = {}) {
                Text(text = "Add Weight")
            }
        }
    }
}

@Composable
fun WeekAverageItem(item: WeightViewStateItem.WeekAverage) {
    Card {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Text(text = item.weight.value.toString(), fontSize = 48.sp)

            Spacer(modifier = Modifier.size(4.dp))

            Text(text = "0.5kg increase from last week")
        }
    }
}

@Composable
@Preview
private fun WeightScreenPreview() {
    val state = WeightViewState.Content(
        listOf(
            WeightViewStateItem.AddWeight,
            WeightViewStateItem.WeekAverage(10, Weight(75))
        )
    )
    Surface(Modifier.fillMaxSize()) {
        WeightScreen(state = state)
    }
}