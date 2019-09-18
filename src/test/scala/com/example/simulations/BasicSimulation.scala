package com.example

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration._

trait BasicSimulation extends Simulation {

    val baseUrl: String
    val simulationDuration: Duration
    val pauseDuration: Duration
    val scenarioBuilder: ScenarioBuilder

    lazy val headers = Map(
        "Content-Type" -> "application/json",
        "Cache-Control" -> "no-cache"
    )

    lazy val httpProtocol: HttpProtocolBuilder = http
      .baseUrl(baseUrl)
      .headers(headers)

}


