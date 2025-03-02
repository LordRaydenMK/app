package dev.sanastasov.app.weight.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sanastasov.app.weight.domain.WeightEntry
import dev.sanastasov.app.weight.domain.WeightRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.format.DateTimeFormatter

class WeightViewModel(
    private val repository: WeightRepository,
) : ViewModel() {

    private val dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")

    val state: StateFlow<WeightViewState> = repository.allWeights()
        .map { weights -> toViewState(weights) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            WeightViewState.Content(emptyList())
        )

    private fun toViewState(entries: List<WeightEntry>): WeightViewState.Content =
        entries
            .map { entry ->
                WeightViewStateItem.WeightEntry(
                    weight = entry.weight.value.toString(),
                    date = entry.date.format(dateFormatter)
                )
            }
            .let { WeightViewState.Content(items = it) }
}