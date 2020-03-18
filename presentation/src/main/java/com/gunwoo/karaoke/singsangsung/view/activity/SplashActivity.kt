package com.gunwoo.karaoke.singsangsung.view.activity

import android.Manifest
import android.accounts.AccountManager
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.gunwoo.karaoke.data.database.sharedpreferences.SharedPreferenceManager
import com.gunwoo.karaoke.singsangsung.base.BaseActivity
import com.gunwoo.karaoke.singsangsung.databinding.ActivitySplashBinding
import com.gunwoo.karaoke.singsangsung.viewmodel.SplashViewModel
import com.gunwoo.karaoke.singsangsung.viewmodelfactory.SplashViewModelFactory
import com.gunwoo.karaoke.singsangsung.widget.extension.getViewModel
import com.gunwoo.karaoke.singsangsung.widget.extension.startActivityWithFinish
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    @Inject
    lateinit var viewModelFactory: SplashViewModelFactory

    override val viewModel: SplashViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(mViewModel) {
            onSuccessEvent.observe(this@SplashActivity, Observer {
                startActivityWithFinish(MainActivity::class.java)
            })

            onOpenAccountPickerEvent.observe(this@SplashActivity, Observer {
                startActivityForResult(
                    credential.newChooseAccountIntent().addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
                    REQUEST_ACCOUNT_PICKER
                )
            })

            onOpenPermissionEvent.observe(this@SplashActivity, Observer {
                ActivityCompat.requestPermissions(
                    this@SplashActivity,
                    arrayOf(Manifest.permission.GET_ACCOUNTS),
                    REQUEST_PERMISSION_GET_ACCOUNTS
                )
            })
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        val isOK = resultCode == RESULT_OK
        when (requestCode) {
            REQUEST_ACCOUNT_PICKER ->
                if (isOK && data != null && data.extras != null) {
                    val accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME)

                    if (!accountName.isNullOrBlank()) {
                        SharedPreferenceManager.insertAccountName(applicationContext, accountName)
                        mViewModel.credential.selectedAccountName = accountName
                        mViewModel.onSuccessEvent.call()
                    }
                }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_PERMISSION_GET_ACCOUNTS) {
            if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED) {
                mViewModel.checkAccount()
            }
        }
    }

    companion object {
        private const val REQUEST_ACCOUNT_PICKER = 0
        private const val REQUEST_PERMISSION_GET_ACCOUNTS = 1
    }
}