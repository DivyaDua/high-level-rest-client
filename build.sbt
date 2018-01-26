name := "elastic-search-rest-client"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "org.elasticsearch" % "elasticsearch" % "6.1.2",
  "org.elasticsearch.client" % "elasticsearch-rest-high-level-client" % "6.1.2",
  "com.typesafe.play" %% "play" % "2.6.11",
  "com.typesafe" % "config" % "1.3.2"
)