package com.baptistecarlier.experimental.localAi.form.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baptistecarlier.experimental.localAi.form.data.repository.RepositoryFactory
import com.baptistecarlier.experimental.localAi.form.data.repository.Source
import com.baptistecarlier.experimental.localAi.form.data.model.FormAnswer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

internal class FormVM(
    private val repositoryFactory: RepositoryFactory
) : ViewModel() {

    private val _uiState = MutableStateFlow<State<FormAnswer>>(State.Init)
    val uiState = _uiState.asStateFlow()

    fun request(source: Source, prompt: String) {
        _uiState.update { State.Loading }

        viewModelScope.launch {
            var result: Result<FormAnswer>

            // Measure the time taken to fetch the form answer
            val timeMillis = measureTimeMillis {
                result = repositoryFactory(source).getFormAnswer(prompt)
            }

            // Update the UI state with the result
            result.fold(
                onSuccess = { item ->
                    _uiState.update {
                        State.Success(item.copy(durationInMillis = timeMillis))
                    }
                },
                onFailure = { error -> _uiState.update { State.Error(error) } }
            )
        }
    }
}
