package com.wilsontryingapp2023.demoapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RoomActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        val adapter = Adapter(this, Supplier().rooms)

        val recyclerView: RecyclerView = findViewById(R.id.myRecycleView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
    }
}