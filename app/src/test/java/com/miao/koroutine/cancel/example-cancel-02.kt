/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from cancellation-and-timeouts.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleCancel02

import kotlinx.coroutines.*
import java.lang.System.currentTimeMillis

fun main() = runBlocking {
    val startTime = currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) { // computation loop, just wastes CPU
            // print a message twice a second
            if (currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

/**
 * job.cancelAndJoin() 就是 job.cancel() job.join()
 */

/**
 * 在协程执行计算任务时，如果协程没有显式地检查取消状态
 * （通过调用isActive属性或在适当的地方使用suspendCancellableCoroutine函数）
 * 那么协程将无法被取消
 *
 * 协程的取消是基于协作的机制，它依赖于协程在执行期间主动检查取消状态并作出相应的响应
 * 如果协程没有在适当的时候检查取消状态，它将继续执行计算任务，无法感知到外部对其取消的请求，因此无法被取消
 */