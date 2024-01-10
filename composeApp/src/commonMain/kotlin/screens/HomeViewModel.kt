package screens

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.ExampleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UiState(val examplesString: List<String> = emptyList())
class HomeViewModel(private val exampleRepository: ExampleRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun getDataExample() {
        viewModelScope.launch {
            val example: List<String> = exampleRepository.getExample()
            _uiState.update {
                it.copy(examplesString = example)
            }
        }
    }
}