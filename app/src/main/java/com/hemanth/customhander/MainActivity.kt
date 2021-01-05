package com.hemanth.customhander

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.TextView

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var worker: /*SimpleWorker*/Worker
    private val tvMessage: TextView by lazy { findViewById<TextView>(R.id.tvMessage) }
    private val handler = Handler(Looper.getMainLooper()) {
        tvMessage.text = (it.obj).toString()
        return@Handler true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvMessage
        worker = /*SimpleWorker()*/Worker()
        worker.execute(Runnable {
            Thread.sleep(1000)
            val message = Message.obtain()
            message.obj = "Task1 completed"
            handler.sendMessage(message)
        }).execute(Runnable {
            Thread.sleep(1000)
            val message = Message.obtain()
            message.obj = "Task2 completed"
            handler.sendMessage(message)
        }).execute(Runnable {
            Thread.sleep(1000)
            val message = Message.obtain()
            message.obj = "Task3 completed"
            handler.sendMessage(message)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        worker.quit()
    }
}