name := "seeping"

scalaVersion := "2.10.3"

seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

libraryDependencies ++= {
  Seq (
    "joda-time" % "joda-time" % "2.3",
    "org.joda" % "joda-convert" % "1.5"
  )
}

scalacOptions in Compile ++= Seq("-unchecked", "-deprecation", "-feature")

logLevel := Level.Info

offline := true

parallelExecution in Test := false

// After -o, "D" shows durations, "S" show short stacktraces, "F" shows full stacktraces
testOptions in Test += Tests.Argument("-oDS")
