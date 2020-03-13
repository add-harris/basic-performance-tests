package com.example.simulations

import com.example.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import com.example.scenarios.{GetScenario, PostScenario}
import io.gatling.core.structure.{PopulationBuilder, ScenarioBuilder}

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


  //////////////////////////////
  // Setup Multiple Scenarios //
  //////////////////////////////

  val getScenario = GetScenario.getScenario

  val postScenario = PostScenario.getScenario

//  val scenarioList = List(getScenario, postScenario)
  val scenarioList = List(postScenario)


  //////////////////
  // Inject Users //
  //////////////////

  val populationList: List[PopulationBuilder] = scenarioList.map(_.inject(atOnceUsers(numberOfUsers)))

  // other examples in the docs: https://gatling.io/docs/3.2/general/simulation_setup/
  // more complicated examples include:
  // constantUsersPerSec(rate) during(duration)
  // rampUsersPerSec(rate1) to (rate2) during(duration)


  ////////////////////
  // Run Everything //
  ////////////////////

  setUp(populationList) // inject scenarios
      .protocols(httpProtocol) // inject http protocols
      .assertions( // set assertions
        global.successfulRequests.percent.gte(95), // 95% of requests are successful
        global.responseTime.mean.lt(120) // mean response time of all requests is under 120 (millis?) - 1.2 seconds
      )

}
