val scala3Version = "3.3.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "modularization-scala",
    scalaVersion := scala3Version,
  )