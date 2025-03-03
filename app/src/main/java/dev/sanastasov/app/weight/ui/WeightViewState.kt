package dev.sanastasov.app.weight.ui

sealed class WeightViewState {
    data class History(val items: List<WeightViewStateItem>) : WeightViewState()
}

sealed class WeightViewStateItem {

    data class WeightEntry(val weight: String, val date: String) : WeightViewStateItem()
}