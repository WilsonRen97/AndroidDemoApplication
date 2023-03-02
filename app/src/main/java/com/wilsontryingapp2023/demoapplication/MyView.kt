package com.wilsontryingapp2023.demoapplication

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Canvas
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import java.util.concurrent.TimeUnit

class MyView(ourContext: Context?) : View(ourContext), TickListener {
    private var height = 0
    private var width = 0
    private var mathDone = false
    private val swans: MutableList<Swan> = ArrayList()
    private var swanSize = 0
    private var timer: Timer? = null

    fun makeSwans() {
        for (i in 1..10) {
            val rx = (Math.random() * width - swanSize).toFloat()
            val ry = (Math.random() * height - swanSize).toFloat()
            swans.add(Swan(resources, rx, ry, swanSize))
            for (swan in swans) {
                timer!!.register(swan)
            }
        }
    }

    public override fun onDraw(c: Canvas) {
        if (!mathDone) {
            height = getHeight()
            width = getWidth()
            mathDone = true
            swanSize = (height * 0.1f).toInt()

            timer = Timer(Looper.getMainLooper())
            makeSwans()
            post { TimeUnit.SECONDS.sleep(1L) }
            timer!!.register(this)
        }
        for (swan in swans) {
            swan.drawSwan(c)
        }
    }

    override fun onTouchEvent(m: MotionEvent): Boolean {
        println(Thread.currentThread().name)
        if (m.action === MotionEvent.ACTION_DOWN) {
            val x = m.x
            val y = m.y
            val tappedSwan: MutableList<Swan> = ArrayList()
            for (swan in swans) {
                if (swan.contains(x, y)) {
                    tappedSwan.add(swan)
                    timer!!.unregister(swan)
                }
            }
            swans.removeAll(tappedSwan)
            invalidate()

            if (swans.size == 0) {
                val ab: AlertDialog.Builder = AlertDialog.Builder(context)
                ab.setTitle(resources.getString(R.string.gameOver))
                ab.setMessage(R.string.message)
                ab.setCancelable(false)
                // by default, if you tap outside the dialog, then the dialog goes away
                // here we are preventing that from happening

                ab.setPositiveButton(R.string.yesOption) { dialog, which -> makeSwans() }
                ab.setNegativeButton(R.string.noOption) { dialog, which -> (context as Activity).finish() }
                val box: AlertDialog = ab.create()
                box.show()
            }

        }
        if (m.action == MotionEvent.ACTION_UP) {
            for (swan in swans) {
                swan.untapped()
                invalidate()
            }
        }
        return true
    }

    override fun tick() {
        invalidate()
    }
}