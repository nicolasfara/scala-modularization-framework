import scala.scalanative.build.*

val scala3Version = "3.4.0"

lazy val dsl = crossProject(JSPlatform, JVMPlatform/*, NativePlatform*/)
  .in(file("dsl"))
//  .nativeSettings(
//    nativeConfig ~= {
//      _.withLTO(LTO.thin)
//        .withMode(Mode.releaseFast)
//        .withGC(GC.immix)
//    }
//  )
  .settings(
    name := "dsl",
    scalaVersion := scala3Version,
    scalacOptions ++= Seq(
      "-rewrite",
      "-indent",
    ),
    libraryDependencies ++= Seq(
      "io.circe" %%% "circe-core" % "0.14.1",
      "io.circe" %%% "circe-generic" % "0.14.1",
      "io.circe" %%% "circe-parser" % "0.14.1"
    )
  )
