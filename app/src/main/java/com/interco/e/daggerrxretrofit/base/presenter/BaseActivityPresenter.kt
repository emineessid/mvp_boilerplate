package com.interco.e.daggerrxretrofit.base.presenter


import android.content.Context
import com.interco.e.daggerrxretrofit.base.BaseViewInterface
import com.interco.e.daggerrxretrofit.base.eventBus.BaseEvent
import com.interco.e.daggerrxretrofit.base.eventBus.RxBus
import com.interco.e.daggerrxretrofit.base.repository.BaseRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by emine on 14/12/2018.
 */
open class BaseActivityPresenter<V : BaseViewInterface, R : BaseRepository>(var repository: R) {
    lateinit var viewCallback: V
    //RX
    private val subscriptions = CompositeDisposable()
    //RX BUS
    private val busEventSubscriptions = CompositeDisposable()


    fun attachViewCallback(viewCallback: V) {
        this.viewCallback = viewCallback
    }


    fun attachViewCallback(viewCallback: Any) {
        this.viewCallback = viewCallback as V
    }


    //    -------------

    private fun sub(s: Disposable) {
        this.subscriptions.add(s)
    }

    protected fun clearSubs() {
        this.subscriptions.clear()
    }

    protected fun <R> subscribe(observable: Observable<R>, onNext: Consumer<in R>, onError: Consumer<Throwable>, onCompleted: Action) {
        this.sub(observable.subscribe(onNext, onError, onCompleted))
    }

    protected fun <R> subscribeMainThred(observable: Observable<R>, onNext: Consumer<in R>, onError: Consumer<Throwable>?) {

        sub(

                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(onNext, onError!!)
        )
    }


    protected fun <R> subscribeMainThredSingle(single: Single<R>, onNext: Consumer<in R>, onError: Consumer<Throwable>?) {

        sub(

                single.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(onNext, onError!!)
        )
    }


    protected fun <R> subscribe(observable: Observable<R>, onNext: Consumer<in R>) {

        this.subscribeMainThred(observable, onNext, null)
    }

    protected fun subscribe(completable: Completable, onComplete: Action, onError: Consumer<in Throwable>?) {
        sub(
                completable.doOnSubscribe { this.sub(it) }.subscribe(onComplete, onError!!))
    }

    protected fun subscribe(completable: Completable, onComplete: Action) {

        this.subscribe(completable, onComplete, null)
    }

    private fun subscribeBusEvents() {
        val observable = RxBus.getInstance().toObservable().doOnNext { this.onBusEventReceived(it) }
        this.busEventSubscriptions.add(observable.subscribeOn(AndroidSchedulers.mainThread()).subscribe())
    }

    private fun unsubscribeBusEvents() {
        this.busEventSubscriptions.clear()
    }

    fun getString(int: Int): String {
        return repository.context.getString(int)
    }

    protected open fun onBusEventReceived(event: BaseEvent) {}

    //--- activity life cycle


    fun onCreated() {
        this.subscribeBusEvents()
    }

    fun onStart() {}

    fun onResume() {}

    fun onPause() {
        this.clearSubs()
    }

    fun onStop() {}

    fun onDestroy() {
        this.unsubscribeBusEvents()
    }


}
