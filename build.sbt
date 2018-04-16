name := "elastic-search-rest-client"

version := "0.1"

scalaVersion := "2.12.4"

val awsJavaCoreSDK = "com.amazonaws" % "aws-java-sdk-core" % "1.11.256"
val awsSigningRequestInterceptor = "vc.inreach.aws" % "aws-signing-request-interceptor" % "0.0.20"
val elasticSearch = "org.elasticsearch" % "elasticsearch" % "5.6.0"
val elasticSearchClient = "org.elasticsearch.client" % "elasticsearch-rest-high-level-client" % "5.6.0"
val play = "com.typesafe.play" %% "play" % "2.6.11"

libraryDependencies ++= Seq(
  elasticSearch,
  elasticSearchClient,
  play,
  awsJavaCoreSDK,
  awsSigningRequestInterceptor
)