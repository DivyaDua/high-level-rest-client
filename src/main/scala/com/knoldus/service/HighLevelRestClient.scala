package com.knoldus.service

import java.time.{LocalDateTime, ZoneOffset}

import com.amazonaws.auth.{AWSCredentialsProvider, AWSStaticCredentialsProvider, BasicAWSCredentials}
import com.google.common.base.Supplier
import com.knoldus.util.ConfigManager
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder
import org.apache.http.{HttpHost, HttpRequestInterceptor}
import org.elasticsearch.client.{RestClient, RestHighLevelClient}
import vc.inreach.aws.request.{AWSSigner, AWSSigningRequestInterceptor}

object HighLevelRestClient {

  val SERVICE_NAME = ConfigManager.config.getString("es-service")
  val REGION = ConfigManager.config.getString("es-service-region")
  val AWS_ACCESS_ID = ConfigManager.config.getString("aws-access-id")
  val AWS_ACCESS_KEY = ConfigManager.config.getString("aws-access-key")
  val AWS_ES_ENDPOINT = ConfigManager.config.getString("aws-es-endpoint")
  val AWS_HOST = ConfigManager.config.getString("aws-es-host")

  /**
    * Creates RestHighLevelClient given the low level RestClientBuilder
    * and sets the hosts that the client will send requests to.
    */

  lazy val client = new RestHighLevelClient(
  RestClient.builder(new HttpHost(AWS_HOST, 80))
    .setHttpClientConfigCallback(
    (httpClientBuilder: HttpAsyncClientBuilder) => httpClientBuilder.addInterceptorLast(requestInterceptor)))

  final val clock = new Supplier[LocalDateTime] {
    override def get(): LocalDateTime = LocalDateTime.now(ZoneOffset.UTC)
  }

  private def requestInterceptor: HttpRequestInterceptor = {
    val awsCredentialsProvider: AWSCredentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials
    (AWS_ACCESS_ID, AWS_ACCESS_KEY))
    val awsSigner = new AWSSigner(awsCredentialsProvider, REGION, SERVICE_NAME, clock)
    new AWSSigningRequestInterceptor(awsSigner)
  }

}
