/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow37

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun foo(): Flow<Int> = flow { 
    for (i in 1..5) {
        println("Emitting $i") 
        emit(i) 
    }
}

fun main() = runBlocking<Unit> {
    foo().collect { value -> 
        if (value == 3) cancel()  
        println(value)
    } 
}

/**
 * 为方便起见，流构建器对每个发射值执行附加的 ensureActive 检测以进行取消。
 * 这意味着从 flow { ... } 发出的繁忙循环是可以取消的
 * 比如可以使用 cancel 函数来取消
 *
 * 尝试发出 4 之后抛出 CancellationException
 */
