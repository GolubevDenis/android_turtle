package com.denis_golubev.turtle_lib.state

import android.graphics.Paint
import com.denis_golubev.turtle_lib.state.models.Appearance

// Created by denis on 09.01.2021.
internal class StateToPaintMapper {

    fun map(appearance: Appearance): Paint {
        val paint = Paint()
        paint.color = appearance.color
        paint.strokeWidth = appearance.strokeWidth
        return paint
    }

}