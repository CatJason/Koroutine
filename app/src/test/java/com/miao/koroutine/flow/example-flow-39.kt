/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow39

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking<Unit> {
    (1..5).asFlow().cancellable().collect { value -> 
        if (value == 3) cancel()  
        println(value)
    } 
}

/**
 * 在协程处于繁忙循环的情况下，必须明确检测是否取消。
 * 可以添加 .onEach { currentCoroutineContext().ensureActive() }
 * 但是这里提供了一个现成的 cancellable 操作符来执行此操作
 */
