/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from channels.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleChannel10

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() = runBlocking<Unit> {
    val tickerChannel = ticker(delayMillis = 100, initialDelayMillis = 0) // create ticker channel
    var nextElement = withTimeoutOrNull(1) { tickerChannel.receive() }
    println("立取元素毫不等待: $nextElement，能取到值是因为初始延迟 initialDelayMillis = 0") // no initial delay

    nextElement = withTimeoutOrNull(50) { tickerChannel.receive() } // all subsequent elements have 100ms delay
    println("在 50ms 内，下一个元素尚未准备好。: $nextElement，因为生产一个元素需要的等待时间 delayMillis = 100")

    nextElement = withTimeoutOrNull(60) { tickerChannel.receive() }
    println("在 110ms 内，下一个元素已经准备好: $nextElement")

    // Emulate large consumption delays
    println("消费者暂停 150ms")
    delay(150)
    // Next element is available immediately
    nextElement = withTimeoutOrNull(1) { tickerChannel.receive() }
    println("在消费者延迟超过再次等待 150ms 之后，下一个元素立即可用: $nextElement")
    // Note that the pause between `receive` calls is taken into account and next element arrives faster
    nextElement = withTimeoutOrNull(60) { tickerChannel.receive() }
    println("在消费者暂停 210ms 后，下一个元素自然也准备就绪: $nextElement")

    tickerChannel.cancel() // indicate that no more elements are needed
}
/**
 * 通过调整超时时间和消费者的暂停时间，演示了不同情况下 ticker 函数的行为。
 * 实际就是介绍 ticker 函数的两个入参数的含义
 * @param delayMillis
 * @param initialDelayMillis
 */

/**
 * 知识点
 * withTimeoutOrNull(time) 函数可以挂起当前协程，而不会阻塞线程
 */