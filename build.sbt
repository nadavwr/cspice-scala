val Scala2_11 = "2.11.12"
val Scala2_12 = "2.12.4"

lazy val cspice = crossProject(JVMPlatform, NativePlatform)
  .withoutSuffixFor(JVMPlatform)
  .in(file("."))
  .settings(
    organization := "com.github.nadavwr",
    scalaVersion := Scala2_11,
    libraryDependencies += "com.lihaoyi" %%% "utest" % "0.6.3" % "test",
    testFrameworks += new TestFramework("utest.runner.Framework"),
    nativeLinkStubs := true,
    licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
    scalacOptions ++= Seq("-feature", "-deprecation"),
  )
  .configurePlatform(JVMPlatform) { project =>
    project.enablePlugins(JniNative)
  }
  .jvmSettings(
    crossScalaVersions := Seq(Scala2_11, Scala2_12),
    javah := (javah triggeredBy (compile in Compile)).value,
    crossPaths := true,
    (packageBin in Compile) := ((packageBin in Compile) dependsOn javah).value
  )
  .nativeSettings(
    crossScalaVersions := Seq(Scala2_11)
  )

lazy val cspiceJVM = cspice.jvm
lazy val cspiceNative = cspice.native
