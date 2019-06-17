package org.kotlin.mpp.mobile.domain

import kotlinx.coroutines.Dispatchers
import sun.rmi.server.Dispatcher
import kotlin.coroutines.CoroutineContext

actual val defaultDispatcher: CoroutineContext
    get() = Dispatchers.Default
actual val uiDispatcher: CoroutineContext
    get() = Dispatchers.Main