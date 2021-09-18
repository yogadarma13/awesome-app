package com.yogadarma.awesomeapp.di

import com.yogadarma.core.domain.usecase.GetCuratedPhotoInteractor
import com.yogadarma.core.domain.usecase.GetCuratedPhotoUseCase
import com.yogadarma.core.domain.usecase.GetDetailInteractor
import com.yogadarma.core.domain.usecase.GetDetailUseCase
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

    @Binds
    @Singleton
    abstract fun provideGetDetailUseCase(getDetailInteractor: GetDetailInteractor): GetDetailUseCase
}