ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.15"
autoScalaLibrary:=false
lazy val root = (project in file("."))
  .settings(
    name := "SparkStreamingDemo",
    idePackagePrefix := Some("org.SparkStreaming")
  )

val sparkVersion ="3.2.3"

libraryDependencies +="org.apache.spark" %% "spark-core" % sparkVersion % "provided"
libraryDependencies+= "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"
