/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow23

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.System.currentTimeMillis

fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First") 
    delay(500) // wait 500 ms
    emit("$i: Second")    
}

/**
 * flatMapConcat {...}
    fun main() = runBlocking<Unit> {
        val flowOfFlows: Flow<Flow<Int>> = flowOf(flowOf(1, 2), flowOf(3, 4))
        val flatMapConcatResult = flowOfFlows.flatMapConcat { it }
        flatMapConcatResult.collect { println(it) } // 输出：1, 2, 3, 4
    }
 * flattenConcat()
    fun main() = runBlocking<Unit> {
        val flowOfFlows: Flow<Flow<Int>> = flowOf(flowOf(1, 2), flowOf(3, 4))
        val flattenConcatResult = flowOfFlows.flattenConcat()
        flattenConcatResult.collect { println(it) } // 输出：1, 2, 3, 4
    }
 */

fun main() = runBlocking<Unit> {
    val startTime = currentTimeMillis() // remember the start time
    (1..3).asFlow().onEach { delay(100) } // emit a number every 100 ms
        .flatMapConcat { requestFlow(it) } // 等价于 map(requestFlow(it)).flattenConcat()
        .collect { value -> // collect and print
            println("$value at ${currentTimeMillis() - startTime} ms from start")
        }
}

/**
 * flatMapConcat操作符用于将原始Flow中的每个元素转换为另一个Flow，并将这些Flow连接和扁平化成一个新的Flow。
 *
 * requestFlow(it)：这是一个转换函数，它接收初始 Flow 中的每个元素，并返回一个新的 Flow。
 * flatMapConcat 接收一个 Flow，返回 这个 Flow = map(requestFlow(it))
 * .flatMapConcat { requestFlow(it) } = map(requestFlow(it)).flattenConcat()
 */




