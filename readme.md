# Project description

Project with automated test using java + gradle + selenium + testNG + allure to test adding a new computer by filling the form on the page
"http://computer-database.gatling.io/computers/new" and verifying its appearance in the table with all registered
computers

Testing consists of 5 steps:
1. Check button Add a new computer redirects to a correct page
2. Fill all fields on page and check button Create this computer redirects to a correct page

    field values:

    name = random UUID

    introduced = 10 years ago

    discontinued = today

    company = Digital Equipment Corporation
3. Check Alert 'Done ! Computer [computerName] has been created' is displayed on the page
4. Check by seeking computer is present in table
5. Remove added computer (only if all previous tests are passed)
   
# How to run
1. Windows 10 x 64 is needed
2. Ensure JDK 8 is installed
3. Install Opera (win64 version)
4. Run:
   
        ./gradlew -i clean build 
        ./gradlew -i runTests   
        ./gradlew allureReport      
        ./gradlew allureServe
