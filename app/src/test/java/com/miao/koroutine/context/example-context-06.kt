/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from coroutine-context-and-dispatchers.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleContext06

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    // launch a coroutine to process some kind of incoming request
    val request = launch {
        // it spawns two other jobs
        launch(Job()) { 
            println("job1: I run in my own Job and execute independently!")
            delay(1000)
            println("job1: I am not affected by cancellation of the request")
        }
        // and the other inherits the parent context
        launch {
            delay(100)
            println("job2: I am a child of the request coroutine")
            delay(1000)
            println("job2: I will not execute this line if my parent request is cancelled")
        }
    }
    delay(500)
    request.cancel() // cancel processing of the request
    println("main: Who has survived request cancellation?")
    delay(1000) // delay the main thread for a second to see what happens
}

/**
 * 在主协程中，通过 launch 函数启动了一个新的协程，该协程用于处理请求。
 * 该协程内部又启动了两个子协程。
 *
 * 第一个子协程通过 launch(Job()) 创建，并传入了一个新的 Job 对象作为参数。
 * 这意味着该子协程具有独立的 Job，与父协程的 Job 没有关联。
 * 在该子协程中，我们打印了两条消息，并使用 delay 函数模拟了一些工作。
 * 注意，即使父协程的请求被取消，该子协程仍然会继续执行，并打印出最后一条消息。
 *
 * 第二个子协程没有传递任何参数给 launch 函数，因此它会继承父协程的上下文，包括父协程的 Job。
 * 在该子协程中，我们打印了一条消息，并使用 delay 函数模拟了一些工作。
 * 然后，我们打印了一条消息，该消息只会在父协程的请求没有被取消时才会执行。
 * 如果父协程的请求被取消，那么该子协程会被取消，且不会打印最后一条消息。
 */