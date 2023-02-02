# Recipe-App


### Recipe app built using Spring Boot & Thymeleaf

#### Prerequisites
- Java11
- Maven3.6
- IDE (I'm use Visual Studio Code)
- No Database installation required as I'm using H2, an in-memory-db

That's all you need to get started tinkering with this application.

#### Let's start

- clone the repo
- open it in VSCode
- and run the app using Spring Boot Dashboard plugin 

**OR**

**You can also try using the terminal / cmd**
- clone the repo
- open terminal in the root of recipe-app repo
- in your terminal, type the following command 
<br>

> ./mvnw clean install

_it will download all the required dependencies and build the executable jar file of this application._

_after build is complete, check the version of the jar file, by following the below steps_

**On windows**

> dir target

**On Linux**

>ls target

_run this command after providing the proper version to run the application_

> java -jar target/recipe-app-0.0.1-SNAPSHOT.jar
