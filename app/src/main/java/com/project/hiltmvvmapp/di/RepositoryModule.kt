package com.project.hiltmvvmapp.di

import com.project.hiltmvvmapp.repositories.NetworkRepository
import com.project.hiltmvvmapp.repositories.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesRepository(repositoryImpl: RepositoryImpl) : NetworkRepository

}