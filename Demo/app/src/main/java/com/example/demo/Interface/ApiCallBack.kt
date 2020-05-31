package com.lifeprint.digitalframe.Login.Interface

interface ApiCallBack<T> {
    fun onSuccess(data: T)
    fun onError(error:String?)
}