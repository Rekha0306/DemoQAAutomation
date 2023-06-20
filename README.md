# DemoQAAutomation

---

## Project Purpose
This project aims to validate sample test cases using automation test framework that uses Selenium and Cucumber with Java as the programming language.

Manual test scripts are placed under **DemoQAAutomation/testcases** folder

---

## Tools and Libraries
This project using 2 main tools, Selenium and Cucumber.
The complete list of plugins used can be seen in the `pom.xml` file.

## Requirements
* JDK 1.8 and above
* Maven
* Chrome, Edge and Firefox

## Running Tests
* Clone the repository 
* Navigate to project folder
* Run the tests with the script below
* If browser is not mentioned, default browser is considered as 'Chrome'.
```shell
$ mvn clean install
```
* If you want to run the specific test, use the cucumber tags like this
```shell
$ mvn clean install -Dcucumber.filter.tags="<See below for Tags section>" -Dbrowser=Chrome
```

## Tags
Below Tags are currently supported
* @Regression  : Covers all Test scenerios.
* @loginScenerios : Covers scenerios login to BookStore Application. 
* @Profile : Covers scenerios of profile page.
* @deleteloginUser : deletes loggd in user.

## Test Results
* Test report automatically generates `target` folder after test execution.
* Test Report generates at `target/cucumber-reports/advanced-reports/cucumber-html-reports/overview-features.html`
---