# Halloween Candy Estimator

### Problem Statement


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



