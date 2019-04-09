package com.interco.e.daggerrxretrofit.ui.splash

import android.content.Intent
import android.os.Bundle
import com.interco.e.daggerrxretrofit.R
import com.interco.e.daggerrxretrofit.base.activity.BaseActivity
import com.interco.e.daggerrxretrofit.ui.search.SearchActivity
import com.interco.e.daggerrxretrofit.utils.DialogUtils


class SplashActivity : BaseActivity<SplashPresenter, SplashViewInterface>(), SplashViewInterface {


    override fun createViewInterface(): SplashViewInterface {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
    }


    override fun onStart() {
        super.onStart()
        presenter.requestValidToken()
    }


    override fun showMessageError(message: String) {
        DialogUtils.showBottomMessage(this, message, 1000, R.color.error_message_color, Runnable { })
    }

    override fun navigateTohome() {
        val myIntent = Intent(this@SplashActivity, SearchActivity::class.java)
        startActivity(myIntent)
    }


    override fun onResume() {
        super.onResume()
        presenter.requestValidToken()
    }
}
