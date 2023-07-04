/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

// This file was automatically generated from exception-handling.md by Knit tool. Do not edit.
package kotlinx.coroutines.guide.exampleExceptions02

import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception -> 
        println("CoroutineExceptionHandler got $exception") 
    }
    val job = GlobalScope.launch(handler) { // root coroutine, running in GlobalScope
        throw AssertionError()
    }
    val deferred = GlobalScope.async(handler) { // also root, but async instead of launch
        throw ArithmeticException() // Nothing will be printed, relying on user to call deferred.await()
    }
    joinAll(job, deferred)
}
/**
 * 将未捕获异常打印到控制台的默认行为是可自定义的。
 * 根协程中的 CoroutineExceptionHandler 上下文元素可以被用于这个根协程通用的 catch 块，及其所有可能自定义了异常处理的子协程。
 * 它类似于 Thread.uncaughtExceptionHandler。
 * 你无法从 CoroutineExceptionHandler 的异常中恢复。
 * 当调用处理者的时候，协程已经完成并带有相应的异常。
 * 通常，该处理者用于记录异常，显示某种错误消息，终止和（或）重新启动应用程序。
 *
 * CoroutineExceptionHandler 仅在未捕获的异常上调用 — 没有以其他任何方式处理的异常。
 */

/**
 * 对于子协程
 * 所有子协程（在另一个 Job 上下文中创建的协程）委托<!– 它们的父协程处理它们的异常，然后它们也委托给其父协程，以此类推直到根协程
 * 因此永远不会使用在其上下文中设置的 CoroutineExceptionHandler
 */

/**
 * uncaughtExceptionPreHandler 和 CoroutineExceptionHandler
 * 是用于处理异常的两个不同的机制，它们在异常处理方面有所不同。
 *
 * uncaughtExceptionPreHandler 是 Java 平台提供的一个机制，用于在未捕获异常发生时进行处理。
 * 它是一个全局的异常处理器，可以设置在应用程序级别，并用于捕获未被任何代码捕获的异常。
 * 当异常没有被适当的代码块捕获时，uncaughtExceptionPreHandler 会被触发，允许你在异常终止应用程序之前进行一些处理，如记录日志、发送错误报告等。
 *
 * CoroutineExceptionHandler 是 Kotlin 协程库提供的一个特殊接口，用于处理协程中的异常。
 * 它与协程的生命周期绑定，在协程内部捕获异常，并提供自定义的异常处理逻辑。
 * CoroutineExceptionHandler 只处理协程内部发生的异常，它不会捕获未在协程范围内抛出的异常。
 */

/**
 * 在 Android 中使用 uncaughtExceptionPreHandler 需要实现一个 Thread.UncaughtExceptionHandler 接口
 * 然后将其设置为应用程序的默认未捕获异常处理器。

        import android.app.Application;
        import android.util.Log;

        public class MyApplication extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        }

        private static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                    // 在这里处理未捕获异常
                    Log.e("MyApplication", "Uncaught exception: " + throwable.toString());

                    // 可以进行其他操作，如记录日志、发送错误报告等

                    // 终止应用程序
                    System.exit(1);
                }
            }
        }
 */