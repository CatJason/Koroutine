/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow02

fun simple(): Sequence<Int> = sequence { // sequence builder
    for (i in 1..3) {
        Thread.sleep(100) // pretend we are computing it
        yield(i) // yield next value
    }
}

fun main() {
    simple().forEach { value -> println(value) } 
}

/**
 * Sequence<Int> 是 Kotlin 标准库中的一个接口，表示一个可迭代的整数序列。
 * 在 Kotlin 中，Sequence 提供了一种惰性求值的序列计算机制。
 * 与集合（例如 List 或 Array）不同，Sequence 并不会在创建时立即计算所有元素，而是按需进行计算。
 * 这种惰性计算的特性使得 Sequence 在处理大型数据集时能够更高效地使用内存和 CPU 资源。
 */

/**
 * 我们深入一下这个 yield(n) 函数
    override suspend fun yield(value: T) {
        nextValue = value
        state = State_Ready
        return suspendCoroutineUninterceptedOrReturn { c ->
            nextStep = c
            COROUTINE_SUSPENDED
        }
    }
 *
 * 用于在协程中暂停执行并返回一个值
 *
 * return suspendCoroutineUninterceptedOrReturn { c -> ... }
 * 返回一个挂起函数 suspendCoroutineUninterceptedOrReturn 的调用结果
 */

/*
    惰性求值的序列计算机制是指在需要时才计算序列中的元素，而不是在创建序列时立即计算所有元素。

    与集合（如列表、数组）不同，序列的计算是按需进行的。
    当需要使用序列的元素时，才会计算下一个元素，并且只计算当前需要的元素，而不会计算整个序列。

    这种惰性计算的机制带来了一些优点：

    节省内存：对于大型数据集，一次性加载和计算所有元素可能会占用大量的内存空间。
    而序列计算机制允许按需计算元素，可以避免一次性加载整个数据集，从而节省内存。
    提高效率：对于复杂的计算或数据转换操作，按需计算可以避免不必要的计算开销。
    只计算当前需要的元素，可以在计算量较大时提高代码的执行效率。
    支持无限序列：序列计算机制也可以用于表示无限序列，即没有固定的大小限制。
    由于序列是按需计算的，只有在需要时才会计算下一个元素，因此可以用来表示无限序列，而不会导致无限循环或内存溢出等问题。
    在 Kotlin 中，Sequence 接口提供了对惰性序列的支持。
    通过使用 `sequenceOf()`、`generateSequence()`、`asSequence()` 等函数，可以创建序列并进行操作和转换。
    常见的操作函数有 `map()`、`filter()`、`flatMap()` 等，它们返回一个新的序列，而不会立即计算所有元素。

    ''' Kotlin
    val sequence = sequence {
        yield(1)
        yield(2)
        yield(3)
    }

    val result = sequence.map { it * 2 }.toList()

    println(result) // 输出：[2, 4, 6]
    '''
 */