package com.example.core.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.observe(
    scope: CoroutineScope,
    action: suspend (T) -> Unit,
    onError: (Throwable) -> Unit
) = this
    .onEach(action)
    .catch { onError(it) }
    .launchIn(scope)