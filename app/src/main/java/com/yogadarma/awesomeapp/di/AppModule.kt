package com.yogadarma.awesomeapp.di

import com.yogadarma.core.domain.usecase.GetCuratedPhotoInteractor
import com.yogadarma.core.domain.usecase.GetCuratedPhotoUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideGetCuratedPhotoUseCase(getCuratedPhotoInteractor: GetCuratedPhotoInteractor): GetCuratedPhotoUseCase
}