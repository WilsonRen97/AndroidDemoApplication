package com.wilsontryingapp2023.demoapplication


import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.RectF

class GridBtn(res: Resources?, private val btnLabel: Char, size: Int, x: Float, y: Float) {
    private val bounds: RectF
    private var unpressBtn: Bitmap
    private var pressBtn: Bitmap
    private var pressed: Boolean

    init {
        unpressBtn = BitmapFactory.decodeResource(res, R.drawable.panda)
        unpressBtn = Bitmap.createScaledBitmap(unpressBtn, size, size, true)
        pressBtn = BitmapFactory.decodeResource(res, R.drawable.graypanda)
        pressBtn = Bitmap.createScaledBitmap(pressBtn, size, size, true)
        bounds = RectF(x, y, x + size, y + size)
        pressed = false
    }

    fun press() {
        pressed = true
    }

    fun release() {
        pressed = false
    }

    fun checkContain(x: Float, y: Float): Boolean {
        return this.contains(x, y)
    }

    fun dance() {
        println("dancing...")
        val dx = (Math.random() * 10 - 5).toFloat()
        val dy = (Math.random() * 10 - 5).toFloat()
        bounds.offset(dx, dy)
    }

    fun drawBtn(c: Canvas) {
        if (pressed) {
            c.drawBitmap(pressBtn, bounds.left, bounds.top, null)
        } else {
            c.drawBitmap(unpressBtn, bounds.left, bounds.top, null)
        }
    }

    fun contains(x: Float, y: Float): Boolean {
        return bounds.contains(x, y) // contains method in RectF returns boolean value
    }
}