/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from coroutines-basics.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleBasic05

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch { // launch a new coroutine and keep a reference to its Job
        delay(1000L)
        println("World!")
    }
    println("Hello")
    job.join() // wait until child coroutine completes
    println("Done") 
}

/**
 * launch 的返回值是一个 Job 接口的实现类
 * Job 接口继承自 CoroutineContext.Element 接口
 * CoroutineContext.Element 接口继承自 CoroutineContext 接口
 *
 * join() 依次执行
 * 运行 block: suspend CoroutineScope.() -> Unit 函数
 * 检查是否完成
 * 未完成执行挂起函数
 */