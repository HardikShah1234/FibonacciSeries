package com.harry.fibonacciseries

object FibonacciCalculation {

    fun Fibonacci(n: Int): Int = when(n){
        1 -> 1
        2 -> 1
        else -> Fibonacci(n-1) + Fibonacci(n-2)
    }

    suspend fun fibTail(n: Int, prev2: ULong, prev1: ULong): ULong =
        when(n){
            1 -> prev1
            else -> fibTail(n - 1, prev1, prev1 + prev2)
        }
}