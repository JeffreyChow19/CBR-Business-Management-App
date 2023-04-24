#!/bin/bash
mvn clean install
java -jar target/CBR-1.0.jar
#chore: fix not loaded javafx in jar