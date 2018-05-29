
// libraryDependencies +=
//   "com.typesafe.akka" %% "akka-actor" % "2.5.12"

libraryDependencies +=
  "com.typesafe.akka" %% "akka-actor" % "2.5-SNAPSHOT"

scalaVersion := "2.11.11"

lazy val fixResources =
  taskKey[Unit]("Fix application.conf presence on first clean build.")

fixResources := {
  val compileConf = (resourceDirectory in Compile).value / "application.conf"
  IO.copyFile(
    compileConf,
    (classDirectory in Compile).value / "application.conf"
  )
}

compile in Compile := {
  fixResources.value
  (compile in Compile).value
}