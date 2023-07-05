/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow11

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking<Unit> {
    val sum = (1..5).asFlow()
        .map { it * it } // squares of numbers from 1 to 5                           
        .reduce { a, b -> a + b } // sum them (terminal operator)
    println(sum)
}

/**
 * 末端操作符是在流上用于启动流收集的挂起函数。
 * collect 是最基础的末端操作符，但是还有另外一些更方便使用的末端操作符：
 */

/**
 * toList 与 toSet
 * 转化为各种集合
 *
 * first
 * first 操作符用于获取Flow中的第一个元素，并返回一个新的Flow，该Flow只包含第一个元素。
 *
 * single
 * single 操作符用于确保Flow只发射单个元素，并返回该元素。
 * 说人话就是用于Flow中只有一个数据的情况
 *
 * reduce
 * 说人话就是可以用来累加，累乘，前提是按照一个统一的规则。
 *
 * fold
 * 说人话就是多了一个起始数值，就相当于在最前面加了一个数
 */
