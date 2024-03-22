val scala3Version = "3.4.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala-modularization-framework",
    scalaVersion := scala3Version,
    scalacOptions ++= Seq(
      "-rewrite",
      "-indent",
    )
  )