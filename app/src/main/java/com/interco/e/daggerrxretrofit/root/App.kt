package com.interco.e.daggerrxretrofit.root

import android.app.Application

import com.facebook.stetho.Stetho
import com.interco.e.daggerrxretrofit.di.componant.AppComponant
import com.interco.e.daggerrxretrofit.di.module.AppModule

import timber.log.Timber
import com.crashlytics.android.Crashlytics
import com.interco.e.daggerrxretrofit.di.componant.DaggerAppComponant
import io.fabric.sdk.android.Fabric


/**
 * Created by emine on 14/12/2018.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        appComponant = DaggerAppComponant.builder()
                .appModule(AppModule(applicationContext))
                .build()

        //Fabric.with(this, Crashlytics())
        Stetho.initializeWithDefaults(this)

        Timber.plant(Timber.DebugTree())


    }

    companion object {
        var appComponant: AppComponant? = null
            private set
    }

}
