**Online Theatre**

Imitation of work online theatre with basic functionality for users and admins written in Java.

**Project Structure**

Project was developed according to SOLID principles.
Has an N-tier structure such as the DAO layer, the service layer, controllers layer, and the presentation layer.

Clients can have a role and do some action. For example:
No role: 
 - Registration
 - Authorization
 - View a list of available performances
 - View the list of theatre stages
 - Find session by date

User:
 - Create order
 - View order list
 - Find session by date
 - Add sessions to shopping cart
 - Make an order

Admin:
 - add performances
 - delete performances sessions
 - find user by email
 - create theatre stages

**Technologies used**

**backend:** Java 11, Spring Core/MVC/Security, Hibernate, Tomcat
**database:** MySQL

**To start the project you need:** 

 1. Download and install the JDK
 2. Download and install servlet container (for example Apache Tomcat)
 3. Download and install MySQL Server
     Setup new connection with 
     - user: "your username"
     - password: "your password"
     - url: jdbc:mysql://"your host name":"your port"/"your name db"?serverTimezone=UTC
 4. Run a project
