/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from cancellation-and-timeouts.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleCancel01

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        repeat(1000) { i ->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancel() // cancels the job
    job.join() // waits for job's completion 
    println("main: Now I can quit.")
}

/**
 * 正确的理解是这样
 * 调用 launch 函数执行传入的 CoroutineScope 的扩展函数，并返回 CoroutineScope 的子类 Job
 * 协程开始执行
 * 线程挂起，协程保持运行
 * 线程唤醒
 * 通过 job.cancel() 主动取消协程的运行
 * job.cancel() 调用之后，协程并不是立刻结束运行的，
 * 通过 job.join() 将线程加入到协程的执行当中，即协程执行完之后执行线程的内容
 * 因为协程相关内容已经被主动取消，故只需执行后加入的线程的内容即可
 */

/**
 * 这个协程可以被取消是因为在它的执行过程中包含了可取消的挂起函数 delay
 * delay 函数是一个可取消的挂起函数，它会暂停协程的执行一段时间
 * 但是在暂停期间，如果协程的取消被请求，它会立即抛出 CancellationException 异常，中断协程的执行
 */