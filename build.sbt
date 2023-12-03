scalaVersion := "3.3.1"
name := "fontsync"
organization := "org.rubin55"
version := "0.0.1"
logLevel := Level.Info

enablePlugins(ScalaNativePlugin)
import scala.scalanative.build.*
nativeConfig ~= { c =>
  c.withLTO(LTO.none) // or thin
    .withMode(Mode.debug) // or releaseFast
    .withGC(GC.immix) // or commix
}

libraryDependencies ++= Seq(
  "com.github.scopt" %%% "scopt" % "4.1.0",
  "com.lihaoyi" %%% "os-lib" % "0.9.2",
  "com.manyangled" %%% "coulomb-core" % "0.8.0",
  "com.manyangled" %%% "coulomb-units" % "0.8.0",
  "io.circe" %%% "circe-core" % "0.14.6",
  "io.circe" %%% "circe-generic" % "0.14.6",
  "io.circe" %%% "circe-parser" % "0.14.6",
  "com.armanbilge" %%% "circe-scala-yaml" % "0.0.4",
  "org.scala-lang.modules" %%% "scala-xml" % "2.2.0"
)
