/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow26

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i) // emit next value
    }
}

fun main() = runBlocking<Unit> {
    try {
        simple().collect { value ->         
            println(value)
            check(value <= 1) { "Collected $value" }
        }
    } catch (e: Throwable) {
        println("Caught $e")
    } 
}

/**
 * 这段代码说明了在使用 Flow 进行数据流处理时，使用 check 函数进行断言检查时可能出现的问题。
 * 在这个例子中，当收集到的值大于 1 时，断言条件不满足，会抛出异常。
 * 通过捕获和处理异常，我们可以在出现问题时进行适当的处理和回退。
 */