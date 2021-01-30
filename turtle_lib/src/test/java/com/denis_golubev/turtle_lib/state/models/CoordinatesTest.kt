package com.denis_golubev.turtle_lib.state.models

import com.denis_golubev.turtle_lib.toRadian
import org.junit.Assert
import org.junit.Test
import kotlin.math.cos
import kotlin.math.sin

class CoordinatesTest : Assert() {

    @Test
    fun distanceTo() {
        val coordinates1 = Coordinates(x = 0f, y = 0f)
        val coordinates2 = Coordinates(x = 6f, y = 8f)
        assertEquals(10f, coordinates1.distanceTo(coordinates2))
    }

    @Test
    fun addDistance() {
        val angle = 30f
        val angleInRadians = 30f.toRadian()
        val distance = 10f

        val coordinates = Coordinates(heading = angle, x = 0f, y = 0f)

        val newCoordinates = coordinates.addDistance(distance)
        assertEquals(sin(angleInRadians) * distance, newCoordinates.x)
        assertEquals(cos(angleInRadians) * distance, newCoordinates.y)
    }

    @Test
    fun limitMaxDistanceTo() {
        val a = Coordinates(x = 0f, y = 0f)
        val b = Coordinates(x = 0f, y = 100f)

        val expected = Coordinates(x = 0f, y = 10f)
        val actual = a.limitMaxDistanceTo(b, 10f)
        assertEquals(expected, actual)
    }

}
