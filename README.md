### Summary:
This is a website written in Java. Its purpose is to provide access to priviously posted
quotations to users, and allow them to post new. User can post new quote after registration
and signing in either, by filling a form or uploading an XML file.
Also there is simple REST API for form validation and quote queries.

### Persistence options:
Application can store data in three different ways, using either: Spring JDBC templates, Hibernate or JAXB.
Repository includes database file ```database.mv.db``` and XML files preloaded with same content - quotations and two user accounts. 
To choose specific option set profiles in /src/main/resources/application.properties as follows:
* ```ormQuotes,ormUsers```  -  for Hibernate
* ```jdbcQuotes,jdbcUsers```  -  for Spring JDBC templates
* ```xmlQuotes,xmlUsers```  -  to load data from XML files in ```/src/main/resources```

### Screenshots:
![main](https://cloud.githubusercontent.com/assets/27825950/25377720/fb4f7324-29b0-11e7-957b-cb612596fcbb.jpg)
![quotes](https://cloud.githubusercontent.com/assets/27825950/25377746/0bd31eb2-29b1-11e7-8870-7b33dde47c28.jpg)
![profile](https://cloud.githubusercontent.com/assets/27825950/25377765/1a003a2e-29b1-11e7-88de-bc0dcdd3e946.jpg)
![registration](https://cloud.githubusercontent.com/assets/27825950/25377781/277418f6-29b1-11e7-952c-2752e05543da.jpg)

### How to run:
Execute a shell command in project root ```gradlew bootRun```, this will start server on ```localhost:8000```.	

### Used technologies:
* Spring 4
* Spring Boot
* Spring Security
* Spring JDBC templates, JAXB, Hibernate
* Thymeleaf
* JUnit, Mockito, AssertJ
