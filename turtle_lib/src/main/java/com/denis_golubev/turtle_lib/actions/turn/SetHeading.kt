package com.denis_golubev.turtle_lib.actions.turn

import com.denis_golubev.turtle_lib.actions.Action
import com.denis_golubev.turtle_lib.normalizeDegree
import com.denis_golubev.turtle_lib.state.models.State

// Created by denis on 10.01.2021.
internal class SetHeading(
        value: Float
)  : Turn(value) {

    override fun calculate(currentAngle: Float, turnDegrees: Float): Float = value

}