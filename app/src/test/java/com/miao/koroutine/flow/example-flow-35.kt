/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow35

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// Imitate a flow of events
fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(100) }

fun main() = runBlocking<Unit> {
    events()
        .onEach { event -> println("Event: $event") }
        .collect() // <--- Collecting the flow waits
    println("Done")
}

/**
 * 我们需要一个类似 addEventListener 的函数，该函数注册一段响应的代码处理即将到来的事件，并继续进行进一步的处理。
 * onEach 操作符可以担任该角色。
 *
 * 然而，onEach 是一个过渡操作符。
 * 我们也需要一个末端操作符来收集流。
 * 否则仅调用 onEach 是无效的。
 *
 * 如果我们在 onEach 之后使用 collect 末端操作符，那么后面的代码会一直等待直至流被收集。
 */