package com.pokemancards.app.di

import androidx.lifecycle.ViewModel
import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import dagger.MapKey
import kotlin.reflect.KClass

@Suppress("DEPRECATED_JAVA_ANNOTATION")
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)