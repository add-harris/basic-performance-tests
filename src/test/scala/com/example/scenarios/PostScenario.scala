package com.example.scenarios

import com.example.config.Config
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.core.body.Body
import io.gatling.http.Predef._

import scala.concurrent.duration._

object PostScenario {

  val pauseDuration : Duration = Config.pauseDuration seconds
  val simulationDuration: Duration = Config.simulationDuration milliseconds

  val json: String = """{
                        |"name": "Steven",
                        |"surname": "Toast"
                        |}""".stripMargin

  val postBody: Body = StringBody(json)

  def getScenario: ScenarioBuilder = scenario("PostScenario")
      .during(simulationDuration) { // how long the simulation will run for i.e 60 seconds
        pace(pauseDuration) // how frequently the the action is executed
          .exec(http("basic post request") // execute action
          .post("/")
          .header("X-add", "additional headers")
          .body(postBody)
          .check(status.is(204))) // check result
    }

}
