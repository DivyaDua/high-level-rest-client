package com.knoldus.util

import com.typesafe.config.ConfigFactory

/**
  * Load the config file once and available for whole microservice.
  *
  */
object ConfigManager {
  lazy val config = ConfigFactory.load()
}
