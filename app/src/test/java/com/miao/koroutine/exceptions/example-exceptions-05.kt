/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from exception-handling.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleExceptions05

import kotlinx.coroutines.*
import java.io.*

@OptIn(DelicateCoroutinesApi::class)
fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception with suppressed ${exception.suppressed.contentToString()}")
    }
    val job = GlobalScope.launch(handler) {
        launch {
            try {
                delay(Long.MAX_VALUE) // it gets cancelled when another sibling fails with IOException
            } finally {
                throw ArithmeticException() // the second exception
            }
        }
        launch {
            delay(100)
            throw IOException() // the first exception
        }
        delay(Long.MAX_VALUE)
    }
    job.join()  
}

/**
 * 注意：上面的代码将只在 JDK7 以上支持 suppressed 异常的环境中才能正确工作。
 *
 * 当协程的多个子协程因异常而失败时， 一般规则是“取第一个异常”，因此将处理第一个异常。
 * 在第一个异常之后发生的所有其他异常都作为被抑制的异常绑定至第一个异常。
 */
