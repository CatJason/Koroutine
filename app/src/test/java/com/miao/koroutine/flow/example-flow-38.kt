/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow38

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking<Unit> {
    (1..5).asFlow().collect { value -> 
        if (value == 3) cancel()  
        println(value)
    } 
}

/**
 * 但是，出于性能原因，大多数其他流操作不会自行执行其他取消检测。
 * 例如，如果使用 IntRange.asFlow 扩展来编写相同的繁忙循环，并且没有在任何地方暂停，那么就没有取消的检测
 */

/**
 * owOn 操作之后进行取消操作，整个流的处理将被取消，并且后续的操作不会执行。
 *
 * 以下是一些不会执行取消检测的操作符：
 * map、filter、flatMap 等转换操作符：这些操作符只是对流中的元素进行转换、过滤或扁平化等处理，
 * 它们不会触发取消检测。即使在这些操作符之后调用取消操作，也不会立即取消流的处理。
 * 流的处理会继续进行，直到遇到触发取消检测的操作符。
 *
 * onEach、forEach：这些操作符用于对流中的每个元素执行副作用操作，例如打印日志或发送网络请求。
 * 它们不会触发取消检测，即使在这些操作符之后调用取消操作，也不会立即取消流的处理。
 */