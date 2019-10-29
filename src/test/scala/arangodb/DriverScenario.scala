package arangodb

import java.util.UUID

import com.arangodb.entity.BaseDocument
import com.arangodb.{ArangoDB, Protocol}
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object DriverScenario {
  private val docKey = "myKey-" + UUID.randomUUID().toString
  private val collectionName = "myColl-" + UUID.randomUUID().toString

  def createScenario(protocol: Protocol): ScenarioBuilder = {

    val arango = new ArangoDB.Builder()
      .host("localhost", 8529)
      .useProtocol(protocol)
      .build()

    val collection = arango.db().collection(collectionName)

    if (!collection.exists()) {
      collection.create()
    }

    if (!collection.documentExists(docKey)) {
      collection.insertDocument(new BaseDocument(docKey))
    }

    scenario("getDocument")
      .repeat(Config.REPETITIONS) {
        exec(
          new ArangoActionBuilder("getDocument", () => {
            collection.getDocument(docKey, classOf[BaseDocument])
          })
        )
      }
  }
}
