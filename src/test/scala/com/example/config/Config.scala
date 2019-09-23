package com.example.config

object Config {

  val baseUrl = System.getProperty("baseUrl", "http://localhost:8080")

  val simulationDuration = System.getProperty("simulationDuration", "20000").toInt

  val pauseDuration = System.getProperty("pauseDuration", "5").toInt

  val numberOfUsers = System.getProperty("numberOfUsers", "5").toInt

}
