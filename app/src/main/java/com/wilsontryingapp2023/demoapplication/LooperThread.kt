package com.wilsontryingapp2023.demoapplication

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.TextView

class MyThreadHandler(looper : Looper) : Handler(looper) {
    override fun handleMessage(msg: Message) {
        Thread.sleep(4000)
    }
}

var myHandler: MyThreadHandler? = null


class LooperThread() : Thread() {


    // override Thread class的run()
    override fun run() {
        Looper.prepare()
        myHandler = MyThreadHandler(Looper.myLooper()!!)
        Looper.loop()
    }
}