#!/usr/bin/env bash

# mvn gatling:test -Dgatling.simulationClass=com.example.simulations.BasicSimulationImpl

mvn gatling:test -Dgatling.simulationClass=com.example.simulations.BasicSimulationImpl \
        -DbaseUrl=http://localhost:8081 \
        -DsimulationDuration=20000 \
        -DpauseDuration=2 \
        -DnumberOfUsers=10
