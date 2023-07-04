/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from exception-handling.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleExceptions03

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        val child = launch {
            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("Child is cancelled")
            }
        }
        yield()
        println("Cancelling child")
        child.cancel()
        child.join()
        yield()
        println("Parent is not cancelled")
    }
    job.join()
}

/**
 * 当一个协程使用 Job.cancel() 取消的时候，它会被终止，但是它不会取消它的父协程。
 */

/**
 * yield() 函数通常不需要显式地调用
 * 因为协程的挂起函数（如 delay()、suspendCancellableCoroutine() 等）已经内置了适当的挂起点，会自动让出执行权。
 * yield() 主要在编写自定义的协程构建器或调度器时使用，以实现更精细的控制和调度策略。
 */