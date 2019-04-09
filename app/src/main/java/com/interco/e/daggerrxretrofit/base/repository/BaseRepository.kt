package com.interco.e.daggerrxretrofit.base.repository


import android.content.Context
import com.google.gson.Gson
import com.interco.e.daggerrxretrofit.NETWORK.NetworkApiService
import com.interco.e.daggerrxretrofit.database.ProduitDataBase
import com.interco.e.daggerrxretrofit.root.App
import com.interco.e.daggerrxretrofit.utils.PreferencesHelper
import timber.log.Timber
import java.lang.Exception
import java.util.concurrent.ExecutionException
import javax.inject.Inject

/**
 * Created by emine on 14/12/2018.
 */
open class BaseRepository {

    init {
        App.appComponant!!.inject(this)
    }

    @Inject
    lateinit var networkApiService: NetworkApiService
    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    @Inject
    lateinit var produitDataBase: ProduitDataBase

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var gson: Gson





}
