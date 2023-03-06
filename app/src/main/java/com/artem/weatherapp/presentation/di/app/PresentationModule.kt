package com.artem.weatherapp.presentation.di.app

import android.content.Context
import com.artem.weatherapp.domain.usecase.ToastDisplayErrorUseCase
import com.artem.weatherapp.domain.usecase.ValidationInputUseCase
import com.artem.weatherapp.presentation.utilities.AppUtilities
import com.artem.weatherapp.presentation.utilities.AppUtilitiesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {

    @Provides
    @Singleton
    fun provideAppUtilities(@ApplicationContext context: Context): AppUtilities {
        return AppUtilitiesImpl(context = context)
    }

    @Provides
    fun provideValidationInputUseCase(appUtilities: AppUtilities): ValidationInputUseCase {
        return ValidationInputUseCase(appUtilities = appUtilities)
    }

    @Provides
    fun provideToastDisplayErrorUseCase(appUtilities: AppUtilities): ToastDisplayErrorUseCase {
        return ToastDisplayErrorUseCase(appUtilities = appUtilities)
    }

}