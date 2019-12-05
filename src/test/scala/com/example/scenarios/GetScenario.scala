package com.example.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.structure.ScenarioBuilder
import com.example.config.Config

import scala.concurrent.duration._

object GetScenario {

  val pauseDuration : Duration = Config.pauseDuration seconds
  val simulationDuration: Duration = Config.simulationDuration milliseconds

  def getScenario: ScenarioBuilder = scenario("GetScenario")
      .during(simulationDuration) { // how long the simulation will run for i.e 60 seconds
        pace(pauseDuration) // how frequently the the action is executed
          .exec(http("basic get request") // execute action
          .get("/")
          .check(status.is(200))) // check result
    }

}
