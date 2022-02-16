package com.harry.fibonacciseries

import java.lang.IllegalArgumentException

object FibonacciCalculation {

    suspend fun Fibonacci(n: Int): Long {
        return when {
            n >= 2 -> Fibonacci(n - 1) + Fibonacci(n - 2)
            n == 0 -> 0
            n == 1 -> 1
            else -> throw IllegalArgumentException()
        }
    }
}