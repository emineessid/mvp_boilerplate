package com.interco.e.daggerrxretrofit.di.module

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.interco.e.daggerrxretrofit.BuildConfig
import com.interco.e.daggerrxretrofit.NETWORK.NetworkApiService
import com.interco.e.daggerrxretrofit.database.ProduitDataBase
import com.interco.e.daggerrxretrofit.di.scope.AppScope
import com.interco.e.daggerrxretrofit.utils.AuthenticationInterceptor
import com.interco.e.daggerrxretrofit.utils.ConnectivityInterceptor
import com.interco.e.daggerrxretrofit.utils.TokenAuthenticator
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by emine on 14/12/2018.
 */
@Module
class AppModule
(internal var context: Context) {

    @Provides
    @AppScope
    internal fun provideGson(): Gson {
        val gson = GsonBuilder()
                .setLenient()
                .create()
        return gson
    }


    @Provides
    @AppScope
    internal fun provideConext(): Context {
        return context
    }

    @Provides
    @AppScope
    internal fun provideRetrofit(gson: Gson): Retrofit {

        val requestInterceptor = { chain: Interceptor.Chain ->


            val url = chain.request()
                    .url()
                    .newBuilder()
                    //.addQueryParameter("key", API_KEY)
                    .build()

            val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

            Log.e("**", "-----------> " + request.toString())



            chain.proceed(request)
        }


        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor(ConnectivityInterceptor(context))
                .addInterceptor(AuthenticationInterceptor())
                //.authenticator(TokenAuthenticator())
                .connectTimeout(6, TimeUnit.SECONDS)
                .readTimeout(6, TimeUnit.SECONDS)
                .build()


        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }


    @Provides
    @AppScope
    internal fun provideApiService(gson: Gson, retrofit: Retrofit): NetworkApiService {
        return NetworkApiService(gson, retrofit)
    }


    @Provides
    @AppScope
    internal fun sharedPrefsManager(): SharedPreferences {
        return context.getSharedPreferences(BuildConfig.sharedPrefsName, Context.MODE_PRIVATE)
    }

    @Provides
    @AppScope
    internal fun provideDataBase(): ProduitDataBase {
        return ProduitDataBase.getInstance(context)!!
    }


}
