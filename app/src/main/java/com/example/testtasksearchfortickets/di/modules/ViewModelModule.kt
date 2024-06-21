package com.example.testtasksearchfortickets.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtasksearchfortickets.di.viewModel.ViewModelFactory
import com.example.testtasksearchfortickets.di.viewModel.ViewModelKey
import com.example.testtasksearchfortickets.presenter.mainScreen.MainScreenViewModel
import com.example.testtasksearchfortickets.presenter.selectCountry.SelectCountryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    abstract fun bindMainScreenViewModel(viewModel: MainScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SelectCountryViewModel::class)
    abstract fun bindSelectCountryViewModel(viewModel: SelectCountryViewModel): ViewModel

}
