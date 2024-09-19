package com.pokemancards.app.pages.list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemancards.app.api.PokemanCardRepository
import com.pokemancards.app.model.PokemanCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PokemanCardsListCallbacks(
    val onSearchQueryChange: (String) -> Unit,
    val onSortClick: (String) -> Unit,
    val onItemClick: (PokemanCard) -> Unit
)

data class PokemanCardsListData(
    var searchText: MutableState<String>,
    var sort: MutableState<String>,
)

class PokemanCardsListViewModel @Inject constructor(
    val pokemanCardRepository: PokemanCardRepository
): ViewModel() {

    private val _pokemanCards = MutableStateFlow<List<PokemanCard>>(emptyList())

    val pokemanCards: StateFlow<List<PokemanCard>>
        get() = _pokemanCards.asStateFlow()

    var searchQuery by mutableStateOf("")

    var sort by mutableStateOf("")

    val searchResults: StateFlow<List<PokemanCard>> =
        snapshotFlow { searchQuery }
            .combine(snapshotFlow { sort }) { searchQuery, sort ->
                Pair(searchQuery, sort)
            }
            .combine(pokemanCards) { (searchQuery, sort) , pokemanCards ->
                val list = when {
                    searchQuery.isNotEmpty() -> pokemanCards.filter { pokemanCard ->
                        pokemanCard.name.contains(searchQuery, ignoreCase = true)
                    }
                    else -> pokemanCards
                }
                val sortedList = when(sort) {
                    "level" -> list.sortedBy { it.level?.toInt() ?: Int.MAX_VALUE }
                    "hp" -> list.sortedBy { it.hp.toInt() }
                    else -> list
                }
                sortedList
            }.stateIn(
                scope = viewModelScope,
                initialValue = emptyList(),
                started = SharingStarted.WhileSubscribed(5_000)
            )


    init {
        loadPokemanCards()
    }

    private fun loadPokemanCards() {
        viewModelScope.launch {
            try {
                _pokemanCards.update { pokemanCardRepository.getCards().data }
                Log.d("ASKAR", "loadPokemanCards: ${pokemanCards.value.size}")
            } catch (e: Exception) {
                _pokemanCards.update { emptyList() }
                Log.d("ASKAR", "loadPokemanCards: ${e.message.toString()}")
            }
        }
    }

    fun onSearchQueryChange(newQuery: String) {
        Log.d("ASKAR", "onSearchQueryChange")
        searchQuery = newQuery
    }

    fun onSortClick(s: String) {
        sort = s
    }

}