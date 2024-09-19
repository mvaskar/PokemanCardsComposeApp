package com.pokemancards.app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pokemancards.app.pages.list.PokemanCardsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(vmFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PokemanCardsListViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: PokemanCardsListViewModel): ViewModel

}
