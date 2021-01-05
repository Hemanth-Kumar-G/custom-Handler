package com.hemanth.customhander

import android.util.Log
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean

private const val TAG = "SimpleWorker"
class SimpleWorker : Thread(TAG) {

    private val alive = AtomicBoolean(true)
    private val taskQueue = ConcurrentLinkedQueue<Runnable>()

    init {
        start()
    }

    override fun run() {
        while (alive.get()) {
            val task = taskQueue.poll()
            task?.run()
        }
        Log.e(TAG, "simple worker terminated")
    }


    fun execute(task: Runnable): SimpleWorker {
        taskQueue.add(task)
        return this
    }

    fun quit() {
        alive.set(false)
    }

}