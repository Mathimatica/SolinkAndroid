package com.example.solink.repository

import com.example.solink.network.SLService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Available app-wide
object AppModule {
    @Provides
    @Singleton
    fun provideUserRepository(apiService: SLService): UserRepository {
        return UserRepository(apiService)
    }
}