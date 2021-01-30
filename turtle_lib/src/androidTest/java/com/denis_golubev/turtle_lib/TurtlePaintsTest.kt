package com.denis_golubev.turtle_lib

import android.graphics.Color
import android.graphics.Paint
import com.denis_golubev.turtle_lib.state.TurtlePaints
import com.denis_golubev.turtle_lib.state.models.Appearance
import org.junit.Assert
import org.junit.Test

class TurtlePaintsTest : Assert() {

    private val appearances = listOf(
            Appearance(color = Color.BLACK, strokeWidth = 1f),
            Appearance(color = Color.BLACK, strokeWidth = 1f),
            Appearance(color = Color.BLUE, strokeWidth = 1f),
            Appearance(color = Color.TRANSPARENT, strokeWidth = 2f),
            Appearance(color = Color.BLUE, strokeWidth = 2f)
    )

    private val expected = mapOf(
            Appearance(color = Color.BLACK, strokeWidth = 1f) to paint(Color.BLACK, 1f),
            Appearance(color = Color.BLUE, strokeWidth = 1f) to paint(Color.BLUE, 1f),
            Appearance(color = Color.TRANSPARENT, strokeWidth = 2f) to paint(Color.TRANSPARENT, 2f),
            Appearance(color = Color.BLUE, strokeWidth = 2f) to paint(Color.BLUE, 2f)
    )

    private fun paint(color: Int, width: Float): Paint =
            Paint().apply {
                this.color = color
                this.strokeWidth = width
            }

    private val turtlePaints = TurtlePaints(appearances)

    @Test
    fun getPaint() {
        for (appearance in appearances)
            assertPaintEquals(expected[appearance], turtlePaints.getPaintFor(appearance))
    }

    private fun assertPaintEquals(a: Paint?, b: Paint?) {
        assertEquals(a == null, b == null)
        if(a != null && b != null) {
            assertEquals("Paint's strokeWidth are not equal", a.strokeWidth, b.strokeWidth)
            assertEquals("Paint's color are not equal", a.color, b.color)
        }
    }

}
