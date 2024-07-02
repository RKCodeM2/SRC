# NCS Test Automation Framework

---
## Overview

This NCS Test Automation Framework is designed for automating tests using Selenium / Appium / Qmetry under the hood with TestNG, built in Java, and managed using Maven.
The automated tests are designed to augment the testing capability of Manual Testers and increase Quality Assurance. 
This is achieved by automatically executing the tests within the Azure DevOps pipeline to ensure fewer bugs make their way through on each deployment.

## Project Structure

- `src/main/java/com/ahq`: Contains the Java source code for test automation.

- `resources/data`: Houses CSV / JSON/ XLSX files for data-driven testing.

- `resources/locators`: Stores web element locators.

- `environment-config`: Contains environment-specific configuration files.

- `tests`: Stores Qmetry BDD tests.

- `test-runner`: Contains TestNG runner XML files.

## Execution

- **Maven Build**: The project is built and tests are executed using Maven.
- **-DtestSuiteFile** is used to determine which TestNG runner file will be executed. This is implemented to allow the DevOps pipeline to control if Regression or Smoke tests will be executed.
- **-Drow** is used to determine which test-pair should be executed. This typically corresponds to the row of data in the CSV data files. This is required due to time limits on the DevOps pipeline agents,
leading to the need to split the tests into multiple jobs.
- **-Denv** is used to determine which environment the tests will be executed against. This is implemented to allow the DevOps pipeline to control which environment the tests will be executed against and corresponds to the folder
name in the environment-config folder.
- An example test execution command is as follows:
  ```bash
  mvn clean verify -DtestSuiteFile="test-runner/smoke.xml" -Drow=1 -Denv=TST
  
**Note:** The **-Drow** and **-Denv** arguments are ommited in the DevOps pipeline as these options are passed in as Environment Variables instead, and are read prior to test execution. Due to QMetry limitations,
the TestNG runner can not be modified at runtime, so the **-DtestSuiteFile** argument is required to be passed in at runtime which houses the suite specific options.

## Test Reports

- **Extent Reports**: After test execution, Extent Reports are generated and stored in the `extent-reports` folder. The reports are fabricated using Test and Step listeners within `src/main/java/com/ahq/listeners`.


## Framework Components

- **Qmetry with TestNG**: The framework is built on the Qmetry platform, utilizing the TestNG testing framework.

- **Data-Driven Testing**: CSV files in `src/main/resources/data` drive test data.

- **Web Element Locators**: Locators for web elements are stored in `src/main/resources/locators`.

- **Environment Config**: Environment-specific configuration files reside in `src/main/resources/environment-config`.

- **Qmetry BDD Tests**: Tests using Qmetry BDD are stored in `src/test/java/tests`.

- **TestNG Runner XML**: TestNG runner XML files are located in `src/test/resources/test-runner`.

## Dependencies

Ensure you have the following dependencies installed:

- Java 17 or higher
- Maven 3.8.0 or higher

## Configuration

- **Environment**: Customize your test environment by modifying configuration files in `environment-config`
This currently just includes the base URLs for each application in the environment.
- **Test Properties**: Customize your test properties by modifying configuration files in `resources/application.properties`
- **Driver/Browser Properties**: Customize your driver/browser properties by modifying configuration files in `resources/web.properties`

## Execution
- **Default Runner**: mvn install (This will run default runner configured in pom.xml)
- **Overide Runner**: mvn install -DtestSuiteFile="test-runner/<your-runner-file>.xml"


## Documentation

For more detailed information and documentation on the tools used in this framework, refer to the following resources:

- [QMetry Documentation](https://qmetry.github.io/qaf/latest/docs.html)
- [TestNG Documentation](https://testng.org/doc/documentation-main.html)
- [Extent Reports Documentation](https://www.extentreports.com/docs/versions/5/java/index.html)


## License
This NCS Test Automation Framework is licensed under the [MIT License](https://mit-license.org/).

---

