/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from channels.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleChannel04

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() = runBlocking {
    val numbers = produceNumbers() // produces integers from 1 and on
    val squares = square(numbers) // squares integers
    repeat(5) {
        println(squares.receive()) // print first five
    }
    println("Done!") // we are done
    coroutineContext.cancelChildren() // cancel children coroutines
}

fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1
    while (true) send(x++) // infinite stream of integers starting from 1
}

fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
    for (x in numbers) send(x * x)
}

/**
 * 创建一个 produce numbers，无限的从 1 到 无穷 生产整数
 * 创建一个 produce square，接收 numbers 作为 ReceiveChannel ，并进行处理
 * squares.receive() 只曲其前 5
 * 在 numbers，squares 挂起等待消费者时取消掉（注意协程的 cancel 机制，没有主动响应的只有在挂起时才能取消掉）
 *
 * numbers 是一个管道：一种一个协程在流中开始生产可能无穷多个元素的模式
 */

/**
 * coroutineContext.cancelChildren()是一个协程上下文的扩展函数，用于取消该协程上下文中的所有子协程。
 * 在这个示例中，main()函数创建了一个顶级协程作用域，其中包含了创建的生产者通道和接收者通道的协程。
 * 当执行到coroutineContext.cancelChildren()时，它会取消所有子协程，即 numbers 和squares协程。
 */