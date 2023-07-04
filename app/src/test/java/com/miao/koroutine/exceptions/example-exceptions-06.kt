/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from exception-handling.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleExceptions06

import kotlinx.coroutines.*
import java.io.*

@OptIn(DelicateCoroutinesApi::class)
fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    val job = GlobalScope.launch(handler) {
        val inner = launch { // all this stack of coroutines will get cancelled
            launch {
                launch {
                    throw IOException() // the original exception
                }
            }
        }
        try {
            inner.join()
        } catch (e: CancellationException) {
            println("Rethrowing CancellationException with original cause")
            throw e // cancellation exception is rethrown, yet the original IOException gets to the handler  
        }
    }
    job.join()
}

/**
 * 在 Kotlin 协程中，默认情况下，取消异常是透明的并且未被包装。
 * 当一个协程被取消时，它会抛出一个 CancellationException 异常。
 * 这个异常并不会被包装在任何其他异常类型中，而是以原始的 CancellationException 形式抛出。
 * 这种取消异常的透明性意味着你可以通过捕获 CancellationException 来判断协程是否被取消，并在需要的时候进行相应的处理。
 */
