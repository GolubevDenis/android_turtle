package com.denis_golubev.turtle_lib

import android.graphics.Color
import com.denis_golubev.turtle_lib.actions.*
import com.denis_golubev.turtle_lib.actions.Action
import com.denis_golubev.turtle_lib.actions.ChangeColor
import com.denis_golubev.turtle_lib.actions.Forward
import com.denis_golubev.turtle_lib.actions.PenUp
import com.denis_golubev.turtle_lib.actions.PenWidth
import com.denis_golubev.turtle_lib.actions.turn.Left
import com.denis_golubev.turtle_lib.actions.turn.Right
import com.denis_golubev.turtle_lib.state.models.Appearance
import com.denis_golubev.turtle_lib.state.models.Coordinates
import com.denis_golubev.turtle_lib.state.models.State

// Created by denis on 07.01.2021.
abstract class Turtle {

    enum class Speed(val value: Float) {
        SLOWEST(1f),
        SLOW(5f),
        NORMAL(10f),
        FAST(25f),
        FASTEST(100f)
    }

    internal abstract val actions: List<Action>
    internal abstract val states: List<State>
    protected var speed: Speed = Speed.NORMAL

    fun speed(speed: Speed) {
        this.speed = speed
    }

    internal fun getAppearances(): List<Appearance> = states.map { it.appearance }
    internal abstract fun getStatesForFrame(frame: Int): List<State>
    internal fun isThereStatesToShow(frame: Int) = states.size >= frame

    abstract fun forward(distance: Float)
    abstract fun fd(distance: Float)

    abstract fun back(distance: Float)
    abstract fun bk(distance: Float)
    abstract fun backward(distance: Float)

    abstract fun left(angle: Float = 90f)
    abstract fun lt(angle: Float = 90f)
    abstract fun right(angle: Float = 90f)
    abstract fun rt(angle: Float = 90f)

    abstract fun penup()
    abstract fun pendown()

    abstract fun pencolor(color: Int)
    abstract fun pen_size(width: Float)
    abstract fun width(width: Float)

    companion object {
        fun buildBottomCenter(fieldWidth: Int, fieldHeight: Int): Turtle {
            return build(fieldWidth, fieldHeight, fieldWidth / 2f, fieldHeight.toFloat())
        }
        fun buildCenter(fieldWidth: Int, fieldHeight: Int): Turtle {
            return build(fieldWidth, fieldHeight, fieldWidth / 2f, fieldHeight / 2f)
        }
        fun buildTopCenter(fieldWidth: Int, fieldHeight: Int): Turtle {
            return build(fieldWidth, fieldHeight, fieldWidth / 2f, 0f)
        }
        fun build(fieldWidth: Int, fieldHeight: Int, x: Float, y: Float): Turtle {
            return TurtleImpl(fieldWidth, fieldHeight, x, y)
        }
    }
}

internal class TurtleImpl(
        fieldWidth: Int,
        fieldHeight: Int,
        x: Float,
        y: Float
) : Turtle() {

    private val startCoordinates = Coordinates(angle = 180f, x = x, y = y)
    private val initialState = State(startCoordinates)

    override val actions = arrayListOf<Action>()
    override val states = arrayListOf(initialState)

    private var colorBeforePenUp: Int = initialState.appearance.color

    override fun getStatesForFrame(frame: Int): List<State> {
        return states.subList(0, minOf(0 + frame, states.size))
    }

    override fun forward(distance: Float) {
        var passedDistance = 0f
        val speedValue = speed.value
        for(i in 0 until (distance / speedValue).toInt()) {
            val instantSpeed = if(passedDistance + speedValue > distance) distance - passedDistance else speedValue
            applyAction(Forward(instantSpeed))
            passedDistance += instantSpeed
        }
    }
    override fun fd(distance: Float) { forward(distance) }

    override fun back(distance: Float) {
        left(180f)
        forward(distance)
        left(180f)
    }
    override fun bk(distance: Float) { back(distance) }
    override fun backward(distance: Float) { back(distance) }

    override fun left(angle: Float) {
        applyAction(Left(angle))
    }
    override fun lt(angle: Float) { left(angle) }

    override fun right(angle: Float) {
        applyAction(Right(angle))
    }
    override fun rt(angle: Float) { right(angle) }

    override fun penup() {
        colorBeforePenUp = states.last().appearance.color
        pencolor(Color.TRANSPARENT)
    }

    override fun pendown() {
        pencolor(colorBeforePenUp)
    }

    override fun pencolor(color: Int) {
        applyAction(ChangeColor(color))
    }

    override fun pen_size(width: Float) {
        width(width)
    }

    override fun width(width: Float) {
        applyAction(PenWidth(width))
    }

    private fun applyAction(action: Action) {
        actions.add(action)

        val newState = action.apply(states.last())
        states.add(newState)
    }

}