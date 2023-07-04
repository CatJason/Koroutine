/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from coroutine-context-and-dispatchers.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleContext02

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
    }
    launch { // context of the parent, main runBlocking coroutine
        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
    }
}

/**
 * 当使用 Dispatchers.Unconfined 调度器时
 * 协程会立即在调用者的线程中开始执行
 * 直到遇到第一个挂起点
 * 然后，协程的执行将会在恢复它的挂起点的线程上继续，这可能是不同的线程
 *
 * 这种特性使得 Dispatchers.Unconfined 在某些特定情况下非常有用。
 * 例如，当需要在某个具体的上下文中执行一段协程代码时
 * 但又不想受限于特定的线程池或调度器时，可以使用 Dispatchers.Unconfined。
 */
