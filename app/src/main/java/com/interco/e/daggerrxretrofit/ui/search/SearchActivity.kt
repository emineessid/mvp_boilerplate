package com.interco.e.daggerrxretrofit.ui.search

import android.os.Bundle
import android.widget.Toast
import com.comuto.search.data.model.ApiTrips
import com.interco.e.daggerrxretrofit.R
import com.interco.e.daggerrxretrofit.base.activity.BaseActivity
import com.interco.e.daggerrxretrofit.utils.DialogUtils
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity<SearchActivityPresenter, SearchActivityViewInterface>(), SearchActivityViewInterface {


    override fun createViewInterface(): SearchActivityViewInterface {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        }


    override fun onStart() {
        super.onStart()
        submit_btn.setOnClickListener {
            presenter.getLocations(departure_city.text.toString(), arrival_city.text.toString())
        }
    }


    override fun showErrorGetLocations(message: String) {
        DialogUtils.showBottomMessage(this, message, 200, R.color.error_message_color, Runnable { })
    }

    override fun showSuccsesgetLocations(result: ApiTrips) {
        Toast.makeText(this, " Price of the first result is : " + result.trips[0].price, Toast.LENGTH_SHORT).show()
    }
}
