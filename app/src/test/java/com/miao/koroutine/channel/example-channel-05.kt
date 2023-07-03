/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from channels.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleChannel05

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

var isFirst = true
var id = 0

fun main() = runBlocking {
    println("-------------------如果一个数可以被小于它的素数整除，那么它也可以被大于它的素数整除。-------------------")
    var cur = numbersFrom(2)
    repeat(10) {
        val prime = cur.receive()
        if(isFirst){
            isFirst = false
            println("第 ${it + 1} 次取数")
            println("从 number 中取数 $prime")
        } else {
            println("第 ${it + 1} 次取数")
            println("从管道中取出余数：prime = $prime，为所求素数")
        }
        println("调用 filter，prime = $prime")
        id ++
        cur = filter(cur, prime, id)
    }
    coroutineContext.cancelChildren() // cancel all children to let main finish
}

fun CoroutineScope.numbersFrom(start: Int) = produce<Int> {
    println("                                  numbersFrom")
    var x = start
    while (true) {
        send(x++) // infinite stream of integers from start
        println("                                  x++, 发送 x = $x 到管道")
    }
}

fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int, id: Int) = produce<Int> { //
    println("                                                        -------------------")
    println("                                                        id: $id")
    println("                                                        -------------------")
    println()
    for (x in numbers) {
        if(isFirst){
            isFirst = false
            println("                                                        从 number 中取数 $x 作为被除数")
        } else {
            println("                                                        从管道中取数：x = $x 作为被除数")
        }
        println("                                                        被除数 x: $x, 除数 prime: $prime, 余数: ${(x % prime)}")
        if (x % prime != 0){
            println("                                                        是素数，发送 x = $x 到管道")
            send(x)
        } else {
            println("                                                        不是素数，结束")
        }
        println()
     }
}

/**
 * 如果一个数可以被小于它的素数整除，那么它也可以被大于它的素数整除。
 * 因此，我们可以通过验证一个数是否能被小于它的素数整除来判断它是否为素数。
 */

/**
 * 由此我们也能看出管道有点和先进先出的队列类似
 */

/**
 * 并发执行：协程可以并发执行，使得我们可以同时进行多个素数验证的任务，提高计算效率。
 * 每个素数验证任务可以在独立的协程中执行，彼此之间不会相互阻塞。
 * 可取消性：协程提供了可取消的机制，可以在需要时取消素数验证任务。
 * 如果一个验证任务正在执行但没有得出结果，我们可以通过取消协程来提前结束验证过程，节省计算资源。
 */

/**
 * 一时看不懂可以先不看，有点复杂
 */