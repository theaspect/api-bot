package com.example.f0x.apibot.app.injections

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by f0x on 13.11.17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class PerActivity