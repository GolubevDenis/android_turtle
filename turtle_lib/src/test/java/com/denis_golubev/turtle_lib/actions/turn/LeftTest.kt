package com.denis_golubev.turtle_lib.actions.turn

import com.denis_golubev.turtle_lib.state.models.Coordinates
import com.denis_golubev.turtle_lib.state.models.State
import org.junit.Assert
import org.junit.Test

class LeftTest : Assert() {

    @Test
    fun apply() {
        val state = State(coordinates = Coordinates(heading = 90f))

        val angle1 = Left(50f).apply(state).coordinates.heading
        assertEquals(140f, angle1)
    }

}
