package arangodb.simulations

import arangodb.{Config, DriverScenario}
import com.arangodb.Protocol
import io.gatling.core.Predef._

class DriverVstSimulation extends Simulation {
  setUp(DriverScenario.createScenario(Protocol.VST).inject(atOnceUsers(Config.USERS)))
}
