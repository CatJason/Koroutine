/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from exception-handling.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleExceptions04

import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception -> 
        println("CoroutineExceptionHandler got $exception") 
    }
    val job = GlobalScope.launch(handler) {
        launch { // the first child
            try {
                delay(Long.MAX_VALUE)
            } finally {
                withContext(NonCancellable) {
                    println("Children are cancelled, but exception is not handled until all children terminate")
                    delay(100)
                    println("The first child finished its non cancellable block")
                }
            }
        }
        launch { // the second child
            delay(10)
            println("Second child throws an exception")
            throw ArithmeticException()
        }
    }
    job.join()
}

/**
 * 如果一个协程遇到了 CancellationException 以外的异常，它将使用该异常取消它的父协程
 * 当父协程的所有子协程都结束后，原始的异常才会被父协程处理
 */

/**
 * 在这个例子中，CoroutineExceptionHandler 总是被设置在由 GlobalScope 启动的协程中
 * 并没有设置在 runBlocking 的主作用域内启动的协程中。
 * 这是因为在 runBlocking 的作用域中，如果主协程被取消（比如发生了未捕获的异常），那么它会取消所有的子协程，包括设置了异常处理者的子协程。
 *
 * 在理解这个行为时，需要注意以下几点：
 * runBlocking 是一个阻塞式的协程构建器，它会等待其作用域内的所有协程执行完毕才会继续执行下去。
 * 如果主协程被取消，那么 runBlocking 会立即取消所有的子协程，并且会将取消原因传递给它们。
 * GlobalScope 是一个全局作用域，其中的协程不会受到外部作用域的影响。
 * 当在 GlobalScope 中启动一个协程时，它的生命周期与应用程序的生命周期无关，它可以在应用程序的任何地方执行。
 * 设置 CoroutineExceptionHandler 在 GlobalScope 中启动的协程可以确保在协程发生异常时，能够进行适当的异常处理。
 * 因此，在这个例子中，通过在 GlobalScope 启动的协程中设置 CoroutineExceptionHandler，可以确保即使主协程被取消，设置了异常处理者的子协程仍然能够执行自定义的异常处理逻辑。
 * 而如果将异常处理者设置在 runBlocking 的主作用域内启动的协程中，尽管子协程已经设置了异常处理者，但是主协程被取消时，所有的子协程都会被取消，异常处理者也无法得到执行。
 * 因此，在这个例子中，设置异常处理者在 GlobalScope 启动的协程中是有意义的，可以确保子协程的异常处理能够生效。
 */

