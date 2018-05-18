lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.5",
      version      := "0.1.0-SNAPSHOT"
    )),
    mainClass in (Compile, run) := Some("com.functionalai.mountaincar.Main"),
    name := "MountainCar-ReinforcementLearning",
    libraryDependencies ++= Seq()
  )
