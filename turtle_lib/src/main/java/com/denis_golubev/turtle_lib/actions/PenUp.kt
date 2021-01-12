package com.denis_golubev.turtle_lib.actions

import androidx.annotation.VisibleForTesting
import com.denis_golubev.turtle_lib.Turtle
import com.denis_golubev.turtle_lib.state.models.Coordinates
import com.denis_golubev.turtle_lib.state.models.State
import com.denis_golubev.turtle_lib.toRadian
import kotlin.math.cos
import kotlin.math.sin

// Created by denis on 07.01.2021.
internal class PenUp : Action {

    override fun apply(state: State): State {
        return state.copy()
    }

}

internal class PenDown : Action {

    override fun apply(state: State): State {
        return state.copy()
    }

}

