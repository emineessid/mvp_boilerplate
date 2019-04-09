package com.interco.e.daggerrxretrofit.base.eventBus

import androidx.annotation.Keep

@Keep
class BaseEvent(nameEvent: String) {
    var nameEvent: String
        protected set

    init {
        this.nameEvent = nameEvent
    }
}