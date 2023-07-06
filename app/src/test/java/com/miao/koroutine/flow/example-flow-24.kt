/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow24

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
        .flatMapMerge { requestFlow(it) }                                                                           
        .collect { value -> // collect and print 
            println("$value at ${currentTimeMillis() - startTime} ms from start") 
        } 
}
/**
 * .flatMapMerge { requestFlow(it) } = map(requestFlow(it)).flattenMerge(concurrency)
 */

/**
 * flatMapMerge 会顺序调用代码块（本示例中的 { requestFlow(it) }）
 * 但是并发收集结果流，相当于执行顺序是首先执行 map { requestFlow(it) } 然后在其返回结果上调用 flattenMerge。
 */

/**
 * flatMapMerge
 * flatMapMerge操作符在处理源流时会并发地处理内部流，不保证内部流的顺序。
 * 它会同时订阅和处理多个内部流，然后将它们的结果合并到单个输出流中。
 * 这种并发处理可以提高处理速度，但内部流的顺序可能会被打乱。
 *
 * flatMapConcat
 * flatMapConcat操作符在处理源流时会按照顺序逐个订阅和处理内部流，确保内部流的顺序保持不变。
 * 它会等待前一个内部流完成后才会处理下一个内部流，确保按照顺序合并结果。
 * 这种顺序处理可能会导致处理速度较慢，因为它必须等待每个内部流完成。
 */
