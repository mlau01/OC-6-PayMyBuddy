# Puddy
A Spring Boot application providing easy money transfer between users

This application use different technologies to achives his purpose like:
- Spring Security
- Thymleaf
- Hibernate
- Spring Data JPA

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them

- Java 1.8
- Maven 3.6.2
- Mysql 8.0.17

### Installing environment

1.Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:

https://maven.apache.org/install.html

3.Install MySql:

https://dev.mysql.com/downloads/mysql/

After downloading the mysql 8 installer and installing it, you will be asked to configure the password for the default `root` account.
This code uses the default root account to connect and the password can be set as `rootroot`. If you add another user/credentials make sure to change the same in your application properties


### Installing App

1.Clone this project using git:
`git clone <url>`

2.Install the app using maven:
`mvn install`

### Installing database structure

Post installation of MySQL, Java and Maven, you will have to set up the tables and data in the data base.
For this, please run the sql commands present in the `Data.sql` file under the `resources` folder in the code base.
Tests use another database, be sure to run sql commands in `Data.sql` file under the `test/resources` folder.

### Running Tests

Run tests:
`mvn test`

### Running App

using java in command line:
`mvn` install
`java -jar <target/last_snapshot.jar>`

### Access Datas
The web server listen on port 8080.

### Class diagram of the domain
[class_diagram](/src/main/resources/P6_01_ClassDiagram.PNG)
### Database structure
