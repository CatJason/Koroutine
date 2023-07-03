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
 * 协程中的大部分操作都是向 CoroutineScope 中增加扩展函数实现的
 * 意识到 Job 就是 CoroutineScope 对我们很重要
 * 拿到了 CoroutineScope 我们就能对协程进行任意操作
 *
 * join() 依次执行
 * 运行 block: suspend CoroutineScope.() -> Unit 函数
 * 检查是否完成
 * 未完成执行挂起函数，将线程挂起
 *
 * 所以 join() 函数是线程中检查子协程是否完成以决定是否挂起的函数
 * join 有加入的意思，将一个 job 加入线程的子协程池
 * job 在加入线程的子线程池后，会判断是否要阻塞线程
 *
 * 注意 join 函数并不是启动函数，launch 函数才是启动函数
 */