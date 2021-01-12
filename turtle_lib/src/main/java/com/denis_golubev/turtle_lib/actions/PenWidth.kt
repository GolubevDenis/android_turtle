package com.denis_golubev.turtle_lib.actions

import com.denis_golubev.turtle_lib.state.models.State

// Created by denis on 10.01.2021.
internal class PenWidth(
        private val width: Float
) : Action {

    override fun apply(state: State): State {
        val appearance = state.appearance.copy(strokeWidth = width)
        return state.copy(appearance = appearance)
    }

}