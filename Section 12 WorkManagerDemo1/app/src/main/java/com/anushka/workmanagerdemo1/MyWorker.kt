package com.anushka.workmanagerdemo1

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class MyWorker(context: Context,workerParameters: WorkerParameters): Worker(context,workerParameters) {
    companion object{
        const val KEY_WORKER = "KEY_WORKER"
        private const val TAG = "MyWorker"
    }
    override fun doWork(): Result {
        return try {

            val inputData = inputData.getInt(MainActivity.KEY_INPUT,0)

            for (i in inputData until 500){
                Log.i(TAG, "doWork: $i")
            }

            val dateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss",Locale.getDefault())
            val time = dateFormat.format(Date())

            val outputData = Data.Builder()
                .putString(KEY_WORKER,time)
                .build()

            Result.success()
        }catch (e:Exception){
            Result.failure()
        }
    }
}