package com.interco.e.daggerrxretrofit.base.eventBus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class RxBus private constructor() {
    private val bus = PublishSubject.create<BaseEvent>()

    fun send(o: BaseEvent) {
        this.bus.onNext(o)
    }

    fun toObservable(): Observable<BaseEvent> {
        return this.bus
    }

    fun hasObservers(): Boolean {
        return this.bus.hasObservers()
    }

    companion object {
        private var instance: RxBus? = null

        fun getInstance(): RxBus {
            if (instance == null) {
                instance = RxBus()
            }

            return instance as RxBus
        }
    }
}