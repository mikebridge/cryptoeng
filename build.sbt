
name := "cryptoeng"

version := "1.0"

//scalaVersion := "2.12.1"
scalaVersion := "2.11.8"


libraryDependencies += "commons-codec" % "commons-codec" % "1.9"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
//libraryDependencies += "com.storm-enroute" %% "scalameter" % "0.7"

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/releases"

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

// scalameter
//testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")
//parallelExecution in Test := false

// SEE: http://www.scalatest.org/user_guide/using_scalatest_with_sbt
logBuffered in Test := false