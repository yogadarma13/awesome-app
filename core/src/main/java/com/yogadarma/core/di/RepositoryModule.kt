package com.yogadarma.core.di

import com.yogadarma.core.data.source.AppRepository
import com.yogadarma.core.domain.repository.IRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(appRepository: AppRepository): IRepository
}