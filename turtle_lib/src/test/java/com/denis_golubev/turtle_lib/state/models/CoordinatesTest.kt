package com.denis_golubev.turtle_lib.state.models

import org.junit.Assert
import org.junit.Test

class CoordinatesTest : Assert() {

    @Test
    fun distanceTo() {
        val coordinates1 = Coordinates(x = 0f, y = 0f)
        val coordinates2 = Coordinates(x = 6f, y = 8f)
        assertEquals(10f, coordinates1.distanceTo(coordinates2))
    }

}
