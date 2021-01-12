package com.denis_golubev.turtle_lib

import org.junit.Assert
import org.junit.Test
import kotlin.math.PI

class ExtensionsKtTest : Assert() {

    @Test
    fun toRadian() {
        assertEquals((PI/6f).toFloat(), 30f.toRadian())
        assertEquals((2f*PI).toFloat(), 360f.toRadian())
    }

    @Test
    fun toDegree() {
        assertEquals(30f, (PI/6f).toFloat().toDegree())
        assertEquals(360f, (PI*2f).toFloat().toDegree())
    }

    @Test
    fun normalizeDegree() {
        assertEquals(110f, 470f.normalizeDegree())
        assertEquals(220f, (-500f).normalizeDegree())
    }

}
