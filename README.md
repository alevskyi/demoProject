## Preface: 
This is an updated version of my first Java project. Changes include: dependencies version updates, UI changes, and minor internal changes.
Original functionality preserved as it was. Full list of changes bellow.
 - Spring Boot version update: `1.5.2` &rarr; `3.2.0`  
 - Build tool change: `Gradle` &rarr; `Maven`
 - Generated sources from an XSD schema: `Part of the codebase` &rarr; `Generated during build, using JAXB plugin`
 - Database change: `H2 database file, part of the codebase` &rarr; `MySQL in Docker`
 - Frontend update: `jQuery` &rarr; `React`
 - Backend view layer: `Thymeleaf templates`  &rarr; `REST API`

## Summary:
This is a website written in Java. It allows users to browse quotes, and post new.
User can post a new quote after register and login process via, filling a form, or uploading an XML file.
Individual quotes contain a unique link, and can be bookmarked.

## Screenshots:
![register](./screenshots/register.png)
![main](./screenshots/main.png)
![profile](./screenshots/profile.png)
![quotes](./screenshots/quotes.png)

## How to run:
To start frontend, run shell commands: 
```shell
cd frontend
npm run start
```
To start backend, run main class `ua.training.quotes.Application`

The application can store data in two different ways: in memory, or in a database. The default option is "in memory".
To use a database: comment out profile setting in `application.properties` and run `docker-compose up` to start a database.
Sample quotes stored in a file `data.json`. Sample user credentials: login - `testUser`, password - `1234`.


