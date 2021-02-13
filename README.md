# android_turtle
A library for Android that repeats the Turtle interface from Python

### Install:

##### Step 1. Add the JitPack repository to your build file  Step 1. Add the JitPack repository to your build file
  Add it in your root build.gradle at the end of repositories:
```groovy
  allprojects {
		  repositories {
			  ...
			  maven { url 'https://jitpack.io' }
		  }
	}
```
  
#####  Step 2. Add the dependency  Step 2. Add the dependency
  ```groovy
dependencies {
	        implementation 'com.github.GolubevDenis:android_turtle:Tag'
	}
```

### Usage:

Sample of usage


  [![](https://github.com/GolubevDenis/android_turtle/blob/main/L-System%20Sample.png)](https://github.com/GolubevDenis/android_turtle/blob/main/L-System%20Sample.png)


  https://youtu.be/oP6RfJpK8TM

```java
val turtleView = findViewById<TurtleView>(R.id.turtle_view)
turtleView.post {
    turtleView.startPosition(TurtleView.CENTER, TurtleView.END)
    val turtle = Turtle.create()
    turtle.forward(10f)
    turtle.left(35f)
    turtle.pencolor(Color.RED)
    turtle.forward(15f)
    turtle.penup()
    turtle.back(30f)
    turtle.pendown()
    turtleView.turtle = turtle
}
```

The default drawing speed is 10 pixels per step.
To change speed use:
```java
  turtle.speed(Speed.FAST)
```

If you want to draw the all stuff immediately use:
  ```java
turtleView.isDrawImmediately = true
```
