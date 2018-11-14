# Halloween Candy Estimator

### Problem Statement
Trouble knowing how many candies to buy this Holloween? Would you like to be able to tell your customers how many candies to buy?

From today the problem is in the past, because the **Halloween Candy Estimator API** has arrived.

#### Objective
Build a JSON REST service which allow users entering an address and the number of candies they expect to give every child, and the service will return how many candies are needed to buy for halloween.

Our application returns, in a **JSON** format, the estimated amount of candy based on the number of children under 14 living near the address entered.

For this users only have to register at [Halloween Candy Estimator API](http://18.191.31.27:8080/candy-estimator) and we give you an API KEY to get access to the service.

### Project Technologies/Techniques
* Security/Authentication
    * Tomcat's JDBC Realm Authentication
    * Admin role
    * User role
    * All: anyone can view information pages (no login)
* Database
    * MySQL
    * Store Users and Roles.
    * Store all history data of past estimates.
* ORM Framework
    * Hibernate 5
* Dependency Management
    * Maven
* Continuing Integration and Delivery
    * AWS CodePipeline
    * AWS CodeDeploy
    * AWS CodeBuild
* Web Services consumed using Java
    * [GeoLife by pitneybowes.com](https://locate.pitneybowes.com/geolife)
* CSS
    * Bootstrap 4
* Data Validation
    * Bootstrap Validator for front end
* Logging
    * Configurable logging using Log4J2. In production, only errors will normally be logged, but logging at a debug level can be turned on to facilitate trouble-shooting.
* Hosting
    * AWS
* Project Lombok to eliminate boilerplate code like getters/setters/equals
* Unit Testing
    * JUnit tests to achieve 80%+ code coverage
* IDE: IntelliJ IDEA

### Supporting Materials
* [Database Schema](db/design/CandyEstimatorERD.png)
* [Project Plan](ProjectPlan.md)
* [Time Log](TimeLog.md)
