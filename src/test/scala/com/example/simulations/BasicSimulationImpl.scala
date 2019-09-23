package com.example.simulations

import com.example.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration._

class BasicSimulationImpl extends Simulation {

  //////////////////////
  // Tests Parameters //
  //////////////////////

  val baseUrl: String = Config.baseUrl

  val simulationDuration: Duration = Config.simulationDuration milliseconds

  val pauseDuration : Duration = Config.pauseDuration seconds

  val numberOfUsers: Int = Config.numberOfUsers


  ///////////////////////
  // Common http stuff //
  ///////////////////////

  val headers = Map(
    "Content-Type" -> "application/json",
    "Cache-Control" -> "no-cache",
    "Authorization" -> getAuth
  )

  def getAuth = "Basic YWxhZGRpbjpvcGVuc2VzYW1limport"

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(baseUrl)
    .headers(headers)


  ////////////////////
  // Setup scenario //
  ////////////////////

  val scenarioBuilder = scenario("BasicSimulationImpl")
    .during(simulationDuration) { // how long the simulation will run for i.e 60 seconds
      pace(pauseDuration) // how frequently the the action is executed
        .exec(http("basic guest request") // execute action
          .get("/")
          .check(status.is(200))) // check result
    }


  /////////////////
  // Setup Users //
  /////////////////

  val populationBuilder = scenarioBuilder.inject(atOnceUsers(numberOfUsers)) // inject x number of users all at once

  // other examples in the docs: https://gatling.io/docs/3.2/general/simulation_setup/
  // more complicated examples include:
  // constantUsersPerSec(rate) during(duration)
  // rampUsersPerSec(rate1) to (rate2) during(duration)


  ////////////////////
  // Run Everything //
  ////////////////////

  setUp(populationBuilder) // inject scenarios
      .protocols(httpProtocol) // inject http protocols
      .assertions( // set assertions
        global.successfulRequests.percent.gte(95), // 95% of requests are successful
        global.responseTime.mean.lt(120) // mean response time of all requests is under 120 (millis?) - 1.2 seconds
      )

}
