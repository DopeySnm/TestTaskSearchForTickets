package com.example.testtasksearchfortickets

import android.app.Application
import com.example.testtasksearchfortickets.di.AppComponent
import com.example.testtasksearchfortickets.di.DaggerAppComponent

class MainApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appContext(this)
            .application(this)
            .build()
    }
}
