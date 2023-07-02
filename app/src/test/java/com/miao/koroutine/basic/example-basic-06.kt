/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from coroutines-basics.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleBasic06

import kotlinx.coroutines.*

fun main() = runBlocking {
    repeat(50_000) { // launch a lot of coroutines
        launch {
            delay(5000L)
            print(".")
        }
    }
}

/**
 * 这段代码主要是说明协程非常的轻量
 */

/**
 * 这是个笑话
 *
 * 在Kotlin协程的背后，使用的是一个单线程中的线程池来调度协程的执行。
 * 线程池会维护一定数量的线程，通过协程调度器（Coroutine Dispatcher）来切换和调度协程的执行。
 * 因此，实际运行的始终是线程池中的那几个线程，而不是真实创建了10万个线程。
 * 当然不会OOM，它的所谓的轻量也就无从谈起。
 */