/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow12

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking<Unit> {
    (1..5).asFlow()
        .filter {
            println("Filter $it")
            it % 2 == 0              
        }              
        .map {
            println("Map $it")
            "string $it"
        }.collect { 
            println("Collect $it")
        }    
}

/**
 * 流是连续的
 */

/**
 * Fow发射1，被过滤掉。
 * Flow发射2，满足条件，打印"Filter 2"。
 * Flow发射3，被过滤掉。
 * Flow发射4，满足条件，打印"Filter 4"。
 * Flow发射5，被过滤掉。
 * Flow发射2，应用映射操作，打印"Map 2"。
 * Flow发射4，应用映射操作，打印"Map 4"。
 * 收集到元素2，打印"Collect string 2"。
 * 收集到元素4，打印"Collect string 4"
 */


