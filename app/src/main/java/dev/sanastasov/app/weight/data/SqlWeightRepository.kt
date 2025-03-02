package dev.sanastasov.app.weight.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import dev.sanastasov.app.WeightQueries
import dev.sanastasov.app.weight.domain.Weight
import dev.sanastasov.app.weight.domain.WeightEntry
import dev.sanastasov.app.weight.domain.WeightRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class SqlWeightRepository(
    private val weightQueries: WeightQueries,
) : WeightRepository {
    override fun allWeights(): Flow<List<WeightEntry>> = weightQueries.getAllWeightsDesc()
        .asFlow()
        .mapToList(Dispatchers.IO)
        .map { weights ->
        weights.map { (_, date, weight) ->
            WeightEntry(LocalDate.parse(date), Weight(weight))
        }
    }
}