package com.example.f0x.apibot.domain.repository

/**
 * Created by f0x on 28.11.17.
 */
class RepositoryException : Exception {
    var backendError: BackendError? = null
    override var message: String? = null

    constructor(message: String) : super(message) {
        this.message = message
    }


    constructor(backendError: BackendError) {
        this.backendError = this.backendError
        this.message = this.backendError?.message
    }

    constructor(cause: Throwable) : super(cause)

}
