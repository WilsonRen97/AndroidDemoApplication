package com.wilsontryingapp2023.demoapplication

import android.os.Handler
import android.os.Looper
import android.os.Message

class Timer(looper: Looper) : Handler(looper) {
    private val listeners: MutableList<TickListener> = ArrayList()

    init {
        sendMessageDelayed(obtainMessage(), 0)
    }

    override fun handleMessage(msg: Message) {
        // println("Loop name is ${Looper.myLooper()}")
        notifyListeners()
        sendMessageDelayed(obtainMessage(), 1000)
    }

    fun register(t: TickListener) {
        listeners.add(t)
    }

    fun unregister(t: TickListener) {
        listeners.remove(t)
    }

    private fun notifyListeners() {
        for (listener in listeners) {
            listener.tick()
        }
    }
}
