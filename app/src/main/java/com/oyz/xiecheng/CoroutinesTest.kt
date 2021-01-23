package com.oyz.xiecheng

import kotlinx.coroutines.*

/*fun main() {
    GlobalScope.launch {
        println("codes run in coroutine scope")
    }
    Thread.sleep(1000)
}*/

/*fun main() {
    runBlocking {
        println("codes run in coroutine scope")
        delay(1500)
        println("codes run in coroutine scope finished")
    }
}*/

/*fun main() {
    runBlocking {
        launch {
            println("launch1")
            delay(1000)
            println("launch1 finished")
        }
        launch {
            println("launch2")
            delay(1000)
            println("launch2 finished")
        }
    }
}*/

/*fun main() {
    val start = System.currentTimeMillis()
    runBlocking {
        repeat(100000) {
            launch {
                println(".")
            }
        }
    }
    val end = System.currentTimeMillis()
    println(end - start)
}*/

/*fun main() {
    runBlocking {
        coroutineScope {
            launch {
                for (i in 1..10) {
                    println(i)
                    delay(1000)
                }
            }
        }
        println("coroutineScope finished")
    }
    println("runBlocking finished")
}*/

/*fun main(){
    runBlocking {
        val result = async {
            5+5
        }.await()
        println(result)
    }
}*/

/*fun main(){
    runBlocking {
        val start = System.currentTimeMillis()
        val result1 = async {
            delay(1000)
            5+5
        }.await()
        val result2 = async {
            delay(1000)
            4+6
        }.await()
        println("result is ${result1+result2}.")
        val end = System.currentTimeMillis()
        println("cost ${end - start} ms.")
    }
}*/

fun main(){
    runBlocking {
        val start = System.currentTimeMillis()
        val deferred1 = async {
            delay(1000)
            5+5
        }
        val deferred2 = async {
            delay(1000)
            4+6
        }

        println("result is ${deferred1.await()+deferred2.await()}.")
        val end = System.currentTimeMillis()
        println("cost ${end - start} ms.")
    }
}