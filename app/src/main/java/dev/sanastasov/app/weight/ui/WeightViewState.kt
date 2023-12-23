package dev.sanastasov.app.weight.ui

import dev.sanastasov.app.weight.domain.Weight

sealed class WeightViewState {
    data object Loading : WeightViewState()

    data class Content(val items: List<WeightViewStateItem>) : WeightViewState()
}

sealed class WeightViewStateItem {

    data object AddWeight : WeightViewStateItem()

    data class WeekAverage(val weekNumber: Int, val weight: Weight) : WeightViewStateItem()
}