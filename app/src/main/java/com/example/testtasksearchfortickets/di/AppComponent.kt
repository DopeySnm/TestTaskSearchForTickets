package com.example.testtasksearchfortickets.di

import android.app.Application
import android.content.Context
import com.example.testtasksearchfortickets.di.modules.AppBindsModule
import com.example.testtasksearchfortickets.di.modules.NetworkModule
import com.example.testtasksearchfortickets.di.modules.ViewModelModule
import com.example.testtasksearchfortickets.presenter.mainScreen.MainScreenFragment
import com.example.testtasksearchfortickets.presenter.selectCountry.SelectCountryFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        AppBindsModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    fun inject(fragment: MainScreenFragment)

    fun inject(fragment: SelectCountryFragment)

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(app: Application): Builder

        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): AppComponent
    }
}
