import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "meastereggs"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    "com.typesafe.akka" % "akka-actor_2.10" % "2.2-M1",
    jdbc,
    anorm
  )
  



  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
