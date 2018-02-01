package com.knoldus.service

import com.knoldus.util.ConfigManager
import org.apache.http.HttpHost
import org.elasticsearch.client.{RestClient, RestHighLevelClient}

object HighLevelRestClient {

  val PORT = ConfigManager.config.getInt("es-port")
  val HOST = ConfigManager.config.getString("es-host")
  val SCHEME = ConfigManager.config.getString("es-scheme")

  val client = new RestHighLevelClient(
    RestClient.builder(new HttpHost(HOST, PORT, SCHEME)))

}
