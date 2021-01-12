package com.denis_golubev.turtle_lib

import kotlin.math.absoluteValue

// Created by denis on 07.01.2021.
internal typealias Degree = Float
internal typealias Radian = Float
internal fun Degree.toRadian(): Radian = (this / 180 * Math.PI).toFloat()
internal fun Radian.toDegree(): Degree = (this * 180 / Math.PI).toFloat()

internal fun Float.normalizeDegree(): Float =
        when {
            this > 0 -> this % 360
            this < 0 -> 360 - (this.absoluteValue % 360)
            else -> 0f
        }