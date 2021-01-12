package com.denis_golubev.turtle_lib.actions

import com.denis_golubev.turtle_lib.state.models.Coordinates
import com.denis_golubev.turtle_lib.toRadian
import org.junit.Assert
import org.junit.Test
import kotlin.math.cos
import kotlin.math.sin

class ForwardTest : Assert() {

    @Test
    fun apply() {
        val angle = 30f
        val angleInRadians = 30f.toRadian()
        val length = 10f

        val coordinates = Coordinates(angle = angle, x = 0f, y = 0f)

        val newCoordinates = Forward(length).calculateNewCoordinates(coordinates)
        assertEquals(sin(angleInRadians) * length, newCoordinates.x)
        assertEquals(cos(angleInRadians) * length, newCoordinates.y)
    }

}
