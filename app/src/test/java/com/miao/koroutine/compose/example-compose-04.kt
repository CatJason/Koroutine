/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from composing-suspending-functions.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleCompose04

import kotlinx.coroutines.*
import kotlin.system.*

// note that we don't have `runBlocking` to the right of `main` in this example
fun main() {
    val time = measureTimeMillis {
        // we can initiate async actions outside of a coroutine
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()
        // but waiting for a result must involve either suspending or blocking.
        // here we use `runBlocking { ... }` to block the main thread while waiting for the result
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time ms")
}

@OptIn(DelicateCoroutinesApi::class)
fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne()
}

@OptIn(DelicateCoroutinesApi::class)
fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

/**
 * GlobalScope.async {...}
 * 是别的协程中非常受欢迎的一种风格
 *
 * 如果在代码中的 val one = somethingUsefulOneAsync() 这一行和 one.await() 表达式之间存在逻辑错误
 * 并且程序抛出异常并终止，会发生以下情况：
 *
 * 全局的异常处理程序会捕获异常并处理，通常会将异常记录到日志并报告给开发者。
 * 这样可以避免程序完全崩溃，而是继续执行其他操作。由于发生了异常并终止，启动 somethingUsefulOneAsync() 的操作也会被终止。
 * 尽管 somethingUsefulOneAsync 在后台执行，但是它不会继续执行下去。
 * 程序不会进行结构化并发，即后续的并发操作将不会执行。
 * 这是因为在发生异常和终止后，程序的控制流被打断，后续的操作无法继续执行。
 * 因此，如果在程序中存在逻辑错误并抛出异常，对于后台执行的异步任务，它的执行将被终止，而程序的并发结构也会受到影响。
 */

/*
fun main() {
    val one = somethingUsefulOneAsync() // 这里存在逻辑错误
    // 其他操作...
    val result = runBlocking {
        one.await() // 这里可能抛出异常
    }
    println(result)
}
 */
