package com.denis_golubev.turtle_lib.actions.turn

import com.denis_golubev.turtle_lib.Turtle
import com.denis_golubev.turtle_lib.actions.turn.Turn
import com.denis_golubev.turtle_lib.normalizeDegree
import kotlin.math.cos
import kotlin.math.sin

// Created by denis on 07.01.2021.
internal class Left(
    value: Float
) : Turn(value) {

    override fun calculate(currentAngle: Float, turnDegrees: Float): Float = currentAngle + turnDegrees

}
