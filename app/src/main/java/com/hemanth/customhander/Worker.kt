package com.hemanth.customhander

import android.os.Handler
import android.os.HandlerThread

private const val TAG = "Worker"

class Worker : HandlerThread(TAG) {

    var handler: Handler

    init {
        start()
        handler = Handler(looper)
    }

    fun execute(task: Runnable): Worker {
        handler.post(task)
        return this
    }

}