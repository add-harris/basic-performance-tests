package com.example.simulations

import com.example.BasicSimulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulationImpl extends BasicSimulation {

  override val baseUrl: String = "http://localhost:8080"

  override val simulationDuration: Duration = 1000 milliseconds

  override val pauseDuration: Duration = 5 seconds

  override val scenarioBuilder = scenario("BasicSimulationImpl")
    .during(simulationDuration) {
      pace(pauseDuration)
        .exec(http("request_1")
          .get("/")
          .check(status.is(200)))
    }

  setUp(scenarioBuilder.inject(atOnceUsers(1)))
      .protocols(httpProtocol)
      .assertions(
        global.successfulRequests.percent.gte(95),
        global.responseTime.mean.lt(120)
      )

}
