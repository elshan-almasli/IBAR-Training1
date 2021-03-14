package com.anushka.workmanagerdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    companion object{
        val KEY_INPUT = "KEY_INPUT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
//            testOneTimeWorkRequest()
            testPeriodicTimeRequest()
        }
    }

    private fun setOneTimeWorkRequest() {
        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .build()
        WorkManager.getInstance(applicationContext)
            .enqueue(uploadRequest)
    }

    private fun testOneTimeWorkRequest(){

        val workManager = WorkManager.getInstance(applicationContext)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .build()


        val data = Data.Builder().putInt(KEY_INPUT,20).build()

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
//            .setConstraints(constraints)
            .setInputData(data)
            .build()

        val uploadWorkerRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .build()

        workManager
//            .beginWith(uploadWorkerRequest)
//            .then(oneTimeWorkRequest)
            .enqueue(oneTimeWorkRequest)

        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this, Observer {
                textView.text = it.state.name
                if (it.state.isFinished) {
                    val data = it.outputData.getString(MyWorker.KEY_WORKER)
                    Toast.makeText(applicationContext, "$data", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun testPeriodicTimeRequest(){
        val periodicWorkRequest = PeriodicWorkRequest
            .Builder(MyWorker::class.java,1,TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueue(periodicWorkRequest)
    }
}
