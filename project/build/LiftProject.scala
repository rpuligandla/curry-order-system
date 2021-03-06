import sbt._

class LiftProject(info: ProjectInfo) extends DefaultWebProject(info) with IdeaProject {
  val liftVersion = property[Version]

  // uncomment the following if you want to use the snapshot repo
  //  val scalatoolsSnapshot = ScalaToolsSnapshots

  // If you're using JRebel for Lift development, uncomment
  // this line
  override def scanDirectories = Nil

  lazy val JavaNet = "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"

  override def libraryDependencies = Set(
    "net.liftweb" %% "lift-webkit" % liftVersion.value.toString % "compile",
    "net.liftweb" %% "lift-mapper" % liftVersion.value.toString % "compile",
    "org.mortbay.jetty" % "jetty" % "6.1.26" % "test",
    "junit" % "junit" % "4.7" % "test",
    "log4j" % "log4j" % "1.2.16",
    "org.slf4j" % "slf4j-log4j12" % "1.6.1",
    "org.scala-tools.testing" %% "specs" % "1.6.8" % "test",
    "org.mockito" % "mockito-all" % "1.8.5" % "test",
    "mysql" % "mysql-connector-java" % "5.1.9",
    "com.h2database" % "h2" % "1.2.147"
  ) ++ super.libraryDependencies

  override def jettyPort = 9333
}
