package com.denis_golubev.turtle_lib.actions

import com.denis_golubev.turtle_lib.state.models.State

// Created by denis on 07.01.2021.
internal class Forward(
    val value: Float
) : Action {

    override fun apply(state: State): State {
        return state.copy(coordinates = state.coordinates.addDistance(value))
    }

    override fun plus(action: Action): Action {
        if(action !is Forward)
            return this
        return Forward(this.value + action.value)
    }

}
