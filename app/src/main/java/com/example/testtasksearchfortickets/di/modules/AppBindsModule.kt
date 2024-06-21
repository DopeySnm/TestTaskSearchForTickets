package com.example.testtasksearchfortickets.di.modules

import com.example.testtasksearchfortickets.data.repository.OffersRepository
import com.example.testtasksearchfortickets.data.repository.OffersRepositoryImpl
import com.example.testtasksearchfortickets.domain.GetAllOffersUseCase
import com.example.testtasksearchfortickets.domain.GetAllOffersUseCaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AppBindsModule {

    @Binds
    @Singleton
    fun bindOfferRepository(
        repository: OffersRepositoryImpl
    ) : OffersRepository

    @Binds
    @Singleton
    fun bindGetAllOffersUseCase(
        useCase: GetAllOffersUseCaseImpl
    ) : GetAllOffersUseCase

}
