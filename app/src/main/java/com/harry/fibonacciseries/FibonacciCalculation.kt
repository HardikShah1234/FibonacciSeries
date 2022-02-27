package com.harry.fibonacciseries

import kotlinx.coroutines.*

object FibonacciCalculation {

    suspend fun fibTail(n: Int, prev2: ULong, prev1: ULong): ULong =
        withContext(Dispatchers.Default){
            when(n){
                1 -> prev1
                else -> fibTail(n - 1, prev1, prev1 + prev2)
            }
        }

}