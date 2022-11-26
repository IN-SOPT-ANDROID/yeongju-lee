package org.sopt.sample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.sample.data.service.AuthService
import org.sopt.sample.data.service.ReqResService
import org.sopt.sample.di.RetrofitModule.Retrofit2
import org.sopt.sample.di.type.RetrofitType.REQ_RES
import org.sopt.sample.di.type.RetrofitType.SOPT
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {
    @Provides
    fun provideAuthService(@Retrofit2(SOPT) retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    fun provideReqResService(@Retrofit2(REQ_RES) retrofit: Retrofit): ReqResService =
        retrofit.create(ReqResService::class.java)
}
