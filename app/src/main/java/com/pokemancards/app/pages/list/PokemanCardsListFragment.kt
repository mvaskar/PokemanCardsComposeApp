package com.pokemancards.app.pages.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.pokemancards.app.navigate
import com.pokemancards.app.ui.theme.PokemanCardsAppTheme
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PokemanCardsListFragment: Fragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PokemanCardsListViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[PokemanCardsListViewModel::class.java]
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Log.d("ASKAR", "LIST fagment")

        val callbacks = PokemanCardsListCallbacks(
            onSearchQueryChange = { viewModel.onSearchQueryChange(it) },
            onSortClick = { viewModel.onSortClick(it) },
            onItemClick = { item -> findNavController().navigate(
                "detail",
                bundleOf("item" to item)
            )}
        )

        val listData = PokemanCardsListData(
            mutableStateOf(viewModel.searchQuery),
            mutableStateOf(viewModel.sort)
        )

        return ComposeView(requireContext()).apply {
            setContent {
                val items = viewModel.searchResults.collectAsStateWithLifecycle().value
                PokemanCardsAppTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        PokemanCardsListScreen(
                            callbacks = callbacks,
                            listData = listData,
                            items = items,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}