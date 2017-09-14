package org.xi.quick.test.kotlin

import java.util.concurrent.locks.Lock

open class Base(var i: Int) {
    open fun p() {
        println("base" + this.i)
    }
}


class Child(i: Int) : Base(i) {
    override fun p() {
        println("child" + this.i)
    }

}

class ComTest {

    companion object {
        fun printTest() {
            println("printTest")
        }
    }
}

fun <T> getLastIndex(i: T): T {
    return i
}

fun fun1(i: Int): Int = 1
fun fun2(i: Int): Int = 2
fun funElse(i: Int): Int = 3
fun returnFun(i: Int): Int {
    when (i) {
        1 -> return fun1(i)
        2 -> return fun2(i)
        else -> return funElse(i)
    }
}

fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) result.add(t)
    return result
}


fun sum(x: Int, y: Int): Int = x + y

val returnFun: (x: Int, y: Int) -> Int = { x: Int, y: Int -> x + y }

infix fun Int.replace(x: Int): Int = x

val <T> List<T>.lastIndex: Int
    get() = size - 1

fun toBeSynchroized() = println("hello")

fun <T> lock(lock: Lock, body: () -> T): T {
    lock.lock()
    try {
        return body()
    } finally {
        lock.unlock()
    }
}

fun <T> max(collection: Collection<out T>, less: (T, T) -> Boolean): T? {
    var max: T? = null
    for (it in collection)
        if (max == null || less(max!!, it))
            max = it
    return max
}


fun main(args: Array<String>) {
    var arr = listOf(1,2,3,4,100,8,40)
    var max = max(arr){a,b->a<b}

    println(max)
    var    max2:String? = "hehe"
    var tmp = max2!!
}
