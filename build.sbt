import scala.scalanative.build.*

val scala3Version = "3.4.0"

lazy val dsl = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .in(file("dsl"))
  .nativeSettings(
    nativeConfig ~= {
      _.withLTO(LTO.thin)
        .withMode(Mode.releaseFast)
        .withGC(GC.immix)
    }
  )
  .settings(
    name := "dsl",
    scalaVersion := scala3Version,
    scalacOptions ++= Seq(
      "-rewrite",
      "-indent",
    )
  )
