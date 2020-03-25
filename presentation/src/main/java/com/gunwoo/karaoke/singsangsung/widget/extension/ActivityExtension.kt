package com.gunwoo.karaoke.singsangsung.widget.extension

import android.Manifest
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.gunwoo.karaoke.singsangsung.R
import java.util.*


fun AppCompatActivity.shortToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.shortToast(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.longToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun AppCompatActivity.longToast(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun AppCompatActivity.startActivity(activity: Class<*>) {
    startActivity(Intent(applicationContext, activity).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
}

fun AppCompatActivity.startActivityForResult(activity: Class<*>,requestCode:Int) {
    startActivityForResult(Intent(applicationContext, activity).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),requestCode)
}

fun AppCompatActivity.startActivityWithFinish(activity: Class<*>) {
    startActivityWithFinish(Intent(applicationContext, activity).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
}

fun AppCompatActivity.startActivityWithFinish(intent: Intent) {
    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
    this.finish()
}

fun AppCompatActivity.startActivityWithFinishAll(activity: Class<*>) {
    val intent = Intent(applicationContext, activity)
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    startActivity(intent)
    this.finishAffinity()
}

fun AppCompatActivity.startActivitiesWithFinish(vararg activity: Class<*>) {
    val intents: ArrayList<Intent> = ArrayList()
    for (clazz in activity) {
        val intent = Intent(applicationContext, clazz)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intents.add(intent)
    }
    startActivities(intents.toArray(arrayOf<Intent?>()))
    this.finish()
}

fun AppCompatActivity.checkPermission() {
    val permissionListener: PermissionListener = object : PermissionListener {
        override fun onPermissionGranted() { }

        override fun onPermissionDenied(deniedPermissions: ArrayList<String?>?) {
            Toast.makeText(applicationContext, R.string.message_permission, Toast.LENGTH_LONG).show()
        }
    }

    TedPermission.with(this)
        .setPermissionListener(permissionListener)
        .setPermissions(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO
        )
        .check()
}

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(factory: ViewModelProvider.Factory): T =
    ViewModelProvider(this, factory)[T::class.java]

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(): T =
    ViewModelProvider(this)[T::class.java]