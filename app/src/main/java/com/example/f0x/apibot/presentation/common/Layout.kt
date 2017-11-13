package com.example.f0x.apibot.presentation.common

import android.support.annotation.LayoutRes
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created by f0x on 26.10.17.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class Layout(@LayoutRes val id: Int)