package com.example.solink.repository

import com.example.solink.network.service.PhotoService
import com.example.solink.network.service.UserService
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
    fun provideUserRepository(apiService: UserService): UserRepository {
        return UserRepository(apiService)
    }

    @Provides
    @Singleton
    fun providePhotoRepository(apiService: PhotoService): PhotoRepository {
        return PhotoRepository(apiService)
    }
}