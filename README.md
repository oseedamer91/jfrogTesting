# jfrogTesting

Hi There


## Here are the steps to run this code test ##

Command line:
- press green code icon , copy url
- create new folder locally
- open bash
- type : git clone <copied url>
- type : mvn clean istall
(this will generate target folder with Jar file <user-test-api-app-1.0-SNAPSHOT>)
- type : java -jar target/user-test-api-app-1.0-SNAPSHOT
(this will run the jar file)



Eclipse IDE:
- press green code icon , copy url
- create new folder locally <workspace>
- create new folder locally
- open bash
- type : git clone <copied url>
- go to eclipse  - > File -> import -> Existing Maven Projects -> navigate to download project
- build project then run.

Starting Application:

===============  Main Menu  ===============
- 1: Deploy a jar file to artifactory
- 2: Create api key
- 3: Regenerate api key
- 4: Revoke api key
- 5: Exit
- Insert option 1 to 5: <type number here>

Notes:
- 1: this will send <user-test-api-app-1.0-SNAPSHOT> to artifactory lib-release folder , so make sure to build project to create the jar file.
- 2: testing if there is key.
- 3: testing if regenerate will create distinct keys
- 4: testing if key will be removed if there is.
