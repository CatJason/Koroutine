/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from channels.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleChannel01

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() = runBlocking {
    val channel = Channel<Int>()
    launch {
        // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
        for (x in 1..5) channel.send(x * x)
    }
    // here we print five received integers:
    repeat(5) { println(channel.receive()) }
    println("Done!")
}

/**
 * 在协程中使用 channel send 信息，在线程中 使用 channel receive 逐一接收
 */

/**
 * Channel<Int> 是一个函数
 * @param capacity：通道的容量，即可以存储多少个元素，默认值为 RENDEZVOUS，表示零容量的“会合”通道，即发送者和接收者必须同时准备好。
 * @param onBufferOverflow：缓冲区溢出时的处理方式，默认为 BufferOverflow.SUSPEND，表示当缓冲区已满时，发送操作会挂起。
 * @param onUndeliveredElement：未发送的元素的处理方式，是一个回调函数，默认为 null，表示不做任何处理。
 *
 * 在零容量的“会合”通道中，发送者和接收者必须彼此同步，意味着它们需要相互等待对方的准备。
 * 当发送者尝试向通道发送数据时，它必须等待接收者准备好接收数据。
 * 如果接收者尚未准备好，发送者将被阻塞，直到接收者准备好接收。
 * 这确保了发送者在发送数据之前，接收者已经就绪，可以安全地接收数据。
 *
 * 也就是说线程和协程在使用零容量的“会合”通道是吗，构成了生产者和消费者的关系
 */

/**
 * UNLIMITED：请求一个无限容量缓冲区的通道，在 Channel(...) 工厂函数中使用。
 *
 * RENDEZVOUS：请求一个“会合”通道，在 Channel(...) 工厂函数中使用，表示该通道没有缓冲区。
 *
 * CONFLATED：请求一个合并通道，在 Channel(...) 工厂函数中使用。
 *            这是一个快捷方式，用于创建一个具有
 *            [onBufferOverflow = DROP_OLDEST][BufferOverflow.DROP_OLDEST] 的通道。
 *
 * BUFFERED：请求一个具有默认缓冲区容量的缓冲通道，在 Channel(...) 工厂函数中使用。
 *           对于在溢出时会 [挂起][BufferOverflow.SUSPEND] 的通道，默认容量为 64，
 *           可以通过在 JVM 上设置 [DEFAULT_BUFFER_PROPERTY_NAME] 来更改默认容量。
 *           对于非挂起通道，使用容量为 1 的缓冲区。
 *
 * OPTIONAL_CHANNEL：仅用于内部使用，不能在 Channel(...) 中使用。
 *
 * DEFAULT_BUFFER_PROPERTY_NAME：定义使用 BUFFERED 参数时默认通道容量的属性名称。
 *
 * CHANNEL_DEFAULT_CAPACITY：通道的默认容量，
 *                           根据系统属性 [DEFAULT_BUFFER_PROPERTY_NAME] 进行设置，默认为 64。
 */