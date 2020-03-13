package com.example.config

object Config {

  val baseUrl = System.getProperty("baseUrl", "http://localhost:8888/basic-service")

  val simulationDuration = System.getProperty("simulationDuration", "10000").toInt

  val pauseDuration = System.getProperty("pauseDuration", "1").toInt

  val numberOfUsers = System.getProperty("numberOfUsers", "10").toInt

}
