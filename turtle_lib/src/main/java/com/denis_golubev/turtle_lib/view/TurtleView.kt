package com.denis_golubev.turtle_lib.view

import android.content.Context
import android.graphics.Canvas
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import com.denis_golubev.turtle_lib.Turtle
import com.denis_golubev.turtle_lib.TurtleImpl
import com.denis_golubev.turtle_lib.state.TurtlePaints
import com.denis_golubev.turtle_lib.state.models.Coordinates
import com.denis_golubev.turtle_lib.state.models.State
import java.lang.Exception

class TurtleView : View {

    companion object {
        const val START = -923136831f
        const val CENTER = -92345133f
        const val END = -923414539f

        const val INTERVAL = (1000 / 60).toLong()
    }

    private var frame = 0

    private var startX = 0f
    private var startY = 0f
    private var isStartPositionSetUp = false
    fun startPosition(x: Float, y: Float) {
        startX = calculatePosition(x, measuredWidth.toFloat())
        startY = calculatePosition(y, measuredHeight.toFloat())
        isStartPositionSetUp = true
    }

    private fun calculatePosition(coordinate: Float, size: Float): Float {
        return when (coordinate) {
            CENTER -> size / 2f
            START -> 0f
            END -> size
            else -> coordinate
        }
    }

    var isDrawImmediately = false
        set(value) {
            field = value
            if(value)
                drawImmediately()
        }

    private fun applyIsImmediately() {
        if(isDrawImmediately)
            drawImmediately()
    }

    private fun drawImmediately() {
        frame = turtle?.getStates()?.size ?: frame
    }

    private var paintBuilder: TurtlePaints? = null

    var turtle: Turtle? = null
        set(value) {
            field = value

            if(value == null)
                return

            if(value.getStates().size <= 1)
                throw Exception("The turtle must do at least one action before it can be drawn")

            paintBuilder = TurtlePaints(value.getAppearances())

            applyIsImmediately()
            scheduleInvalidation()
            invalidate()
        }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private fun init() {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if(!isStartPositionSetUp)
            startPosition(CENTER, CENTER)
    }

    private fun scheduleInvalidation() {
        postDelayed({
            if(!isShown) {
                scheduleInvalidation()
                return@postDelayed
            }

            if(isDrawn)
                return@postDelayed

            frame++
            invalidate()
            scheduleInvalidation()
        }, INTERVAL)
    }

    private var isDrawn = false
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val turtle = this.turtle as TurtleImpl? ?: return

        var passedDistance = 0f
        val maxDistance = (frame + 1f) * turtle.speed.value

        for(index in 1 until turtle.getStates().size) {
            val lastState = turtle.getStates()[index - 1]
            val currentState = turtle.getStates()[index]

            val lastCoordinates = lastState.coordinates
            val currentCoordinates = lastCoordinates.limitMaxDistanceTo(currentState.coordinates, maxDistance - passedDistance)

            passedDistance += lastCoordinates.distanceTo(currentCoordinates)

            drawStep(currentState, canvas, lastCoordinates, currentCoordinates)

            if(passedDistance >= maxDistance)
                return
        }
        isDrawn = true
    }

    private fun drawStep(currentState: State, canvas: Canvas, lastCoordinates: Coordinates, currentCoordinates: Coordinates) {
        val paint = paintBuilder?.getPaintFor(currentState.appearance)!!
        canvas.drawLine(lastCoordinates.x + startX, lastCoordinates.y + startY, currentCoordinates.x + startX, currentCoordinates.y + startY, paint)
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        val savedState = TurtleViewSavedState(superState)
        savedState.frame = this.frame
        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        val savedState = state as TurtleViewSavedState
        this.frame = savedState.frame

        super.onRestoreInstanceState(savedState.superState)
    }


    private class TurtleViewSavedState : BaseSavedState {
        var frame = 0

        constructor(superState: Parcelable?) : super(superState) {}
        private constructor(source: Parcel) : super(source) {
            frame = source.readInt()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(frame)
        }

        companion object {
            val CREATOR: Parcelable.Creator<TurtleViewSavedState?> = object : Parcelable.Creator<TurtleViewSavedState?> {
                override fun createFromParcel(source: Parcel): TurtleViewSavedState? {
                    return TurtleViewSavedState(source)
                }

                override fun newArray(size: Int): Array<TurtleViewSavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }

}