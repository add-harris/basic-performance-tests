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
    val numberOfUsers: Int



}


