package com.denis_golubev.turtle_lib.actions

import com.denis_golubev.turtle_lib.state.models.Coordinates
import com.denis_golubev.turtle_lib.state.models.State
import org.junit.Assert
import org.junit.Test

class ForwardTest : Assert() {

    @Test
    fun apply() {
        val angle = 30f
        val expectedDistance = 10f

        val initialCoordinates = Coordinates(heading = angle, x = 0f, y = 0f)
        val initialState = State(coordinates = initialCoordinates)

        val newState = Forward(expectedDistance).apply(initialState)

        val actualDistance = initialCoordinates.distanceTo(newState.coordinates)
        assertEquals(expectedDistance, actualDistance)
    }

}
