/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from exception-handling.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleExceptions01

import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main() = runBlocking {
    val job = GlobalScope.launch { // launch 根协程
        println("在 launch 中抛出异常")
        throw IndexOutOfBoundsException() // 我们将在控制台打印 Thread.defaultUncaughtExceptionHandler
    }
    job.join()
    println("Joined failed job")
    /**
     * "Joined failed job" 表示当前协程会等待根协程（job）的完成，并且会获取根协程执行的结果（即异常）。
     * 无论根协程是正常完成还是抛出了异常，当前协程都会继续执行下去。
     * 在这个例子中，根协程故意抛出了 IndexOutOfBoundsException 异常，而根协程的执行完成是由于抛出了异常而导致的。
     * 因此，"Joined failed job" 表示当前协程等待根协程的完成
     * 即等待根协程抛出异常或正常完成，并在这之后继续执行下去。
     */
    val deferred = GlobalScope.async { // async 根协程
        println("在 async 中抛出异常")
        throw ArithmeticException() // 没有打印任何东西，依赖用户去调用等待
    }
    try {
        deferred.await() // 在 try {...} catch {...} 中手动调用 await() 函数让其捕获异常
        println("Unreached")
    } catch (e: ArithmeticException) {
        println("Caught ArithmeticException")
    }
}

/**
 * 主要是为了对比异常传播的两种形式：
 * 自动传播异常（launch 与 actor）
 * 这类构建器将异常视为未捕获异常，类似 Java 的 Thread.uncaughtExceptionHandler
 * 未捕获异常（Uncaught Exception）是指在程序执行期间抛出的异常，但没有被适当的代码进行捕获和处理。
 * 这样的异常会导致程序的异常终止或不可预期的行为。
 *
 * 向用户暴露异常（async 与 produce）
        try {
            // 可能抛出异常的代码块
            // ...
        } catch (e: Exception) {
            // 处理异常并向用户提供错误信息
            println("发生了错误：${e.message}")
            // 或者显示异常堆栈跟踪信息
        e.printStackTrace()
            // 或者将异常信息记录到日志文件
            // logger.error("发生了错误", e)
            // 其他处理逻辑
            // ...
        }
 */
