package dev.sanastasov.app.weight.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import dev.sanastasov.app.ui.theme.AppTheme
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterial3Api
class WeightScreenKtTest {

    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

    @Test
    fun content() {
        val state = WeightViewState.Content(
            listOf(
                WeightViewStateItem.WeightEntry("73 kg", "1 Mar"),
                WeightViewStateItem.WeightEntry("74.5 kg", "20 Feb"),
                WeightViewStateItem.WeightEntry("75.3 kg", "10 Feb"),
            )
        )

        paparazzi.snapshot {
            AppTheme {
                WeightScreen(state = state)
            }
        }
    }
}