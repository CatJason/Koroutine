/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow13

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
           
fun simple(): Flow<Int> = flow {
    log("Started simple flow")
    for (i in 1..3) {
        emit(i)
    }
}  

fun main() = runBlocking<Unit> {
    simple().collect { value -> log("Collected $value") } 
}

/**
 * "快速执行"指的是对执行速度有较高要求的情况。
 * 这意味着我们希望代码能够尽快执行完毕，以便进行下一步操作。
 * 例如，如果我们只是简单地打印一些日志消息或者执行一些轻量级的计算
 * 我们可能对执行速度非常敏感，因为我们希望这些操作能够尽快完成，而不会引入额外的延迟
 */

/**
 * "对执行上下文不关心"意味着我们对代码在哪个线程或协程上执行并不特别关注。
 * 我们只关心代码的逻辑是否正确，并且不需要考虑并发执行或线程切换等复杂性。
 * 在这种情况下，我们可以使用主线程作为默认执行上下文，因为它是最简单、最直接的方式。
 */

/*
 当你运行这段代码时，整个流程包括创建Flow、应用操作符和收集元素的过程都会在主线程中执行。
 这意味着在调用simple().collect时，整个流的处理以及其中的日志输出都会在主线程中进行。

 需要注意的是，虽然流的处理在主线程中执行，但Flow本身是一种异步的数据流。
 Flow的发射和收集操作是非阻塞的，可以在后台进行，并且不会阻塞主线程。
 这使得在处理大量数据或需要进行异步操作时，Flow可以提供高效的并发性能。

 总结：给定的代码是在主线程中执行的，因为main()函数是程序的入口点，并且被包裹在runBlocking函数中。
 这意味着整个流的创建、操作符应用和元素收集都发生在主线程中。
 但是，Flow本身是异步的，可以在后台进行非阻塞的发射和收集操作。
 */

/**
 * 说白了还是异步并发的，只不过你只要在主线程无脑调用 simple().collect{value-> ...}，不会阻塞主线程的
 */