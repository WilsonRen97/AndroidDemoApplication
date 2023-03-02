package com.wilsontryingapp2023.demoapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView


class SplashActivity : Activity() {

    private var iv: ImageView? = null

    public override fun onCreate(b: Bundle?) {
        super.onCreate(b)
        iv = ImageView(this)
        iv?.setImageResource(R.drawable.panda)
        iv?.scaleType = ImageView.ScaleType.FIT_XY
        setContentView(iv)

        // 獲得Inent物件
        val myIntent: Intent = intent // or getIntent()
        val information: String? = myIntent.getStringExtra("some information")
        println(information)

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.getAction() === MotionEvent.ACTION_DOWN) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // close this Main activity
        }
        return true
    }
}