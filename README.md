# Ghost Town

## Build
`mvn clean install`

## Run
`java -cp ./target/group16-1.0-SNAPSHOT.jar gtown.GameEngine`<br>
or<br>
`mvn exec:java -Dexec.mainClass="gtown.GameEngine"` (this command doesn't require you to build the project first)

## Test
Run all unit tests and integration tests<br>
    `mvn clean verify -P integration-test`
<br>
Run only unit tests<br>
`mvn test`
