package com.denis_golubev.turtle_lib.state

import com.denis_golubev.turtle_lib.Turtle
import com.denis_golubev.turtle_lib.state.models.Coordinates
import com.denis_golubev.turtle_lib.state.models.State
import org.junit.Assert
import org.junit.Test

class SpeedStatesSplitterTest : Assert() {

    private val states = listOf(
            State(coordinates = Coordinates(y = 0f)),
            State(coordinates = Coordinates(y = 20f)),
            State(coordinates = Coordinates(y = 30f)),
            State(coordinates = Coordinates(y = 60f))
    )

    @Test
    fun split() {
        val expected = listOf(
                State(coordinates = Coordinates(y = 0f)),
                State(coordinates = Coordinates(y = 10f)),
                State(coordinates = Coordinates(y = 20f)),
                State(coordinates = Coordinates(y = 30f)),
                State(coordinates = Coordinates(y = 40f)),
                State(coordinates = Coordinates(y = 50f)),
                State(coordinates = Coordinates(y = 60f))
        )

        val splitter = SpeedStatesSplitter(10)
        val actual = splitter.split(states)
        assertEquals(expected, actual)
    }

}
