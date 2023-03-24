package com.example.phonepe.di

import com.example.phonepe.ui.GameActivity
import dagger.Component
import javax.inject.Singleton


@Component
@Singleton

interface Appcomponent {

   fun  inject(activity: GameActivity)



}