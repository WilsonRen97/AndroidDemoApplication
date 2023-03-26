package com.wilsontryingapp2023.demoapplication

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

class MainActivity : AppCompatActivity() {
    var currantImage: Int? = null
    var currentText = ""
    private var soundtrack: MediaPlayer? = null
    lateinit var imageView: ImageView

    override fun onStop() {
        super.onStop()
        soundtrack!!.pause()
        println("something important????")
        Log.d("my message", "this is my message...")
    }

    override fun onStart() {
        super.onStart()
        // 如果getSoundFX是return true的話，就播放音樂
        if (SettingsActivity.getSoundFX(this)) {
            soundtrack!!.start()
        } else {
            // 不然就暫停音樂
            soundtrack!!.pause()
        }

        if (SettingsActivity.getImagePref(this) == "0") {
            imageView.scaleX = 1f
            imageView.scaleY = 1f
        } else if (SettingsActivity.getImagePref(this) == "-1x") {
            imageView.scaleX = -1f
            imageView.scaleY = 1f
        } else {
            imageView.scaleX = 1f
            imageView.scaleY = -1f
        }
    }

    // 改這邊
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 每次save state時，將目前的 saved image 存入
        outState.putInt("image", currantImage!!)
        outState.putString("text", currentText)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        soundtrack = MediaPlayer.create(this, R.raw.ice_fire);
        var counter = 0
        var systemHandler: Handler = Handler(Looper.getMainLooper())
        var supplier = Supplier()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.thread_layout)
        var button: Button = findViewById(R.id.button3)
        imageView = findViewById(R.id.imageView2)
        var editText: EditText = findViewById(R.id.editTextTextPersonName)
        var preButton: Button = findViewById(R.id.button8)

        preButton.setOnClickListener() { _ ->
            var myIntent = Intent(this, SettingsActivity::class.java)
            startActivity(myIntent)
        }

        // saved state內部有內容
        println(savedInstanceState)
        if (savedInstanceState != null) {
            println("we are heree...")
            // 設定image view
            imageView.setImageResource(savedInstanceState.getInt("image"))
            // 設定current image
            currantImage = savedInstanceState.getInt("image")

            // 設定counter
            for (i in 0 until supplier.rooms.size) {
                if (supplier.rooms[i]!!.picture == savedInstanceState.getInt("image")) {
                    counter = i
                    break
                }
            }
            currentText = savedInstanceState.getString("text").toString()
            editText.setText(savedInstanceState.getString("text"))
        } else {
            // saved state內部沒有內容
            imageView.setImageResource(R.drawable.bank)
            currantImage = R.drawable.bank
            counter = 0
            currentText = "請輸入名稱"
        }



        class MyThread : Thread() {
            val countDown = CountDownLatch(1)
            inner class MyHandler(looper: Looper) : Handler(looper) {
                override fun handleMessage(msg: Message) {
                    println(Thread.currentThread().name)
                    Thread.sleep(1500)
                    systemHandler.post {
                        // 改這邊
                        counter += 1
                        currantImage = supplier.rooms[(counter) % supplier.rooms.size]!!.picture
                        imageView.setImageResource(currantImage!!)
                    }
                }
            }

            var myHandler: MyHandler? = null
            override fun run() {
                Looper.prepare()
                myHandler = MyHandler(Looper.myLooper()!!)
                countDown.countDown() // 通知main thread我們完成製作myHandler了
                Looper.loop()
            }
        }
        var myThread = MyThread()
        myThread.start()


        try {
            // 在LooperThread完成myHandler製作之前，都需要等待
            myThread.countDown.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        val executor: ExecutorService = Executors.newFixedThreadPool(10)


//        button.setOnClickListener() { _ ->
//            myThread.myHandler!!.sendMessage(Message.obtain())
//        }

        button.setOnClickListener {
            executor.execute{
                println(Thread.currentThread().name)
                Thread.sleep(10000)
                systemHandler.post {
                    println(counter)
                    counter += 1
                    currantImage = supplier.rooms[(counter) % supplier.rooms.size]!!.picture
                    imageView.setImageResource(currantImage!!)
                }
            }
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                currentText = s.toString()
            }
        })
    }
}