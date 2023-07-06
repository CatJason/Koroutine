/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow38

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking<Unit> {
    (1..5).asFlow().collect { value -> 
        if (value == 3) cancel()  
        println(value)
    } 
}

/**
 * 但是，出于性能原因，大多数其他流操作不会自行执行其他取消检测。
 * 例如，如果使用 IntRange.asFlow 扩展来编写相同的繁忙循环，并且没有在任何地方暂停，那么就没有取消的检测
 */