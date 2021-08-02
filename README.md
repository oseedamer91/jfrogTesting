# jfrogTesting

Hi There


Here are the steps to run this code test 
- press green code button , copy url
- create new folder locally

# Command line #
- open bash
- type : git clone <copied url>
- type : mvn clean istall
(this will generate target folder with Jar file <user-test-api-app-1.0-SNAPSHOT>)
- type : java -jar target/user-test-api-app-1.0-SNAPSHOT
(this will run the jar file)



# Eclipse IDE #
- create new folder locally <workspace>
- open bash
- type : git clone <copied url>
- once you have download the application into workspace
- go to eclipse  - > File -> import -> Existing Maven Projects -> navigate to downloaded project
- build (mvn clean install)project then run.

Starting Application:

===============  Main Menu  ===============
- 1: Deploy a jar file to artifactory
- 2: Create api key
- 3: Regenerate api key
- 4: Revoke api key
- 5: Exit
- Insert option 1 to 5: [type number here]

Notes:
- 1: this will send <user-test-api-app-1.0-SNAPSHOT> to artifactory lib-release folder , so make sure to build project to create the jar file.
- 2: testing if there is key.
- 3: testing if regenerate will create distinct keys
- 4: testing if key will be removed if there is.
