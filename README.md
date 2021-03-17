# Puddy
A Spring Boot application providing easy money transfer between users

This application use different technologies to achieve his purpose like:
- Spring Security
- Thymeleaf
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

### Installing database structure

Post installation of MySQL, Java and Maven, you will have to set up the tables and data in the data base.

For this, please run the sql commands present in [data.sql](/src/main/resources/data.sql)

Tests use another database, be sure to run sql commands in [data-test.sql](/src/test/resources/data.sql)

### Installing App

1.Clone this project using git:
`git clone <url>`

2.Install the app using maven:
`mvn install`

### Mysql connection
The database connection is handled by application.properties, you need to copy this file from test environment (/src/test...) to your production environment (/src/main...)
and change login and password according to your mysql user


### Running Tests

Run tests:
`mvn test`

### Running App

Using java in command line:

`java -jar <target/last_snapshot.jar>`

### Access Datas
The web server listen on port 8080.

### Test Account
You can register in app or use tests account

Since credit card versement is not implemented yet, if you want to realize a money transfer, you need to use one of these test users :

Login: matt.lau@gmail.com

Password: test


Login: yann.lau@gmail.com

Password: test


Login: herve.loiseau@gmail.com

Password: test


Login: jean.joubler@gmail.com

Password: test


### Resources
[Class diagram of the domain](/src/main/resources/P6_01_ClassDiagram.PNG)

[Database structure](/src/main/resources/P6_02_SQL-Table_Struct.PNG)
