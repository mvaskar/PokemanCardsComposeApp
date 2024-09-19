package com.pokemancards.app.di

import com.pokemancards.app.MainActivity
import com.pokemancards.app.pages.list.PokemanCardsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributePokemanCardsListFragment(): PokemanCardsListFragment

}