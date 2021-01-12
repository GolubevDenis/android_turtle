package com.denis_golubev.turtle_lib.actions

import com.denis_golubev.turtle_lib.Turtle
import com.denis_golubev.turtle_lib.state.models.State

// Created by denis on 07.01.2021.
internal interface Action {

    /**
    * Creates copy of Turtle.State and applies the changes to this copy.
    * */
    fun apply(state: State): State

}