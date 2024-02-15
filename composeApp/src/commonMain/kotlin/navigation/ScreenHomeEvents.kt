package navigation

sealed interface ScreenHomeEvents {
    data object SingOut : ScreenHomeEvents
}