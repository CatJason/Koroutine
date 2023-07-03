/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from channels.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleChannel03

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
    for (x in 1..5) send(x * x)
}

fun main() = runBlocking {
    val squares = produceSquares()
    squares.consumeEach { println(it) }
    println("Done!")
}

/**
 * 将生产者抽象成一个函数，并且使通道作为它的参数
 * 使用 produce ：便捷的协程构建器
 * 使用扩展函数 consumeEach 在消费者端替代 for 循环
 */