# README 

## About the project
* This is Maven project which has pom.xml file includes dependencies.
* Used dependencies: Selenium, TestNG, Log4J and flickr4java.

## Project Structure
### Resources folder contains:
* Chrome driver executable file for running local browser, if driver file isn't supported you can download compatible driver from chrome drivers website.
* Properties file, contains parameters and values used in the script.
* 2 images, jpg image and bmp image for upload tests.
### src main java
* This package has java classes functionalities for testing.
### src main resources
* This directory has log4J configurations file for logging script steps. log folder will be created on running the tests.
### src test
* This package has Karate configurations file and TestNGRunner.xml to run the test class.
### src test java
* This package has java test class UploadFileTest and KarateTestLauncher for presenting Karate tool.
### features
* This directory has Karate feature file with short example of getting response on calling google.com
* You can fund more details about Karate here https://github.com/intuit/karate.
## How to run the tests suite?
* Find TestNGRunner.xml under test package, right click on the file then select run or run as TestNG.
* Please fill your use data inside props.propertis file.
## Scalable way to run tests suite each day
* Please find SclableArchitecture.png file.