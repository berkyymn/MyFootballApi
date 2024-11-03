package com.berkayyaman.footballapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkayyaman.footballapp.data.repository.FootballRepository
import com.berkayyaman.footballapp.domain.model.TeamUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by berkayyaman on 22,October,2024
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    footballRepository: FootballRepository
): ViewModel() {

    private val viewState = MutableStateFlow(MainViewState())

    val viewStateFlow = viewState
        .onStart {
            viewModelScope.launch {
                footballRepository.getFavoriteTeam().collectLatest { favoriteTeam ->
                    viewState.update {
                        it.copy(
                            favoriteTeam = favoriteTeam,
                            destination = if (favoriteTeam == null) "myFirstRoute" else "mySecondRoute"
                        )
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = viewState.value
        )


}

data class MainViewState(
    var favoriteTeam: TeamUiModel? = null,
    var destination: String = ""
)