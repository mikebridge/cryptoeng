
name := "cryptoeng"

version := "1.0"

//scalaVersion := "2.12.1"
scalaVersion := "2.11.8"


libraryDependencies += "commons-codec" % "commons-codec" % "1.9"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

// for debugging sbt problems
logLevel := Level.Debug

scalacOptions += "-deprecation"

coverageMinimum := 70

coverageFailOnMinimum := false
coverageExcludedPackages := "java.*"

coverageEnabled := true

coverageHighlighting := {
  if (scalaBinaryVersion.value == "2.11")
    true
  else
    false
}


// SEE: http://www.scalatest.org/user_guide/using_scalatest_with_sbt
logBuffered in Test := false