package com.baptistecarlier.experimental.localAi.form.ui.viewModel

/**
 * Represents the state of a view model in a unidirectional data flow architecture.
 */
internal sealed interface State<out T> {
    data object Init : State<Nothing>
    data class Error(val throwable: Throwable) : State<Nothing>
    data object Loading : State<Nothing>
    data class Success<T>(val value: T) : State<T>
}


