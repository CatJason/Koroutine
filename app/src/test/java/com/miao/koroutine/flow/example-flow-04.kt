/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow04

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun simple(): Flow<Int> = flow { // flow builder
    for (i in 1..3) {
        delay(100) // pretend we are doing something useful here
        emit(i) // emit next value
    }
}

fun main() = runBlocking<Unit> {
    // Launch a concurrent coroutine to check if the main thread is blocked
    launch {
        for (k in 1..3) {
            println("I'm not blocked $k")
            delay(100)
        }
    }
    // Collect the flow
    simple().collect { value -> println(value) } 
}

/**
 * 我们这里应给学到的是 List，Sequence，Flow 三者的不同
 *
 * 使用 List 结果类型，意味着我们只能一次返回所有值。
 * 同步计算的值流（stream）会使用 Sequence 类型
 * 异步计算的值流（stream），我们可以使用 Flow 类型
 */

/*
    Kotlin中的Sequence和Flow都是用于处理集合或流式数据的类型，但它们在实现和使用方式上有一些区别。

    生成方式：
        Sequence：Sequence是一种惰性计算的序列
            可以通过调用sequenceOf、asSequence或使用生成器函数（如generateSequence）创建。
            Sequence的计算是惰性的，只有在需要使用元素时才会进行计算。
        Flow：Flow是一种基于协程的异步数据流，可以通过调用flowOf、asFlow或使用挂起函数（如flow）创建。
            Flow可以支持异步的、非阻塞的数据流。
    数据处理：
        Sequence：Sequence提供了一系列用于转换、过滤和操作元素的函数（如map、filter、flatMap等）。
            Sequence的操作是惰性的，每个操作只会在需要时逐个处理元素，并且会保留中间结果。
        Flow：Flow提供了类似的操作函数，例如map、filter、flatMap等，但这些操作都是挂起函数，可以在协程中使用。
            Flow的操作是可以取消的，并且支持背压（backpressure）处理，可以控制数据的产生和消费速率，以避免数据丢失或内存溢出。
    执行方式：
        Sequence：Sequence的操作是同步执行的，即在调用操作函数时会立即执行并返回结果。
            由于操作是惰性的，可能需要多个操作组合在一起才会触发计算，但整体执行过程是同步的。
        Flow：Flow的操作是异步执行的，即在协程中执行。
            Flow的操作可以在不同的协程之间切换，允许非阻塞的并发处理。
            Flow的执行可以通过调用collect或其他终止操作来触发。
    使用场景：
        Sequence：Sequence适用于处理较小的集合或数据流，可以进行复杂的转换和计算，但可能不适合处理大量的元素或需要异步处理的情况。
        Flow：Flow适用于处理大量的异步数据，可以在协程中进行高效的并发处理。
            Flow可以用于处理数据库查询、网络请求、传感器数据等需要异步操作的场景。
    总结：
        总的来说，Sequence适用于简单的集合处理，而Flow适用于异步、大规模数据处理和并发操作。
        选择使用哪种类型取决于你的具体需求和场景。
 */

/**
 * collect函数是Flow的一个终止操作，用于消费Flow中的元素
 * Flow的创建和操作通常发生在协程的上下文中。要创建一个Flow，可以使用flow函数或其他Flow构造函数
 * 然后使用操作符函数（如map、filter、flatMap等）对其进行转换和处理
 */

/**
 * map函数用于对集合中的每个元素进行转换，生成一个新的集合，新集合的元素与原集合对应位置的元素经过转换后得到
 */

/**
 * filter函数用于对集合中的元素进行筛选，返回符合指定条件的元素组成的新集合
 */

/**
 * flatMap函数用于对集合中的每个元素进行转换，并将结果扁平化成一个新的集合
 */

/**
 * 其实有点类似生产者与消费者，有点类似于 produce 函数
 */

/*
异同之处：
    异步处理：Flow和Channel都可以用于处理异步数据流。
        它们都可以在协程中使用，支持非阻塞的数据处理。
    数据传递方式：Flow以数据流的形式传递数据，通过操作符和函数对数据流进行处理。
        Channel以发送和接收的方式传递数据，通过send和receive函数发送和接收数据项。
    惰性计算：Flow是惰性计算的，数据只有在终止操作（如collect）触发时才会开始计算和传递。
        Channel支持延迟传递，数据在接收方准备好接收时才会传递。
    缓冲：Channel可以具有缓冲区，使发送方和接收方可以在异步环境中解耦。
        Flow没有内置的缓冲机制，它通常是无缓冲的，数据项按需计算和传递。
    生产者-消费者模型：Channel可以用于实现生产者-消费者模型，其中一个协程作为生产者发送数据，另一个协程作为消费者接收数据。
        Flow更加通用，可以适用于更多不同的数据处理场景。
    底层实现：Flow是基于Suspend函数和协程的高级抽象，它使用了协程的挂起和恢复机制。
        Channel是一种更底层的原语，直接提供了数据传递和通信的能力。

关系：
    Flow和Channel都可以相互转换：
        Kotlin的标准库提供了一些函数，可以将Channel转换为Flow（如consumeAsFlow函数），或将Flow转换为Channel（如produceIn函数）。
        这使得它们可以在一些特定的情况下进行互操作。
    Flow可以使用Channel作为其底层实现：
        在某些情况下，可以使用Channel来实现自定义的Flow，通过在Channel中发送和接收数据项来实现异步数据的传递。

总结：
    综上所述，Flow和Channel都是用于处理异步数据流的工具，它们有一些共同的特性和相似之处，但也有一些不同之处。
    Flow是一种更高级的抽象，适用于通用的数据处理场景，而Channel是一种更底层的原语，提供了更直接的数据传递和通信能力。
    选择使用Flow还是Channel取决于具体的需求和场景。
 */