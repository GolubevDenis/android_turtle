package com.denis_golubev.turtle_lib.actions

import com.denis_golubev.turtle_lib.state.models.State

// Created by denis on 07.01.2021.
internal class Move(
    private val x: Float,
    private val y: Float
) : Action {

    override fun apply(state: State): State {
        val coordinates = state.coordinates
        return state.copy(coordinates = coordinates.copy(x = x, y = y))
    }

}
