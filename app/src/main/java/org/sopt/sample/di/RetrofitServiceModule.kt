package org.sopt.sample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.sample.data.service.AuthService
import org.sopt.sample.data.service.ReqResService
import org.sopt.sample.di.type.RetrofitType
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {
    fun provideAuthService(@RetrofitModule.Retrofit2(RetrofitType.SOPT) retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    fun provideReqResService(@RetrofitModule.Retrofit2(RetrofitType.SOPT) retrofit: Retrofit): ReqResService =
        retrofit.create(ReqResService::class.java)
}
