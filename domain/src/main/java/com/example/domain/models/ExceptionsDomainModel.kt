package com.example.domain.models

import android.util.Log
import java.net.ConnectException
import java.net.UnknownHostException

sealed class ExceptionsDomainModel(exception: Throwable) : Throwable(exception) {
    override val cause: Throwable = exception

    class Other(exception: Throwable) : ExceptionsDomainModel(exception)
    class NoInternet(exception: Throwable) : ExceptionsDomainModel(exception)
    class NoAuth(exception: Throwable) : ExceptionsDomainModel(exception)
}



