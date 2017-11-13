package com.example.f0x.apibot.presentation.common

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import com.arellomobile.mvp.MvpView
import com.example.f0x.apibot.app.Const

/**
 * Created by f0x on 28.10.17.
 */
abstract class ABasePermissionPresenter<View : MvpView> : ABasePresenter<View>() {


    protected fun checkExternalStoragePermission(): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) true else ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected fun requestRecordAudioPermission() {
        (context as Activity).requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
                Const.REQUEST_RECORD_AUDIO_PERMISSION)
    }

    open fun setRecordAudioResult(isGranted: Boolean) {

    }


}