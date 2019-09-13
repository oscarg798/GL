package com.reyesmagos.gl.core

import kotlin.coroutines.CoroutineContext

data class CoroutineContextProvider(
    val mainContext: CoroutineContext,
    val backgroundContext: CoroutineContext
)
