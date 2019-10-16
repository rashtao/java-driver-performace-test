package arangodb

import com.arangodb.{ArangoDB, Protocol}
import io.gatling.core.Predef._

class DriverSimulation extends Simulation {

  private val arango = new ArangoDB.Builder()
    .useProtocol(Protocol.VST)
//    .useProtocol(Protocol.HTTP_VPACK)
//    .useProtocol(Protocol.HTTP_JSON)
    .build()

  private val scn = scenario("getVersion").repeat(100000) {
    exec(
      new ArangoActionBuilder("getVersion", () => {
        arango.getVersion()
      })
    )
  }

  setUp(scn.inject(atOnceUsers(1)))
}
