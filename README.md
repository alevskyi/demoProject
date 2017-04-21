This is website written in Java, using Spring4, SpringBoot and SpringSecurity.
Its purpose is to provide access to priviously posted quotations to users, and allow them to post new.
User can post new quote after registration and signing in by filling form or uploading XML file.
Also there is simple REST API for form validation and quote queries.

Repository includes database and XML file preloded with same content - quotations and two user accounts. 
To use database and Hibernate set active profiles in application.properties to "ormQuotes,ormUsers".
To use database and Spring JDBC templates set profiles to "jdbcQuotes,jdbcUsers", and "xmlQuotes,xmlUsers" to 
load quotations and user accounts from XML files in /src/main/resources: quotes.xml and users.xml correspondingly.  

CSS use two fonts - Cabazon and Lobster, which avalable for free on typekit.com.

To run application execute a shell command in project root "gradlew bootRun", this will start a server on localhost:8000.

 
