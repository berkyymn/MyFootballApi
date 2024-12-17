package com.berkayyaman.footballapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkayyaman.footballapp.data.repository.FootballRepository
import com.berkayyaman.footballapp.domain.usecases.GetFixturesUseCase
import com.berkayyaman.footballapp.util.FlowExtensions.onError
import com.berkayyaman.footballapp.util.FlowExtensions.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by berkayyaman on 09,December,2024
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val footballRepository: FootballRepository,
    private val getFixtures: GetFixturesUseCase
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


    private suspend fun onStart(){
        footballRepository.getFavoriteTeam().collectLatest {
            it?.let { favoriteTeam ->

                viewState.update {
                    viewState.value.copy(favoriteTeam = favoriteTeam, loading = true)
                }

                footballRepository.getFixturesFromDB(favoriteTeam.teamInfo.id).collectLatest { fixtures ->

                    if (fixtures.isEmpty()){

                        getFixtures.invoke(favoriteTeam.teamInfo.id)
                            .onSuccess { _ ->
                                viewState.update {
                                    viewState.value.copy(
                                        loading = false,
                                        error = ""
                                    )
                                }
                            }.onError { errorMessage ->

                                viewState.update {
                                    viewState.value.copy(
                                        loading = false,
                                        error = errorMessage,
                                        fixtures = arrayListOf()
                                    )
                                }

                            }.launchIn(viewModelScope)
                    }else{
                        viewState.update {
                            viewState.value.copy(
                                fixtures = ArrayList(fixtures),
                                loading = false,
                                error = ""
                            )
                        }
                    }
                }
            }
        }
    }
}