/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from coroutine-context-and-dispatchers.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleContext11

import kotlinx.coroutines.*

val threadLocal = ThreadLocal<String?>() // declare thread-local variable

fun main() = runBlocking<Unit> {
    threadLocal.set("main")
    println(getString1())
    val job = launch(Dispatchers.Default + threadLocal.asContextElement(value = "launch")) {
        println(getString2())
        yield()
        println(getSting3())
    }
    job.join()
    println(getSting4())
}

/**
 * 知识点
 *
 * threadLocal
 * ThreadLocal 是 Java 平台提供的一种机制，用于在线程之间存储线程局部变量。
 * 在 Kotlin 协程中，我们可以通过 ThreadLocal 类来存储和获取线程局部数据。
 *
 * 为了方便在协程之间传递线程局部数据，Kotlin 协程提供了一个名为 asContextElement 的扩展函数。
 * asContextElement 扩展函数可以将一个 ThreadLocal 对象转换为协程上下文元素，从而将其与协程绑定。
 * 这样，在协程切换执行的过程中，线程局部数据也会随之切换，确保在每个协程中都能够获取到正确的线程局部数据。
 */

/**
 * 线程局部变量
 * 线程局部变量（ThreadLocal variable）是一种特殊类型的变量，它的值是与线程关联的。
 * 每个线程都有自己的独立副本，线程之间互不干扰。
 * 在多线程编程中，线程局部变量用于在每个线程中存储数据，这些数据在整个线程的执行过程中可供访问。
 * 线程局部变量通常用于存储线程特定的上下文信息或状态，例如用户身份信息、语言环境、数据库连接等。
 *
 * 线程局部变量的主要特点是：
 * 线程隔离：每个线程拥有自己的独立副本，互不干扰。
 * 一个线程对线程局部变量的修改不会影响其他线程的副本。
 * 线程共享：线程局部变量在一个线程的整个执行过程中都可供访问
 * 线程中的各个方法或代码块都可以读取和修改线程局部变量的值。
 */

fun getString1(): String {
    return "Pre-main,             current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'"
}

fun getString2(): String {
    return "Launch start,         current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'"
}

fun getSting3(): String {
    return "After yield,          current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'"
}

fun getSting4(): String {
    return "Post-main,            current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'"
}

/**
 * 这里其实主要说的就是
 * 线程中的 threadLocal 和 使用 asContextElement 传入协程中的 threadLocal 是两个独立副本
 * 无论线程与协程处于什么状态，都是相互独立的
 */