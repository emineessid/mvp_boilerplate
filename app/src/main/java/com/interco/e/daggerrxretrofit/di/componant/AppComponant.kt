package com.interco.e.daggerrxretrofit.di.componant

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

import com.interco.e.daggerrxretrofit.NETWORK.NetworkApiService
import com.interco.e.daggerrxretrofit.base.repository.BaseRepository
import com.interco.e.daggerrxretrofit.database.ProduitDataBase
import com.interco.e.daggerrxretrofit.di.module.AppModule
import com.interco.e.daggerrxretrofit.di.scope.AppScope
import com.interco.e.daggerrxretrofit.utils.AuthenticationInterceptor
import com.interco.e.daggerrxretrofit.utils.TokenAuthenticator

import dagger.Component
import javax.inject.Singleton

/**
 * Created by emine on 14/12/2018.
 */

@Component(modules = arrayOf(AppModule::class))
@AppScope
interface AppComponant {
    @Singleton
    fun provideApiService(): NetworkApiService

    @Singleton
    fun provideConext(): Context

    @Singleton
    fun provideDatabase(): ProduitDataBase

    @Singleton
    fun sharedPrefsManager(): SharedPreferences

    @Singleton
    fun inject(baseRepository: BaseRepository)

    @Singleton
    fun inject(tokenAuthenticator: TokenAuthenticator)

    @Singleton
    fun inject(authenticationInterceptor: AuthenticationInterceptor)


}
