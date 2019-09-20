package com.example.simulations

import com.example.BasicSimulation
import com.example.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BasicSimulationImpl extends BasicSimulation {

  override val baseUrl: String = Config.baseUrl

  override val simulationDuration: Duration = Config.simulationDuration milliseconds

  override val pauseDuration : Duration = Config.pauseDuration seconds

  override val numberOfUsers: Int = Config.numberOfUsers

  override val scenarioBuilder = scenario("BasicSimulationImpl")
    .during(simulationDuration) { // how long the simulation will run for i.e 60 seconds
      pace(pauseDuration) // how frequently the the action is executed
        .exec(http("request_1") // execute action
          .get("/")
          .check(status.is(200))) // check result
    }


  val populationBuilder = scenarioBuilder.inject(atOnceUsers(numberOfUsers)) // inject x number of users all at once
  // other examples in the docs: https://gatling.io/docs/3.2/general/simulation_setup/
  // more complicated examples include:
  // constantUsersPerSec(rate) during(duration)
  // rampUsersPerSec(rate1) to (rate2) during(duration)

  setUp(populationBuilder)
      .protocols(httpProtocol) // set common http attributes i.e. baseUrl, headers
      .assertions( // set assertions
        global.successfulRequests.percent.gte(95), // 95% of requests are successful
        global.responseTime.mean.lt(120) // mean response time of all requests is under 120 (millis?)
      )

}
