package com.denis_golubev.turtle_lib

import com.denis_golubev.turtle_lib.state.models.Appearance
import com.denis_golubev.turtle_lib.state.models.Coordinates
import com.denis_golubev.turtle_lib.state.models.State

// Created by denis on 07.01.2021.
interface Turtle {

    enum class Speed(val value: Float) {
        SLOWEST(1f),
        SLOW(5f),
        NORMAL(10f),
        FAST(25f),
        FASTEST(100f)
    }

    fun speed(speed: Speed)

    fun getStates(): List<State>
    fun getAppearances(): List<Appearance>

    fun getCoordinates(): Coordinates
    fun setCoordinates(coordinates: Coordinates)

    fun getX(): Float = getCoordinates().x
    fun getY(): Float = getCoordinates().y
    fun getHeading(): Float = getCoordinates().heading

    fun setPosition(x: Float, y: Float)
    fun setHeading(degree: Float)

    fun forward(distance: Float)
    fun fd(distance: Float)

    fun back(distance: Float)
    fun bk(distance: Float)
    fun backward(distance: Float)

    fun left(angle: Float = 90f)
    fun lt(angle: Float = 90f)
    fun right(angle: Float = 90f)
    fun rt(angle: Float = 90f)

    fun penup()
    fun pendown()

    fun pencolor(color: Int)
    fun pen_size(width: Float)
    fun width(width: Float)

    companion object {
        fun create(): Turtle {
            return TurtleImpl()
        }
    }
}

