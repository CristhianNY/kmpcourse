import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import login.SignInViewModel
import navigation.ScreenLoginComponent
import navigation.ScreenLoginEvent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreen(component: ScreenLoginComponent, signInViewModel: SignInViewModel = koinInject()) {
    val signInState by signInViewModel.state.collectAsState()

    LaunchedEffect(signInState) {
        if (signInState.isSignInSuccessful) {
            component.onEvent(ScreenLoginEvent.GoToHome)
            signInViewModel.resetState()
        }
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter =  painterResource("google.png"),
                contentDescription = "Google logo"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { component.onEvent(ScreenLoginEvent.ClickButtonLogin) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4285F4)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(50.dp)
            ) {
                Text(
                    "Login with Google",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
