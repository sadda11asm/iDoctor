package org.kotlin.mpp.mobile.domain

import com.squareup.sqldelight.db.SqlDriver
import kotlin.coroutines.CoroutineContext

expect val defaultDispatcher: CoroutineContext

expect val uiDispatcher: CoroutineContext