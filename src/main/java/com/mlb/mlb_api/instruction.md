https://docs.google.com/document/d/1xNqRoO4Yi-bCrjz2ksrHrRXWEi9VeWvrBmzjxhR35qM/edit

Building the mlb_db with java

Starting the project

Spring Boot Walkthrough (Make sure to add Spring Data JPA and MySQL Driver as dependencies as well.)
For this project the “group” should be: com.mlb
The name and artifact should be: mlb_api

Add the following to your application.properties file
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
spring.jpa.hibernate.ddl-auto=create
spring.jpa.database=mysql
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/mlb_db
spring.datasource.username=root
spring.datasource.password=ThePasswordYouCreated
server.error.include-message=always

Make sure to change the username and password to match what you set for your MySql server.

Creating the first table and get route for the “player”
If you try to run your application right away you will get an error. This is because there is no mlb_db that exists. Open MySql Workbench and create a DB called “mlb_db”. Now when we run our application it will recognize the mlb_db and won’t crash.
Create packages inside of com.mlb.mlb_api (or whatever you called it) called “entities”, “controllers”, “repositories”.
Entities can be inside the controllers too.

Start with Entity to be able to communicate with a table in the database
Create a class inside of the entities package called “Player”
The class should have the following private properties
Integer id (that is annotated as an @Id and @GeneratedValue(strategy = GenerationType.IDENTITY) and @Column(name = id, nullable = false, updatable = false) )
String name
Integer age
Double yearsOfExperience (use the @Column annotation and set the name = “years_of_experience” so that it keeps to the naming convention for mysql)
Double rating
You will need an empty constructor for the class and also a constructor that takes in and sets all of the values to the properties except the id. (2 constructors)
Create getters and setters for each property (remember you can use the “Generate” feature from intelliJ. (You might not always use the getters and setters but the Spring Container might and it could cause an error if they are missing.

Create an interface inside of the repositories package called “PlayerRepository”
This interface should extend the CrudRepository and the CrudRepository should also have 2 parameters in the angle brackets attached to it <Player, Integer> (the first is for the class that it handles that relates to the table in the DB and the second is the data type of the ID of that class.

Create a class inside of the controllers package called “PlayerController”
You will need to add the @RestController and @RequestMapping annotations above the class name. @RequestMapping should be set to map to “/player”
You will need to create an instance of the PlayerRepository and use the @AutoWired annotation for dependency injection.
Create a constructor as well that sets the playerRepository to the incoming playRepository.
Creating the POST, GET, PUT, and DELETE routes in the PlayerController
POST:
Create a method that listens for a POST request at “/add”
This method should be public and should return a Player value.
Use the @RequestBody as a parameter to set the incoming data to a player object.
Inside of the method we need to use the playerRepository to save the player from the incoming request body to the database.
Before firing up your server make sure your “spring.jpa.hibernate.ddl-auto=” is set to ”create” so that it can rebuild your tables. Then switch it to “update” once the server is up and running, so that it doesn’t drop your data each time you start the application.
Use Postman to test this route. Here is an example of a post body :
{
"name": "tommy",
"age": 44,
"yearsOfExperience": 12.0,
"rating": 56.0
}

Make sure to select that the request is “raw” format and select “JSON” from the dropdown menu.
GET:
Create a method that listens for a GET request at the base url for the controller
This method should be public and should return all of the players in the database.
Create a method that listens for a GET request at “/{id}”
This method should be public and should return the player requested by the “id” given as a path variable.


PUT:
Create a method that listens for a PUT request at “/update”
This method should be public and should return the updated player.
You will need to find the player by the id that is coming in as part of the request body.
Once you have found the player by their id you will need to then update their properties with the incoming information.
Make sure to check if the incoming values exist for each property so you don’t accidentally delete what was already on the player.
To ensure that you are updating the players information you will need to save the player to the database.
DELETE:
Create a method that listens for a DELETE request at “/{id}” and removes the player by their id.
This method should be public and void.
It should check to make sure the player exists
It should throw a new ResponseStatusException with an HttpStatus.OK and a response that lets the client know the player has been deleted
If the player is not found, it should throw a new ResponseStatusException with an HttpStatus.NOT_FOUND and a response letting the client know the player was not found.

CREATING SEED DATA
SEED DATA USING GOOGLE SHEETS


BONUS
Create a custom query in your PlayerRepository using some the syntax found here https://www.websparrow.org/spring/spring-data-derived-findby-query-methods-example
Then create a method in your PlayerController that uses the custom query.
For example you could make a custom query by creating a method in the PlayerRepository called “findByName” that returns a Player and takes in a String of name as a parameter. 
