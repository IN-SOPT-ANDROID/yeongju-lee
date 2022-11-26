package org.sopt.sample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.sample.data.repository.AuthRepository
import org.sopt.sample.data.repository.AuthRepositoryImpl
import org.sopt.sample.data.repository.ReqResRepository
import org.sopt.sample.data.repository.ReqResRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository = authRepositoryImpl

    @Provides
    @Singleton
    fun providesReqResRepository(
        reqResRepositoryImpl: ReqResRepositoryImpl
    ): ReqResRepository = reqResRepositoryImpl
}
