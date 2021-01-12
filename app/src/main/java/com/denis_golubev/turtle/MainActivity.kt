package com.denis_golubev.turtle

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.denis_golubev.turtle_lib.Turtle
import com.denis_golubev.turtle_lib.TurtleView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val turtleView = findViewById<TurtleView>(R.id.turtle_view)


        turtleView.post {
            val turtle = Turtle.buildCenter(turtleView.width, turtleView.height)
            turtle.speed(Turtle.Speed.SLOW)
            turtle.forward(300f)
            turtle.left()

            turtle.speed(Turtle.Speed.SLOW)
            turtle.forward(300f)
            turtle.width(0f)
            turtle.pencolor(Color.RED)
            turtle.left()

            turtle.penup()
            turtle.speed(Turtle.Speed.NORMAL)
            turtle.forward(300f)
            turtle.width(10f)
            turtle.pencolor(Color.BLUE)
            turtle.left()

            turtle.pendown()
            turtle.speed(Turtle.Speed.SLOW)
            turtle.forward(300f)

            turtle.penup()
            turtle.speed(Turtle.Speed.FAST)
            turtle.forward(300f)
            turtle.pendown()

            turtle.width(30f)
            turtle.pencolor(Color.GREEN)
            turtle.back(500f)

            turtle.width(50f)
            turtle.pencolor(Color.RED)
            turtle.forward(500f)


            turtleView.turtle = turtle
        }
    }

    // TODO use L-system as a demo

}