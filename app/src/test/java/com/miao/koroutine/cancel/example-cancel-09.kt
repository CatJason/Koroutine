/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from cancellation-and-timeouts.md by Knit tool. Do not edit.
package com.miao.koroutine.cancel

import kotlinx.coroutines.*

var acquired = 0

class Resource {
    init {
        acquired++
    } // Acquire the resource
    fun close() { acquired-- } // Release the resource
}

fun main() {
    runBlocking {
        repeat(10_000) { // Launch 10K coroutines
            launch { 
                val resource = withTimeout(60) { // Timeout of 60 ms
                    delay(50) // Delay for 50 ms
                    Resource() // Acquire a resource and return it from withTimeout block
                }
                resource.close() // Release the resource
            }
        }
    }
    // Outside of runBlocking all coroutines have completed
    println(acquired) // Print the number of resources still acquired
}

/**
 * 这段代码是一个协程取消的示例，通过设置超时时间来限制协程的执行时间。
 * 在 `main` 函数中，使用 `runBlocking` 创建一个协程作用域。在该作用域中，通过 `repeat(10_000)` 启动了 10,000 个协程。
 * 每个协程都通过 `launch` 函数启动，并在其中使用 `withTimeout` 函数来设置一个超时时间。
 * 在这个例子中，超时时间被设置为 60 毫秒。
 * 如果超过该时间，`withTimeout` 函数会抛出 `TimeoutCancellationException` 异常。
 * 在 `withTimeout` 的代码块中，
 * 通过 `delay(50)` 函数进行了一个延迟操作（模拟协程的计算任务），然后通过 `Resource()` 构造函数获取了一个资源。
 * 如果在超时时间内成功获取到资源，就会继续执行后续代码，然后通过 `resource.close()` 释放资源。
 * 在 `runBlocking` 作用域之外，打印了 `acquired` 的值，表示还未释放的资源数量。
 * 这段代码的目的是模拟同时启动大量协程并设置超时的情况。
 * 在超时时间内能够成功获取资源的协程会继续执行，超时时间到达后会抛出异常并取消协程。
 * 通过这种方式，可以控制并发操作的执行时间，并防止资源被长时间占用或出现死锁等问题。
 * 在最后的输出中，打印了 `acquired` 的值，即还未释放的资源数量。
 */

/**
 * 代码的关键在于线程并没有等待协程，与协程是并发执行的
 * 也就是说，如果 60ms 后如果线程执行完了，则 runBlocking 中的代码根本不会被执行
 * 大量协程与线程抢占处理器，导致线程延迟执行超过 60 ms
 * 这才有了部分协程执行的机会
 * 由于大量协程并发执行，部分协程在并发执行完 Resource() 后，线程就执行完了，来不及 resource.close()
 *
 * 如果将 60ms 改为 6000ms，50ms 改为 5000ms，增 acquired = 0
 */