/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow14

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
                      
fun simple(): Flow<Int> = flow {
    // The WRONG way to change context for CPU-consuming code in flow builder
    kotlinx.coroutines.withContext(Dispatchers.Default) {
        for (i in 1..3) {
            Thread.sleep(100) // pretend we are computing it in CPU-consuming way
            emit(i) // emit next value
        }
    }
}

fun main() = runBlocking<Unit> {
    simple().collect { value -> println(value) } 
}            

/**
 * 长时间运行的消耗 CPU 的代码也许需要在 Dispatchers.Default 上下文中执行，
 * 并且更新 UI 的代码也许需要在 Dispatchers.Main 中执行
 * 通常，withContext 用于在 Kotlin 协程中改变代码的上下文
 * 但是 flow {...} 构建器中的代码必须遵循上下文保存属性，并且不允许从其他上下文中发射（emit）
 */