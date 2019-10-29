package arangodb.simulations

import arangodb.{Config, DriverScenario}
import com.arangodb.Protocol
import io.gatling.core.Predef._

class DriverHttpVPackSimulation extends Simulation {
  setUp(DriverScenario.createScenario(Protocol.HTTP_VPACK).inject(atOnceUsers(Config.USERS)))
}
