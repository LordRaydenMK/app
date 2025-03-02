package dev.sanastasov.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dev.sanastasov.app.ui.theme.AppTheme
import dev.sanastasov.app.weight.data.SqlWeightRepository
import dev.sanastasov.app.weight.ui.WeightScreen
import dev.sanastasov.app.weight.ui.WeightViewModel

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val driver = AndroidSqliteDriver(Database.Schema, this, "app.db")
        val database = Database(driver)
        val repository = SqlWeightRepository(database.weightQueries)
        val viewModel = WeightViewModel(repository)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state by viewModel.state.collectAsState()
                    WeightScreen(state)
                }
            }
        }
    }
}
