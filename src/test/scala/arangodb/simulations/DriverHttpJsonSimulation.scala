package arangodb.simulations

import arangodb.{Config, DriverScenario}
import com.arangodb.Protocol
import io.gatling.core.Predef._

class DriverHttpJsonSimulation extends Simulation {
  setUp(DriverScenario.createScenario(Protocol.HTTP_JSON).inject(atOnceUsers(Config.USERS)))
}
