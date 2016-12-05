logLevel := Level.Warn

resolvers += Classpaths.sbtPluginReleases

resolvers += Classpaths.typesafeReleases

// SEE: http://www.scalatest.org/user_guide/using_scalatest_with_sbt
resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"
addSbtPlugin("com.artima.supersafe" % "sbtplugin" % "1.1.0")

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.0")