package dev.sanastasov.app.weight.ui

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import dev.sanastasov.app.ui.theme.AppTheme
import dev.sanastasov.app.weight.domain.Weight
import org.junit.Rule
import org.junit.Test

class WeightScreenKtTest {

    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

    @Test
    fun content() {
        val state = WeightViewState.Content(
            listOf(
                WeightViewStateItem.AddWeight,
                WeightViewStateItem.WeekAverage(10, Weight(75))
            )
        )

        paparazzi.snapshot {
            AppTheme {
                WeightScreen(state = state)
            }
        }
    }
}