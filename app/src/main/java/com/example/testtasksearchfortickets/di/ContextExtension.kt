package com.example.testtasksearchfortickets.di

import android.content.Context
import com.example.testtasksearchfortickets.MainApp

val Context.appComponent: AppComponent
    get() = when(this){
        is MainApp -> appComponent
        else -> this.applicationContext.appComponent
    }
