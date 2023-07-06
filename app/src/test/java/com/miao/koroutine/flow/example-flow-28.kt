/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from flow.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleFlow28

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun simple(): Flow<String> = 
    flow {
        for (i in 1..3) {
            println("Emitting $i")
            emit(i) // emit next value
        }
    }
    .map { value ->
        check(value <= 1) { "Crashed on $value" }                 
        "string $value"
    }

fun main() = runBlocking<Unit> {
    simple()
        .catch { e -> emit("Caught $e") } // emit on exception
        .collect { value -> println(value) }
}

/**
 * 在 Flow 中，我们可以使用 catch 操作符来捕获发射器中可能抛出的异常，并在操作符的代码块中对异常进行处理。
 */

/**
 * 使用 catch 操作符时，我们可以对异常进行必要的处理，但仍然可以选择在某种程度上保留异常的信息或传递异常给上游或下游的操作符。
 * 使用 catch 操作符还允许封装异常处理，意味着我们可以在 catch 操作符的代码块中进行异常处理逻辑，将其与主要的流处理逻辑分离。
 * 这样可以使代码更加清晰、可读，并且可以在需要时轻松更改或调整异常处理策略。
 */
