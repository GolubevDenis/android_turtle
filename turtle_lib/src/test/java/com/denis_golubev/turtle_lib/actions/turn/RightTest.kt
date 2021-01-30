package com.denis_golubev.turtle_lib.actions.turn

import com.denis_golubev.turtle_lib.state.models.Coordinates
import com.denis_golubev.turtle_lib.state.models.State
import org.junit.Assert
import org.junit.Test

class RightTest : Assert() {

    @Test
    fun apply() {
        val state = State(coordinates = Coordinates(heading = 90f))

        val angle = Right(50f).apply(state).coordinates.heading
        assertEquals(40f, angle)
    }

}
