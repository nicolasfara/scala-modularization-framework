val scala3Version = "3.3.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala-modularization-framework",
    scalaVersion := scala3Version,
  )