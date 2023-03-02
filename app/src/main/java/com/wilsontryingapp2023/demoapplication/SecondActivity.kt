package com.wilsontryingapp2023.demoapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        // 取得name資訊
        val myIntent: Intent = intent // or getIntent()
        val information: String? = myIntent.getStringExtra("name")

        // 找到文字View object
        val textView: TextView = findViewById(R.id.textView)
        textView.text = information
    }
}