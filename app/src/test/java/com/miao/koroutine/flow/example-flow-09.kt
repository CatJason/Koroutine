/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow09

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

suspend fun performRequest(request: Int): String {
    delay(1000) // imitate long-running asynchronous work
    return "response $request"
}

fun main() = runBlocking<Unit> {
    (1..3).asFlow() // a flow of requests
        .transform { request ->
            emit("Making request $request") 
            emit(performRequest(request)) 
        }
        .collect { response -> println(response) }
}

/**
 * 在流转换操作符中，最通用的一种称为 transform。
 * 它可以用来模仿简单的转换，例如 map 与 filter，以及实施更复杂的转换。
 * 使用 transform 操作符，我们可以 发射 任意值任意次。
 */

/**
 * 我们不能直接使用emit函数多次发射元素，是指一次发送和挂起之间只能发射一个元素。
 */

/**
 * 对数据流的转换作用是指在Flow中对数据进行修改、过滤、映射或其他操作，以产生经过处理的新数据流。
 * 当我们对数据流进行转换时，我们可以根据需求和业务逻辑对每个元素进行处理，从而得到具有不同形式或状态的新数据流。
 * 具体而言，对数据流的转换作用可以包括以下方面：
 *
 * 映射（Mapping）：将数据流中的每个元素映射为另一种形式或类型。例如，将一个整数流映射为对应的字符串流，或者将对象流映射为其中的某个属性的流。
 * 过滤（Filtering）：根据某个条件过滤数据流中的元素，只保留满足条件的元素。例如，过滤掉奇数或根据某个属性进行筛选。
 * 转换（Transformation）：对数据流中的元素进行计算、处理或转换，生成新的元素。这可能涉及对元素进行计算、排序、聚合等操作。
 * 组合（Combining）：将多个数据流合并为一个数据流，或者根据某个规则将多个数据流进行组合。例如，将两个流按照某个条件进行合并或连接。
 * 排序（Sorting）：对数据流中的元素进行排序，使其按照指定的顺序排列。
 */