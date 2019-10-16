import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder

object Engine extends App {

  val props = new GatlingPropertiesBuilder()
    .resourcesDirectory(IDEPathHelper.resourcesDirectory.toString)
    .resultsDirectory(IDEPathHelper.resultsDirectory.toString)
    .binariesDirectory(IDEPathHelper.mavenBinariesDirectory.toString)
    //		.simulationClass("arangodb.HttpSimulation")
    .simulationClass("arangodb.DriverSimulation")

  Gatling.fromMap(props.build)
}
