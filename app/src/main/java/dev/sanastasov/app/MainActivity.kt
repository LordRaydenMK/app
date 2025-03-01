package dev.sanastasov.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dev.sanastasov.app.ui.theme.AppTheme
import dev.sanastasov.app.weight.ui.WeightScreen
import dev.sanastasov.app.weight.ui.WeightViewState
import dev.sanastasov.app.weight.ui.WeightViewStateItem

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeightScreen(
                        WeightViewState.Content(
                            listOf(
                                WeightViewStateItem.WeightEntry("73 kg", "1 Mar"),
                            )
                        )
                    )
                }
            }
        }
    }
}
