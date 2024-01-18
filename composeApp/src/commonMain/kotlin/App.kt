import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import login.LoginScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import tabs.HomeTab
import tabs.UserTab


@Composable
fun App() {
    MaterialTheme {
        TabNavigator(UserTab) { navigator ->
            Scaffold(bottomBar = {
                BottomNavigation {
                    TabItem(UserTab)
                    TabItem(HomeTab)
                }
            }) {
                CurrentTab()
            }
        }
    }
}

@Composable
private fun RowScope.TabItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(selected = tabNavigator.current == tab,
        onClick = {
            tabNavigator.current = tab
        },
        icon = {
            tab.options.icon?.let { painter ->
                Icon(painter, contentDescription = tab.options.title)
            }
        })
}