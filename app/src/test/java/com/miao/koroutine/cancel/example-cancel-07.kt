/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from cancellation-and-timeouts.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleCancel07

import kotlinx.coroutines.*

fun main() = runBlocking {
    withTimeout(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
}

/**
 * withTimeout(time)
 * 到时间后会抛出 kotlinx.coroutines.TimeoutCancellationException 异常
 * 需要包裹在 try {...} catch (e: TimeoutCancellationException) {...} 中使用
 */