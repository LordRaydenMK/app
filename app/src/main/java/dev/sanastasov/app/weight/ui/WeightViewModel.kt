package dev.sanastasov.app.weight.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sanastasov.app.weight.domain.Weight
import dev.sanastasov.app.weight.domain.WeightEntry
import dev.sanastasov.app.weight.domain.WeightRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WeightViewModel(
    private val repository: WeightRepository,
) : ViewModel() {

    private val dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")

    private val insert = MutableStateFlow(false)

    val state: StateFlow<WeightViewState> = combine(
        repository.allWeights(),
        insert,
    ) { weights, insert ->
        when (insert) {
            true -> WeightViewState.InsertWeight("")
            false -> weights.toViewState(dateFormatter)
        }
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            WeightViewState.History(emptyList())
        )

    fun onInsertWeightClicked() {
        insert.value = true
    }

    fun onSubmitClicked(weightStr: String) {
        val weightValue = weightStr.toDouble()
        viewModelScope.launch {
            val weightEntry = WeightEntry(
                date = LocalDate.now(),
                weight = Weight(weightValue)
            )
            repository.insertWeight(weightEntry)
            insert.value = false
        }
    }
}

private fun List<WeightEntry>.toViewState(dateFormatter: DateTimeFormatter): WeightViewState.History =
    map { entry ->
        WeightViewStateItem.WeightEntry(
            weight = entry.weight.value.toString(),
            date = entry.date.format(dateFormatter)
        )
    }.let { WeightViewState.History(items = it) }