package com.wilsontryingapp2023.demoapplication

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView




class Adapter(val context: Context, val rooms: Array<Room?>) :
    RecyclerView.Adapter<Adapter.RoomViewHolder>() {

    companion object {
        var counter: Int = 0
    }

    inner class RoomViewHolder(var roomView: View) : RecyclerView.ViewHolder(roomView) {

        private val textView: TextView = this.roomView.findViewById(R.id.textView2)
        private val imageView: ImageView = this.roomView.findViewById(R.id.imageView)
        var id: Int = 0
        var message: String? = null

        init {
            imageView.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, "我最喜歡的房子是$message")
                intent.type = "text/plain"
                try {
                    context.startActivity(Intent.createChooser(intent, "請選擇一個App來傳送訊息"))
                } catch (e: ActivityNotFoundException) {
                    // Define what your app should do if no activity can handle the intent.
                }
            }
        }

        fun setTextViewData(text: String) {
            textView.text = text
            message = text
        }

        fun setImageViewData(image: Int) {
            imageView.setImageResource(image)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        val returnedViewHolder = RoomViewHolder(view)
        returnedViewHolder.id = counter
        counter++
        println("正在創建view" + returnedViewHolder.id)
        return returnedViewHolder
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        println("正在使用viewholder" + holder.id + "，並且設定重新textview以及imageview的數據")
        holder.setTextViewData(Supplier().rooms[position]!!.name)
        holder.setImageViewData(Supplier().rooms[position]!!.picture)
    }

    override fun getItemCount(): Int {
        return rooms.size
    }
}