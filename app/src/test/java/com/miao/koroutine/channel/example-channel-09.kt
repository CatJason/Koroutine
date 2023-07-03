/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from channels.md by Knit tool. Do not edit.
package com.miao.koroutine.channel

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

data class Ball(var hits: Int)

fun main() = runBlocking {
    val table = Channel<Ball>() // a shared table
    launch { player("ping", table) }
    launch { player("pong", table) }
    table.send(Ball(0)) // serve the ball
    delay(1000) // delay 1 second
    coroutineContext.cancelChildren() // game over, cancel them
}

suspend fun player(name: String, table: Channel<Ball>) {
    for (ball in table) { // receive the ball in a loop
        ball.hits++
        println("$name $ball")
        delay(300) // wait a bit
        table.send(ball) // send the ball back
    }
}

/**
 * 在协程中，通道可以被认为是"公平"的
 * 这意味着多个协程在竞争接收通道中的元素时，它们将按照先后顺序逐个获得元素
 * 而不会出现某个协程一直占据通道而其他协程无法获取的情况
 *
 * ping，pong，ping，pong，ping，pong
 */