package com.denis_golubev.turtle_lib

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.denis_golubev.turtle_lib.state.TurtlePaints


/**
 * TODO: document your custom view class.
 */
class TurtleView : View {

    private var paintBuilder: TurtlePaints? = null

    var turtle: Turtle? = null
        set(value) {
            field = value

            if(value == null)
                return

            paintBuilder = TurtlePaints(value.getAppearances())
            runDrawing()
            invalidate()
        }

    private val defaultPaint by lazy {
        TextPaint().apply {
            color = Color.BLACK
        }
    }
    private val turtleMarkPaint by lazy {
        TextPaint().apply {
            this.color = Color.BLACK
            this.textSize = 50f
            this.isFakeBoldText = true
        }
    }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {}

    private var frame = 0
    private fun runDrawing() {
        postDelayed({
            if(!isShown) {
                runDrawing()
                return@postDelayed
            }
            if(turtle?.isThereStatesToShow(frame) == false)
                return@postDelayed

            frame++
            invalidate()
            runDrawing()
        }, 1000 / 60)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val turtle = this.turtle as TurtleImpl? ?: return

        val states = turtle.getStatesForFrame(frame)
        if(states.isEmpty())
            return

        for(index in 1 until states.size) {
            val lastState = turtle.states[index - 1]
            val currentState = turtle.states[index]

            val lastCoordinates = lastState.coordinates
            val currentCoordinates = currentState.coordinates

            val paint = paintBuilder?.getPaint(index) ?: defaultPaint

            canvas.drawLine(lastCoordinates.x, lastCoordinates.y, currentCoordinates.x, currentCoordinates.y, paint)
        }

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