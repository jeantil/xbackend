import sbt._
import PlayProject._
import Keys._

object ApplicationBuild extends Build {

  val appName = "XBackend"
  val appVersion = "1.0"

  val appDependencies = { Seq() }

  val main = play.Project (appName, appVersion, appDependencies ).settings(
  )

}
