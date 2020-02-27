
# To run

run command:

`mvn gatling:test -Dgatling.simulationClass=com.example.simulations.BasicSimulationImpl`

or

`./run-tests.sh`

## 

This is currently set to run against an imaginary service running on [http://localhost:8080](http://localhost:8080).

## Scenarios

### Get

It does a _GET_ request to http://localhost:8080/ expecting a `200` response back.

### Post

It does a _POST_ request http://localhost:8080/ with the following data:

```json
{
  "name": "Steven",
  "surname": "Toast"
}
```

and expects a `204` response back.