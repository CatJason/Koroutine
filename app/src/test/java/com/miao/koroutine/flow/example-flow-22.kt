/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow22

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.System.currentTimeMillis

fun main() = runBlocking<Unit> { 
    val nums = (1..3).asFlow().onEach {
        delay(300)
    } // numbers 1..3 every 300 ms

    val strs = flowOf("one", "two", "three").onEach {
        delay(400)
    } // strings every 400 ms

    val startTime = currentTimeMillis() // remember the start time

    nums.combine(strs) { a, b -> "$a -> $b" } // compose a single string with "combine"
        .collect { value -> // collect and print 
            println("$value at ${currentTimeMillis() - startTime} ms from start") 
        } 
}

/**
 * 使用 combine，只有第一次打印的时机是二者都至少完成了一次的时间
 * 快的那个可能已经完成了 1-N 次，慢的才完成第一次
 * 这时候将快的第 N 次的结果和慢的第一次的结果组合打印出来
 * 之后二者每有一个产生了新的数据，就将二者最新的数据组合打印出来
 */