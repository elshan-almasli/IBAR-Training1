package com.anushka.notificationdemo

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        receiveInput()
    }

    private fun receiveInput(){

        val REPLY_KEY = "REPLY_KEY"
        val intent = this.intent
        val remoteInput = RemoteInput.getResultsFromIntent(intent)

        if(remoteInput!= null){
            val message = remoteInput.getCharSequence(REPLY_KEY)
            text_view_reply.text = message
        }

        val channelId = "com.anushka.notificationdemo.channel1"
        val notificationId = 50

        val notification = NotificationCompat.Builder(applicationContext,channelId)
            .setContentText("Success")
            .setSmallIcon(android.R.drawable.ic_media_play)
            .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager?.notify(notificationId,notification)
    }
}