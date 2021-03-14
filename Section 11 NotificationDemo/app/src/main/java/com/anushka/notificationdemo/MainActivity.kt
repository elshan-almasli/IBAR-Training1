package com.anushka.notificationdemo


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val channelID = "com.anushka.notificationdemo.channel1"
    private var notificationManager: NotificationManager? = null

    private val REPLY_KEY = "REPLY_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID, "demo channel", "this is a desc")
        button.setOnClickListener {
            displayNotification()
        }
    }

    private fun createNotificationChannel(id: String, name: String, channelDescription: String) {
        val channelImportance = NotificationManager.IMPORTANCE_HIGH
        val notificationChannel = NotificationChannel(id, name, channelImportance).apply {
            description = channelDescription
        }
        notificationManager?.createNotificationChannel(notificationChannel)
    }

    private fun displayNotification() {
        val notificationId = 50

        val secondActivityIntent = Intent(this, SecondActivity::class.java)

        val secondPendingIntent = PendingIntent.getActivities(
            applicationContext,
            0,
            arrayOf(secondActivityIntent),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        //action

        val settingIntent = Intent(this, SettingActivity::class.java)

        val settingPendingIntent =
            PendingIntent.getActivity(this, 0, settingIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val settingActon =
            NotificationCompat.Action.Builder(1, "Setting", settingPendingIntent).build()

        //reply action

        val remoteInput = RemoteInput.Builder(REPLY_KEY).run {
            setLabel("Enter your input")
            build()
        }

        val replyAction = NotificationCompat.Action.Builder(
            0,
            "Reply",
            secondPendingIntent
        ).addRemoteInput(remoteInput)
            .build()

        val notification = NotificationCompat.Builder(applicationContext, channelID)
            .setContentTitle("This is a notification")
            .setContentText("Hello Notification")
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .addAction(settingActon)
            .addAction(replyAction)
            .setContentIntent(secondPendingIntent)
            .build()

        notificationManager?.notify(notificationId, notification)
    }

}
