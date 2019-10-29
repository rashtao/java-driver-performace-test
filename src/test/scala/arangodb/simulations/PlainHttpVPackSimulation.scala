package arangodb.simulations

import arangodb.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class PlainHttpVPackSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8529")
    .header("Content-Type", "application/x-velocypack")

  val scn = scenario("getVersion").repeat(Config.REPETITIONS) {
    exec(http("getVersion")
      .get("/_api/version"))
  }

  setUp(scn.inject(atOnceUsers(Config.USERS)).protocols(httpProtocol))
}
