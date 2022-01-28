package com.jwar.github_repo.core.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class RefreshableLiveData<T>(
    private val source: () -> LiveData<T>
) : MediatorLiveData<T>() {

    private var liveData = source()

    init {
        this.addSource(liveData, ::observer)
    }

    private fun observer(data: T) {
        value = data
    }

    fun refresh() {
        this.removeSource(liveData)
        liveData = source()
        this.addSource(liveData, ::observer)
    }
}