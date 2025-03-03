package dev.sanastasov.app.weight.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.sanastasov.app.weight.ui.WeightViewState.History
import dev.sanastasov.app.weight.ui.WeightViewState.InsertWeight

@ExperimentalMaterial3Api
@Composable
fun WeightScreen(
    state: WeightViewState,
    onInsertWeight: () -> Unit,
    onSubmitWeight: (String) -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("My App") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onInsertWeight) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    ) { paddingValues ->
        when (state) {
            is History -> WeightContent(state, Modifier.padding(paddingValues))
            is InsertWeight -> InsertWeight(Modifier.padding(paddingValues), onSubmitWeight)
        }
    }
}

@Composable
fun WeightContent(state: History, modifier: Modifier) {
    LazyColumn(
        modifier = modifier
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
        is WeightViewStateItem.WeightEntry -> WeightEntry(item)
    }
}

@Composable
fun WeightEntry(item: WeightViewStateItem.WeightEntry) {
    Card {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Text(text = item.date, fontSize = 16.sp)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = item.weight, fontSize = 48.sp)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
@Preview
private fun WeightScreenPreview() {
    val state = History(
        listOf(
            WeightViewStateItem.WeightEntry("74 kg", "1 Mar"),
            WeightViewStateItem.WeightEntry("75.5 kg", "20 Feb"),
            WeightViewStateItem.WeightEntry("76 kg", "10 Feb")
        )
    )
    Surface(Modifier.fillMaxSize()) {
        WeightScreen(state = state, {}) {}
    }
}