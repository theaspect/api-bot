package com.example.f0x.apibot.domain.repository

import com.google.gson.annotations.SerializedName

/**
 * Created by f0x on 28.11.17.
 */
data class BackendError(@SerializedName("code") val code: Int = 0,
                        @SerializedName("message") val message: String)
