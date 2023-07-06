/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow25

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.System.currentTimeMillis

fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First") 
    delay(500) // wait 500 ms
    emit("$i: Second")    
}

fun main() = runBlocking<Unit> { 
    val startTime = currentTimeMillis() // remember the start time 
    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms 
        .flatMapLatest { requestFlow(it) }                                                                           
        .collect { value -> // collect and print 
            println("$value at ${currentTimeMillis() - startTime} ms from start") 
        } 
}

/**
 * flatMapLatest操作符的行为类似于flatMapMerge，但有一个重要的区别。
 * flatMapLatest只关注源流的最新元素，忽略之前的元素。
 */

/**
 * 与flatMapMerge和flatMapConcat不同，flatMapLatest不会等待前一个内部流完成。
 * 它会立即切换到最新的内部流，并在处理完成后将其结果合并到输出流中。
 * 这可以提高响应性，并且对于处理频繁变化的数据流非常有用。
 */
