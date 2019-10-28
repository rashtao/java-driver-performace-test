package arangodb

import java.util.UUID

import com.arangodb.entity.BaseDocument
import com.arangodb.{ArangoDB, Protocol}
import io.gatling.core.Predef._

class DriverSimulation extends Simulation {

  private val docKey = "myKey-" + UUID.randomUUID().toString

  private val arango = new ArangoDB.Builder()
    .host("127.0.0.1", 8529)
    .maxConnections(20)
        .useProtocol(Protocol.VST)
//    .useProtocol(Protocol.HTTP_VPACK)
    //    .useProtocol(Protocol.HTTP_JSON)
    .build()

  private val collection = arango.db().collection("myColl")

  if (!collection.exists()) {
    collection.create()
  }

  if (!collection.documentExists(docKey)) {
    collection.insertDocument(new BaseDocument(docKey))
  }

  private val scn = scenario("getDocument")
    .repeat(10000) {
      exec(
        new ArangoActionBuilder("getDocument", () => {
          collection.getDocument(docKey, classOf[BaseDocument])
        })
      )
    }

  setUp(scn.inject(atOnceUsers(50)))
}
