package com.berkayyaman.footballapp.presentation.favoriteTeam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkayyaman.footballapp.domain.usecases.SearchTeamsUseCase
import com.berkayyaman.footballapp.presentation.searchbar.SearchEvent
import com.berkayyaman.footballapp.util.FlowExtensions.onError
import com.berkayyaman.footballapp.util.FlowExtensions.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by berkayyaman on 11,October,2024
 */

@HiltViewModel
class FavoriteTeamViewModel @Inject constructor(
    private val searchTeam: SearchTeamsUseCase
) : ViewModel() {


    private val viewState = MutableStateFlow(FavoriteTeamViewState())
    val viewStateFlow = viewState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = FavoriteTeamViewState()
    )

    fun onEvent(event: FavTeamEventInterface){
        when(event){
            is SearchEvent -> onSearchEvent(event)
            is FavoriteTeamEvent -> onFavoriteTeamEvent(event)
        }
    }

    private fun onSearchEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.Search -> {
                viewModelScope.launch {
                    viewState.value = viewStateFlow.value.copy(isLoading = true)
                }

                searchTeam.invoke(viewStateFlow.value.searchString)
                    .onSuccess { teamsUIModel ->
                        viewState.value = viewStateFlow.value.copy(
                            teams = teamsUIModel.response,
                            error = "",
                            isLoading = false
                        )
                    }.onError {errorMessage ->
                        viewState.value = viewStateFlow.value.copy(
                            teams = arrayListOf(),
                            isLoading = false,
                            error = errorMessage
                        )
                    }.launchIn(viewModelScope)
            }

            is SearchEvent.UpdateSearchString -> {
                viewState.value = viewStateFlow.value.copy(searchString = event.searchString)
            }

        }
    }

    private fun onFavoriteTeamEvent(event: FavoriteTeamEvent){
        when(event){
            is FavoriteTeamEvent.TeamClicked -> {
                println(event.teamUiModel.teamInfo.name)
            }
        }
    }

}