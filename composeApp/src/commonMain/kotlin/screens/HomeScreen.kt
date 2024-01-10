package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import navigation.ScreenHomeComponent
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    component: ScreenHomeComponent,
    homeViewModel: HomeViewModel = koinInject<HomeViewModel>()
) {

    val uiState by homeViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(component.text)

        Button(onClick = {
            homeViewModel.getDataExample()
        }) {
            Text("Ir Atras")
        }

        LazyColumn {
            items(uiState.examplesString) { example ->
                Text(text = example)
            }
        }

    }
}