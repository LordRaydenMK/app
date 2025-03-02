package dev.sanastasov.app.weight.domain

import kotlinx.coroutines.flow.Flow

interface WeightRepository {

    fun allWeights(): Flow<List<WeightEntry>>
}