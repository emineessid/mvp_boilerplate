package com.interco.e.daggerrxretrofit.utils

import android.content.Context
import android.net.ConnectivityManager
import com.interco.e.daggerrxretrofit.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isConnectedToNetwork(context)) {

            throw NoConnectivityException(context)
        } else {
            val response = chain.proceed(chain.request())
            return response
        }
    }

    companion object {

        fun isConnectedToNetwork(context: Context): Boolean {
            val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val isConnected: Boolean
            val activeNetwork = connectivityManager.activeNetworkInfo
            isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting


            return isConnected
        }
    }
}


internal class NoConnectivityException(var context: Context) : IOException() {

    override val message: String?
        get() = context.getString(R.string.error_no_network)
}


