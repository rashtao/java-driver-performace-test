/*
 * DISCLAIMER
 *
 * Copyright 2016 ArangoDB GmbH, Cologne, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright holder is ArangoDB GmbH, Cologne, Germany
 */

package arangodb

import io.gatling.core.action.Action
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.stats.StatsEngine
import io.gatling.core.structure.ScenarioContext
import io.gatling.core.session.Session
import io.gatling.commons.stats.{KO, OK, Status}


/**
 * @author Michele Rastelli
 */
class ArangoActionBuilder(name: String, func: () => Unit) extends ActionBuilder {
  override def build(ctx: ScenarioContext, next: Action): Action =
    new ArangoAction(name, ctx.coreComponents.statsEngine, func, next)
}

class ArangoAction(
                    val name: String,
                    val statsEngine: StatsEngine,
                    func: () => Unit,
                    val next: Action
                  ) extends Action {

  override def execute(session: Session): Unit = {
    val start = System.currentTimeMillis

    val status = try {
      func()
      OK
    } catch {
      case _: Throwable => KO
    }

    val end = System.currentTimeMillis
    statsEngine.logResponse(session, name, start, end, status, None, None)
    next ! session
  }

}
