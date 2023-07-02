/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from coroutines-basics.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleBasic01

import kotlinx.coroutines.*

fun main() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello") // main coroutine continues while a previous one is delayed
}

// runBlocking 使用顶层函数，用于启动一个新的协程并阻塞当前线程，直到该协程执行完成。
// 阻塞当前协程的意思是：我这里面协程不完成，你这个线程就别想结束
// 在其中延迟一秒，打印 World！
// 打印 Hello

/**
 * 简易协程函数
 */

/**
 * 知识点
 * runBlocking 是一个函数
 * runBlocking 会阻塞自己所在的线程
 *
 * runBlocking 函数的两个入参 context: CoroutineContext, block: suspend CoroutineScope.() -> T
 * @param context: CoroutineContext 上下文接口的实现类，在 Kotlin 中是有默认实现的，看 Java 代码应该是个 null
 * @param block: suspend CoroutineScope.() -> T 注意，这是一个 CoroutineScope 的扩展函数做为入参
 *
 * CoroutineScope.launch 函数
 * @param context: CoroutineContext = EmptyCoroutineContext
 * @param start: CoroutineStart = CoroutineStart.DEFAULT
 * @param block: suspend CoroutineScope.() -> Unit
 *
 * 二者 block 函数的异同点
 * 不同点
 * runBlocking 的 block 参数是有返回数值的
 * launch 的 block 参数是没有返回数值的
 * 相同点
 * 都是 CoroutineScope 的扩展函数，都是在 CoroutineScope 内部运行的
 *
 * runBlocking 的 block 函数有返回值是因为在协程中经常需要执行一些异步操作，并且希望能够获取到异步操作的结果。
 */