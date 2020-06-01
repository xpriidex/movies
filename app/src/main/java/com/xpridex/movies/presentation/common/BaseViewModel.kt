package com.xpridex.movies.presentation.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.properties.Delegates

abstract class BaseViewModel<T : BaseViewModel.BaseViewState>(initialState: T) : ViewModel() {
    private val stateMutableLiveData = MutableLiveData<T>()
    val stateLiveData = stateMutableLiveData.toLiveData()

    protected var state by Delegates.observable(initialState) { _, old, new ->
        if (new != old) {
            stateMutableLiveData.value = new
        }
    }

    interface BaseViewState
}