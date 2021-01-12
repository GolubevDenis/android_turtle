package com.denis_golubev.turtle_lib.actions.turn

import com.denis_golubev.turtle_lib.Turtle
import com.denis_golubev.turtle_lib.actions.Action

// Created by denis on 07.01.2021.
internal class Right(
    value: Float
) : Turn(value) {

    override fun calculate(currentAngle: Float, turnDegrees: Float): Float = currentAngle - turnDegrees

}
