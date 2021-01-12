package com.denis_golubev.turtle_lib.actions.turn

import com.denis_golubev.turtle_lib.actions.Action
import com.denis_golubev.turtle_lib.normalizeDegree
import com.denis_golubev.turtle_lib.state.models.State

// Created by denis on 10.01.2021.
internal abstract class Turn(
        protected val value: Float
) : Action {

    override fun apply(state: State): State {
        val coordinates = state.coordinates
        val degree = calculate(coordinates.angle, value).normalizeDegree()
        val newCoordinates = coordinates.copy(angle = degree)
        return state.copy(coordinates = newCoordinates)
    }

    abstract fun calculate(currentAngle: Float, turnDegrees: Float): Float

}