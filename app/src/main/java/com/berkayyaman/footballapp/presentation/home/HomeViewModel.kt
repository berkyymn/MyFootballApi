package com.berkayyaman.footballapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkayyaman.footballapp.data.repository.FootballRepository
import com.berkayyaman.footballapp.domain.usecases.GetFixturesUseCase
import com.berkayyaman.footballapp.util.FlowExtensions.onError
import com.berkayyaman.footballapp.util.FlowExtensions.onSuccess
import com.berkayyaman.footballapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by berkayyaman on 05,November,2024
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFixtures: GetFixturesUseCase,
    private val footballRepository: FootballRepository
) : ViewModel() {

    private val viewState = MutableStateFlow(HomeViewState())
    val viewStateFlow = viewState
        .onStart {
            viewModelScope.launch {
                onStart()
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = HomeViewState()
        )

    private suspend fun onStart() {
        footballRepository.getFavoriteTeam().collectLatest {
            it?.let { favoriteTeam ->

                viewState.update {
                    it.copy(favoriteTeam = favoriteTeam)
                }

                getFixtures.getLastMatches(teamId = favoriteTeam.teamInfo.id, last = 10).combine(
                    getFixtures.getNextMatches(teamId = favoriteTeam.teamInfo.id, next = 10)){last, next ->

                    if (last is Resource.Error || next is Resource.Error){

                        viewState.update {
                            viewState.value.copy(
                                isLoading = false,
                                error = "Error"
                            )
                        }
                    }
                }.launchIn(viewModelScope)

            }
        }
    }
}