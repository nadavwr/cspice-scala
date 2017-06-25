import sbtcrossproject.{crossProject, CrossType}

val Scala_2_12 = "2.12.2"
val Scala_2_11 = "2.11.11"
def crossAlias(aliasName: String, commandName: String, projectNames: String*): Command =
  BasicCommands.newAlias(aliasName, {
    projectNames
      .map { projectName =>
        s""";++$Scala_2_12
           |;${projectName}JVM/$commandName
           |;++$Scala_2_11
           |;${projectName}JVM/$commandName
           |;${projectName}Native/$commandName
         """.stripMargin
      }.mkString
  })

def forallAlias(aliasName: String, commandName: String, projectNames: String*): Command =
  BasicCommands.newAlias(aliasName, {
    projectNames
      .map { projectName =>
        s""";${projectName}JVM/$commandName
           |;${projectName}Native/$commandName
         """.stripMargin
      }.mkString
  })

lazy val commonSettings = Def.settings(
  scalaVersion := Scala_2_11,
  organization := "com.github.nadavwr",
  publishArtifact in (Compile, packageDoc) := false,
  licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
)

lazy val cspiceRoot = (project in file("."))
  .settings(
    commands += crossAlias("publishLocal", "publishLocal", "cspice"),
    commands += crossAlias("publish", "publish", "cspice"),
    commands += crossAlias("package", "package", "cspice"),
    commands += crossAlias("test", "test", "cspiceTest"),
    commands += forallAlias("clean", "clean", "cspice", "cspiceTest")
  )

lazy val cspice = crossProject(JVMPlatform, NativePlatform)
  .in(file("."))
  .settings(
    commonSettings,
    moduleName := "spice-scala"
  )
  .configurePlatform(JVMPlatform)(p => p.enablePlugins(JniNative))
  .jvmSettings(
    crossPaths := true
  )

lazy val cspiceJVM = cspice.jvm
lazy val cspiceNative = cspice.native

lazy val cspiceTest = crossProject(JVMPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .settings(commonSettings)
  .settings(
    resolvers += Resolver.bintrayRepo("nadavwr", "maven"),
    libraryDependencies += "com.github.nadavwr" %%% "makeshift" % "0.2.0",
    test := { (run in Compile).toTask("").value }
  )
  .dependsOn(cspice)

lazy val cspiceTestJVM = cspiceTest.jvm
lazy val cspiceTestNative = cspiceTest.native
