package com.dermo.app.ammj.app.utils

import org.mockito.Mockito
import kotlin.reflect.KCallable
import kotlin.reflect.KParameter
import kotlin.reflect.jvm.isAccessible

object MockitoHelper {

    fun <T> anyObject(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

    fun getPrivateFunByName(`object`: Any, funName: String): KCallable<*> {
        val function = `object`::class.members.single { it.name == funName }
        function.isAccessible = true
        return function
    }

    fun getKParameterByPosition(`object`: Any, funName: String, position: Int = 0): KParameter {
        val kCallable = getPrivateFunByName(`object`, funName)
        return kCallable.parameters[position]
    }
}
