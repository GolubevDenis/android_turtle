package com.denis_golubev.turtle_lib.actions.turn

import com.denis_golubev.turtle_lib.normalizeDegree
import com.denis_golubev.turtle_lib.state.models.Coordinates
import com.denis_golubev.turtle_lib.state.models.State
import org.junit.Assert
import org.junit.Test

class TurnTest : Assert() {

    private val CALCULATED_DEGREES = 370f

    private val turn = object : Turn(0f) {
        override fun calculate(currentAngle: Float, turnDegrees: Float): Float {
            return CALCULATED_DEGREES
        }
    }

    @Test
    fun apply() {
        val state = State(coordinates = Coordinates(heading = 0f))
        val angle = turn.apply(state).coordinates.heading
        assertEquals(CALCULATED_DEGREES.normalizeDegree(), angle)
    }

}
