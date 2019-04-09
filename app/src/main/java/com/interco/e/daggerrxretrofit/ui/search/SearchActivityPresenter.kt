package com.interco.e.daggerrxretrofit.ui.search
import com.interco.e.daggerrxretrofit.base.presenter.BaseActivityPresenter
import io.reactivex.functions.Consumer

class SearchActivityPresenter : BaseActivityPresenter<SearchActivityViewInterface,SearchActivityRepesotery>(SearchActivityRepesotery()) {
    fun getLocations(departure: String, arrival: String) {
        if (departure.isEmpty() || arrival.isEmpty()) {
            viewCallback.showErrorGetLocations(" Epmty data !")
        } else {
            viewCallback.toggleLoading(true)

            subscribeMainThred(repository.requestLocations( departure, arrival),
                    Consumer { result ->
                        viewCallback.toggleLoading(false)
                        viewCallback.showSuccsesgetLocations(result)
                    },
                    Consumer { error ->
                        viewCallback.toggleLoading(false)
                        viewCallback.showErrorGetLocations(error.localizedMessage)
                    })
        }
    }
}
