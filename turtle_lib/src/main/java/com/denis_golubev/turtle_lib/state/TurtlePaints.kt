package com.denis_golubev.turtle_lib.state

import android.graphics.Paint
import androidx.annotation.VisibleForTesting
import com.denis_golubev.turtle_lib.state.models.Appearance

// Created by denis on 10.01.2021.
internal class TurtlePaints(
        private val appearances: List<Appearance>
) {

    private val mapper = StateToPaintMapper()
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE) var paints: Map<Appearance, Paint> = mapOf()

    private fun createPaints() {
        if(appearances.isEmpty())
            return

        paints = appearances.toSet()
                .map { it to mapper.map(it) }
                .toMap()
    }

    init {
        createPaints()
    }

    fun getPaintFor(appearance: Appearance): Paint {
        if(paints.isEmpty())
            throw Exception("Call init() method first.")

        return paints[appearance] ?: error("No Paint for appearance $appearance")
    }

}