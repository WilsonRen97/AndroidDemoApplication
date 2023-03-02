package com.wilsontryingapp2023.demoapplication

import android.content.res.Resources
import android.graphics.*

class Swan(res: Resources?, x: Float, y: Float, size: Int) : TickListener, Comparable<Swan> {
    private val bounds: RectF
    private var swanImage: Bitmap
    private var swanImage2: Bitmap
    private var tapped: Boolean
    private val id: Int
    private val textPaint: Paint

    init {
        swanImage = BitmapFactory.decodeResource(res, R.drawable.panda)
        swanImage = Bitmap.createScaledBitmap(swanImage, size, size, true)
        swanImage2 = BitmapFactory.decodeResource(res, R.drawable.graypanda)
        swanImage2 = Bitmap.createScaledBitmap(swanImage2, size, size, true)
        bounds = RectF(x, y, x + size, y + size)
        tapped = false
        count++
        id = count
        textPaint = Paint()
        textPaint.textSize = 100f
        textPaint.color = Color.BLUE
        textPaint.textAlign = Paint.Align.CENTER
    }

    fun drawSwan(c: Canvas) {
        if (tapped) {
            c.drawBitmap(swanImage2, bounds.left, bounds.top, null)
        } else {
            c.drawBitmap(swanImage, bounds.left, bounds.top, null)
        }
        c.drawText("" + id, bounds.centerX(), bounds.centerY(), textPaint)
    }

    fun contains(x: Float, y: Float): Boolean {
        return bounds.contains(x, y)
    }

    fun tapped() {
        tapped = true
    }

    fun untapped() {
        tapped = false
    }

    fun dance() {
        val dx = (Math.random() * 10 - 5).toFloat()
        val dy = (Math.random() * 10 - 5).toFloat()
        bounds.offset(dx, dy)
    }

    override fun tick() {
        dance()
    }


    companion object {
        private var count = 0
    }

    override fun compareTo(other: Swan): Int {
        if (this.bounds.top < other.bounds.top) {
            return 1
        } else if (this.bounds.top > other.bounds.top) {
            return -1
        } else {
            return 0
        }
    }
}