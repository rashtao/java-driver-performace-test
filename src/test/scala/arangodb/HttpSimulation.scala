package arangodb

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class HttpSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8529")
//    .header("Content-Type", "application/x-velocypack")

  val scn = scenario("getVersion").repeat(500000) {
    exec(http("getVersion")
      .get("/_api/version"))
  }

  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
}
