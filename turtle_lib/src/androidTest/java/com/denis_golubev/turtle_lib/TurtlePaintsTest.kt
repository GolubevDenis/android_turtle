package com.denis_golubev.turtle_lib

import android.graphics.Color
import android.graphics.Paint
import com.denis_golubev.turtle_lib.state.TurtlePaints
import org.junit.Assert
import org.junit.Test

class TurtlePaintsTest : Assert() {

    private val appearances = listOf(
            Turtle.State.Appearance(color = Color.BLACK, strokeWidth = 1f),
            Turtle.State.Appearance(color = Color.BLACK, strokeWidth = 1f),
            Turtle.State.Appearance(color = Color.BLUE, strokeWidth = 1f),
            Turtle.State.Appearance(color = Color.BLUE, strokeWidth = 2f),
            Turtle.State.Appearance(color = Color.BLUE, strokeWidth = 2f)
    )

    private fun paint(color: Int, width: Float): Paint =
            Paint().apply {
                this.color = color
                this.strokeWidth = width
            }

    private val turtlePaints = TurtlePaints(appearances)

    @Test
    fun paintsListsAreTheSame() {
        val expected = listOf(
                paint(Color.BLACK, 1f),
                null,
                paint(Color.BLUE, 1f),
                paint(Color.BLUE, 2f),
                null
        )
        val actual = turtlePaints.paints
        for (i in expected.indices)
            assertPaintEquals(i, expected[i], actual[i])
    }

    @Test
    fun getPaint() {
        val expected = listOf(
                paint(Color.BLACK, 1f),
                paint(Color.BLACK, 1f),
                paint(Color.BLUE, 1f),
                paint(Color.BLUE, 2f),
                paint(Color.BLUE, 2f)
        )
        for (i in expected.indices)
            assertPaintEquals(i, expected[i], turtlePaints.getPaint(i))
    }

    private fun assertPaintEquals(index: Int, a: Paint?, b: Paint?) {
        assertEquals(a == null, b == null)
        if(a != null && b != null) {
            assertEquals("Paint's strokeWidth are not equal for index $index", a.strokeWidth, b.strokeWidth)
            assertEquals("Paint's color are not equal for index $index", a.color, b.color)
        }
    }

}
