package com.berkayyaman.footballapp.presentation.favoriteTeam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkayyaman.footballapp.domain.usecases.SearchTeamsUseCase
import com.berkayyaman.footballapp.util.FlowExtensions.onError
import com.berkayyaman.footballapp.util.FlowExtensions.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Created by berkayyaman on 11,October,2024
 */

@HiltViewModel
class FavoriteTeamViewModel @Inject constructor(
    private val searchTeam: SearchTeamsUseCase
): ViewModel(){


    private val viewState = MutableStateFlow(FavoriteTeamViewState())
    val viewStateFlow = viewState.onStart {
        sendRequest("fen")
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = FavoriteTeamViewState()
    )

    private fun sendRequest(text: String){

        searchTeam.invoke(text).onSuccess {
            viewState.value = viewStateFlow.value.copy(teams = it.response)
        }.onError {

        }.launchIn(viewModelScope)
    }

}