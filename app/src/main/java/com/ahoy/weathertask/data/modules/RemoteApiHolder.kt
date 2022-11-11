package com.ahoy.weathertask.data.modules

import com.ahoy.weathertask.data.ServiceApi

class RemoteApiHolder {
    private var remoteApi: ServiceApi? = null
    fun getRemoteApi() = remoteApi
    fun setRemoteApi(value: ServiceApi) {
        this.remoteApi = value
    }
}