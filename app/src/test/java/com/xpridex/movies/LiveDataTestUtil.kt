/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xpridex.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.mockk.spyk
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * Observes a [LiveData] until the `block` is done executing.
 */
fun <T> LiveData<T>.observeForTesting(block: (spiedObserver: Observer<T>, viewStates: MutableList<T>) -> Unit) {
    val observer = spyk(Observer<T> { })
    val viewStateSlots = mutableListOf<T>()
    try {
        observeForever(observer)
        block(observer, viewStateSlots)
    } finally {
        removeObserver(observer)
    }
}