package com.denis_golubev.turtle_lib

import android.graphics.Color
import com.denis_golubev.turtle_lib.actions.*
import com.denis_golubev.turtle_lib.actions.Action
import com.denis_golubev.turtle_lib.actions.ChangeColor
import com.denis_golubev.turtle_lib.actions.Forward
import com.denis_golubev.turtle_lib.actions.Move
import com.denis_golubev.turtle_lib.actions.PenWidth
import com.denis_golubev.turtle_lib.actions.turn.Left
import com.denis_golubev.turtle_lib.actions.turn.Right
import com.denis_golubev.turtle_lib.actions.turn.SetHeading
import com.denis_golubev.turtle_lib.state.models.Appearance
import com.denis_golubev.turtle_lib.state.models.Coordinates
import com.denis_golubev.turtle_lib.state.models.State

internal class TurtleImpl : Turtle {

    private val startCoordinates = Coordinates(heading = 180f, x = 0f, y = 0f)
    private val initialState = State(startCoordinates)

    private val states = arrayListOf(initialState)
    private var appearances = states.map { it.appearance }

    var speed: Turtle.Speed = Turtle.Speed.NORMAL
        private set

    override fun getStates(): List<State> = states

    override fun speed(speed: Turtle.Speed) {
        this.speed = speed
    }

    override fun getAppearances(): List<Appearance> = states.map { it.appearance }

    override fun getCoordinates(): Coordinates {
        synchronized(this) {
            if (states.isEmpty())
                return Coordinates()

            return states.last().coordinates
        }
    }

    override fun setCoordinates(coordinates: Coordinates) {
        setPosition(coordinates.x, coordinates.y)
        setHeading(coordinates.heading)
    }

    override fun setPosition(x: Float, y: Float) {
        penup()
        applyAction(Move(x, y))
        pendown()
    }

    override fun setHeading(degree: Float) {
        applyAction(SetHeading(degree))
    }

    override fun forward(distance: Float) {
        applyAction(Forward(distance))
    }

    override fun fd(distance: Float) {
        forward(distance)
    }

    override fun back(distance: Float) {
        left(180f)
        forward(distance)
        left(180f)
    }

    override fun bk(distance: Float) {
        back(distance)
    }

    override fun backward(distance: Float) {
        back(distance)
    }

    override fun left(angle: Float) {
        applyAction(Left(angle))
    }

    override fun lt(angle: Float) {
        left(angle)
    }

    override fun right(angle: Float) {
        applyAction(Right(angle))
    }

    override fun rt(angle: Float) {
        right(angle)
    }


    private var colorBeforePenUp: Int = initialState.appearance.color

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

    var lastAction: Action? = null
    private fun applyAction(action: Action) {
        var _action = action
        if (lastAction?.javaClass == action.javaClass) {
            states.removeAt(states.size - 1)
            lastAction = lastAction!! + action
            _action = lastAction!!
        }
        val newState = _action.apply(states.last())
        states.add(newState)

        lastAction = _action
    }

}