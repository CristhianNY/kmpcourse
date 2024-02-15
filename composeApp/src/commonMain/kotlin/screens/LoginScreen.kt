package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import login.SignInViewModel
import navigation.ScreenLoginComponent
import navigation.ScreenLoginEvent
import org.koin.compose.koinInject

@Composable
fun LoginScreen(component: ScreenLoginComponent,  signInViewModel: SignInViewModel = koinInject<SignInViewModel>()
) {
    val text by component.text.subscribeAsState()
    val signInState by signInViewModel.state.collectAsState()

    LaunchedEffect(signInState) {
        if (signInState.isSignInSuccessful) {
            component.onEvent(ScreenLoginEvent.GoToHome)
            signInViewModel.resetState()
        }
    }

    Column(
        Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Login")
        OutlinedTextField(
            value = text,
            onValueChange = { component.onEvent(ScreenLoginEvent.UpdateText(it)) },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        Button(onClick = {
            component.onEvent(ScreenLoginEvent.ClickButtonLogin)
        }) {
            Text("Login")
        }
    }
}