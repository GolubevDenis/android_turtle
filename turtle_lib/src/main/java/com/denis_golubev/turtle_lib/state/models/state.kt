package com.denis_golubev.turtle_lib.state.models

import android.graphics.Color
import com.denis_golubev.turtle_lib.toRadian
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

// Created by denis on 10.01.2021.
data class State(
        val coordinates: Coordinates = Coordinates(),
        val appearance: Appearance = Appearance()
)

data class Coordinates(
        val heading: Float = 0f,
        val x: Float = 0f,
        val y: Float = 0f
) {
    fun distanceTo(coordinate: Coordinates): Float {
        return sqrt((coordinate.x - x).pow(2) + (coordinate.y - y).pow(2))
    }
    fun limitMaxDistanceTo(coordinate: Coordinates, maxDistance: Float): Coordinates {
        val distance = distanceTo(coordinate)
        if(distance > maxDistance) {
            return addDistance(maxDistance)
        }
        return coordinate
    }
    fun addDistance(value: Float): Coordinates {
        val deltaX = sin(heading.toRadian()) * value
        val deltaY = cos(heading.toRadian()) * value
        return copy(x = x + deltaX, y = y + deltaY)
    }
}

data class Appearance(
        val color: Int = Color.BLACK,
        val strokeWidth: Float = 1f
)