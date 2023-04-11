package com.example.jocasta.di

import com.example.jocasta.data.repository.SwapiRepository
import com.example.jocasta.data.repository.SwapiRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSwapiRepository(
        repository: SwapiRepositoryImpl
    ): SwapiRepository
}