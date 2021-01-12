package com.denis_golubev.turtle_lib.state

import com.denis_golubev.turtle_lib.Turtle
import com.denis_golubev.turtle_lib.state.models.State

// Created by denis on 10.01.2021.
internal interface StatesSplitter {

    fun split(states: List<State>): List<State>

}

internal class SpeedStatesSplitter(
        private val speed: Int
) : StatesSplitter {

    override fun split(states: List<State>): List<State> {
        if(states.isEmpty())
            return emptyList()

        val result = arrayListOf<State>()
        result.add(states[0])

        for(i in 1 until states.size) {
            val prevState = states[i-1]
            val state = states[i]
            val prevCoordinates = prevState.coordinates
            val coordinates = state.coordinates

            val distance = prevCoordinates.distanceTo(coordinates)
            if(distance > speed) {

            } else {
                result.add(state)
            }
        }
        return emptyList()
    }

}