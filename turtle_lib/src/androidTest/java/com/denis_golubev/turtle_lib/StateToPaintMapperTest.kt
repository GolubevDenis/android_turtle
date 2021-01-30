package com.denis_golubev.turtle_lib

import android.graphics.Color
import com.denis_golubev.turtle_lib.state.StateToPaintMapper
import com.denis_golubev.turtle_lib.state.models.Appearance
import org.junit.Assert
import org.junit.Test

class StateToPaintMapperTest : Assert() {

    @Test
    fun map() {
        val mapper = StateToPaintMapper()
        val appendable = Appearance(color = Color.BLUE, strokeWidth = 10f)
        val paint = mapper.map(appendable)
        assertEquals(Color.BLUE, paint.color)
        assertEquals(10f, paint.strokeWidth)
    }

}
