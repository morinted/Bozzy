name := "Bozzy"

version := "0.6.0"

scalaVersion := "2.11.7"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

// Add managed dependency on ScalaFX library
libraryDependencies ++= Seq(
  "org.scalafx" %% "scalafx" % "8.0.60-R9",
  "org.scalafx" %% "scalafxml-core-sfx8" % "0.2.2",
  "com.typesafe.play" %% "play-json" % "2.5.1",
  "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
)
addCompilerPlugin("org.scalamacros" % "paradise" % "2.0.1" cross CrossVersion.full)

fork := true

