/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from coroutines-basics.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleBasic02

import kotlinx.coroutines.*

fun main() = runBlocking { // this: CoroutineScope
    launch { doWorld() }
    println("Hello")
}

// this is your first suspending function
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}

// runBlocking 使用顶层函数，用于启动一个新的协程并阻塞当前线程，直到该协程执行完成。
// 阻塞当前协程的意思是：我这里面协程不完成，你这个线程就别想结束
// doWorld() 延迟一秒打印 World！
// 直接打印 Hello

/**
 * 是讲 suspend 函数的使用
 */
