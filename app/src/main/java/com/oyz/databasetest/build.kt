package com.oyz.databasetest

fun <T> T.build(block: T.() -> Unit): T {
    block()
    return this
}