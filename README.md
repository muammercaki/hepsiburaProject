# Gauge Project in Java

This is a case project for testing web automation with Gauge. This project tests the search functionality of the Hepsiburada application.

# Running this hepsiburada

The tests are run on Chrome by default.

# Prerequisites
This project requires the following software to run.


Java 1.7 or higher

Note that Gauge works with Java 1.6 and higher. But this project uses Java 14

Gauge

Gauge Java plugin
  
Can be installed using gauge install java
  
Chrome

# Run specs
If you already have Maven installed, you can execute specs as mvn test This runs Gauge specs with Maven.

This uses Chrome as default browser for specs execution. Make sure Chrome is installed in your machine and chromedriver is in PATH.

If you want to use Firefox/IE as browser, pass the corresponding argument to set browser environment as follows:

mvn gauge:execute -DspecsDir=specs -Denv="firefox"
or
mvn gauge:execute -DspecsDir=specs -Denv="ie"


# Topics covered in the hepsiburada project
* Use Webdriver as base of implementation
* Concepts (Search, Search Listing, Reviews Tab, Reviews Count Control, Reviews Yes Button, Reviews Alert)
* API (GET & POST Request)
* Specification, Search Scenario & Step usage
* External datasource (special param)
* Using Gauge with Selenium Webdriver
* Running Gauge specs with Maven
