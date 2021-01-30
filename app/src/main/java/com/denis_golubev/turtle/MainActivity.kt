package com.denis_golubev.turtle

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.denis_golubev.turtle_lib.Turtle
import com.denis_golubev.turtle_lib.view.TurtleView
import com.denis_golubev.turtle_lib.state.models.Coordinates
import java.lang.StringBuilder
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val turtleView = findViewById<TurtleView>(R.id.turtle_view)

        turtleView.post {
            turtleView.startPosition(TurtleView.CENTER, TurtleView.END)

            val turtle = Turtle.create()

            val lSystemSource = LSystemGenerator().generate()
            LSystemTurtleDrawer(turtle).draw(lSystemSource)

            turtleView.turtle = turtle
        }
    }

}

class LSystemTurtleDrawer(
        private val turtle: Turtle
) {

    fun draw(lSystemSource: String) {
        val stack = Stack<Coordinates>()

        turtle.pencolor(Color.YELLOW)
        turtle.speed(Turtle.Speed.FASTEST)
        turtle.left(10f)

        for (c in lSystemSource) {
            when (c.toString()) {
                "F" -> turtle.forward(5f)
                "-" -> turtle.left(Random().nextInt(33).toFloat())
                "+" -> turtle.right(Random().nextInt(33).toFloat())
                "[" -> stack.push(turtle.getCoordinates())
                "]" -> turtle.setCoordinates(stack.pop())
            }
        }
    }
}

class LSystemGenerator(
        private val oxiome: String = OXIOME,
        private val rules: Map<String, String> = RULES
) {

    companion object {
        private const val OXIOME = "X"
        private const val ITERATIONS = 7
        private val RULES = mapOf(
                "X" to "F−[[X]+X]+F[+FX]−X",
                "F" to "FF"
        )
    }

    fun generate(iterations: Int = ITERATIONS): String {
        var system = oxiome

        for (i in 0 until iterations) {
            val builder = StringBuilder()
            for (c in system) {
                val appliedRule = rules[c.toString()] ?: c.toString()
                builder.append(appliedRule)
            }
            system = builder.toString()
        }

        return system
    }

}