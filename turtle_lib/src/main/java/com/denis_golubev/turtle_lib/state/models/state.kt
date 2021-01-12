package com.denis_golubev.turtle_lib.state.models

import android.graphics.Color
import kotlin.math.pow
import kotlin.math.sqrt

// Created by denis on 10.01.2021.
internal data class State(
        val coordinates: Coordinates = Coordinates(),
        val appearance: Appearance = Appearance()
)

internal data class Coordinates(
        val angle: Float = 0f,
        val x: Float = 0f,
        val y: Float = 0f
) {
    fun distanceTo(coordinates: Coordinates): Float {
        return sqrt((coordinates.x - x).pow(2) + (coordinates.y - y).pow(2))
    }
}

internal data class Appearance(
        val color: Int = Color.BLACK,
        val strokeWidth: Float = 1f
)