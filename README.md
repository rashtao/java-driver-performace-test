# java-driver-performace-test

To test it out, simply execute the following command:

    mvn gatling:test -Dgatling.simulationClass=arangodb.<simulationClassName>

or simply:

    mvn gatling:test


## Simulations

- [HTTP_VPACK (no driver)](./results/httpsimulation-20191016084242209/index.html)
- [HTTP_JSON (no driver)](./results/httpsimulation-20191016083751482/index.html)
- [VST](./results/drivervstsimulation-20191016081323244/index.html)
- [HTTP_VPACK](./results/driversimulation-20191016083219711/index.html)
- [HTTP_JSON](./results/driversimulation-20191016082949469/index.html)

