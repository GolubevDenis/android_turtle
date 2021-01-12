package com.denis_golubev.turtle_lib.state

import android.graphics.Paint
import androidx.annotation.VisibleForTesting
import com.denis_golubev.turtle_lib.Turtle
import com.denis_golubev.turtle_lib.state.models.Appearance
import java.lang.Exception
import kotlin.math.min

// Created by denis on 10.01.2021.
internal class TurtlePaints(
        private val appearances: List<Appearance>
) {

    private val mapper = StateToPaintMapper()
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE) val paints: Array<Paint?> = Array(appearances.size) { null }

    private fun createPaintFor(index: Int) {
        val initialPaint = mapper.map(appearances[index])
        paints[index] = initialPaint
    }

    private fun createPaints() {
        if(appearances.isEmpty())
            return

        createPaintFor(0)

        if(appearances.size < 2)
            return

        for(i in 1 until appearances.size) {
            if(appearances[i - 1] != appearances[i])
                createPaintFor(i)
        }
    }

    init {
        createPaints()
    }

    fun getPaint(index: Int): Paint {
        if(paints.isEmpty())
            throw Exception("Call init() method first.")

        val endIndex = min(index, paints.size)
        return  paints.slice(0 until endIndex)
                .reversed()
                .firstOrNull { it != null } ?: paints.first()!!
    }

}