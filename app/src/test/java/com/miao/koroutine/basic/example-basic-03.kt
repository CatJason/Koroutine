/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from coroutines-basics.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleBasic03

import kotlinx.coroutines.*

fun main() = runBlocking {
    doWorld()
}

suspend fun doWorld() = coroutineScope {  // this: CoroutineScope
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello")
}

/**
 * 这里说的是 coroutineScope 函数的使用
 * coroutineScope 函数是 CoroutineScope 的函数
 * coroutineScope 函数入参 block: suspend CoroutineScope.() -> R
 * 它的作用是给 CoroutineScope 增加一个带返回值的扩展函数，
 * 就和 runBlocking 函数的 block 参数重新 set 一遍作用差不多，在我看来和 setBlock 也没什么区别
 */