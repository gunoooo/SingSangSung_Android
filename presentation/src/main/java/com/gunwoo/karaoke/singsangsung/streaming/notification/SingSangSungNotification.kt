package com.gunwoo.karaoke.singsangsung.streaming.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.app.NotificationCompat
import com.gunwoo.karaoke.singsangsung.R
import com.gunwoo.karaoke.singsangsung.ui.splash.SplashActivity

object SingSangSungNotification {

    private const val NOTIFICATION_ID = 20020827
    private const val CHANNEL_ID = "SINSANGSUNG_NOTIFICATION"
    private const val name = "싱생송 - 무료 오프라인 노래방"
    private const val description = "SingSangSung Alarm"

    private var notificationManager: NotificationManager? = null

    private var openPendingIntent: PendingIntent? = null

    var notification: Notification? = null

    fun showNotification(context: Context, message: String?, title: String) {
        init(context)

        notification = NotificationCompat.Builder(context, CHANNEL_ID).apply {
            setSmallIcon(R.mipmap.ic_launcher)
            setLargeIcon(BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher))

            setContentTitle(title)
            setContentText(message)
            setContentIntent(openPendingIntent)

            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setAutoCancel(true)
        }.build()

        vibrateEffect(context)

        notificationManager!!.notify(NOTIFICATION_ID, notification)

    }

    private fun init(context: Context) {
        if (notificationManager == null) {
            notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH)
                channel.description = description
                notificationManager!!.createNotificationChannel(channel)
            }
        }

        if (openPendingIntent == null) {
            openPendingIntent = PendingIntent.getActivity(
                context,
                0,
                Intent(context, SplashActivity::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
    }

    private fun vibrateEffect(context: Context) {
        val v = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        else
            v.vibrate(500)
    }
}