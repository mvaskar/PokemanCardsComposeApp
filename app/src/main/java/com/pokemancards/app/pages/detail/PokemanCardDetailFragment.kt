package com.pokemancards.app.pages.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.pokemancards.app.model.PokemanCard
import com.pokemancards.app.ui.theme.PokemanCardsAppTheme

class PokemanCardDetailFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Log.d("ASKAR", "DETAIL fagment")

        val item = arguments?.getParcelable<PokemanCard>("item")!!

        return ComposeView(requireContext()).apply {
            setContent {
                PokemanCardsAppTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        PokemanCardDetailScreen(
                            item = item,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}