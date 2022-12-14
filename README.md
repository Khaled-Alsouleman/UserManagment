UserManagment is a backend service (without user interface)  that provides a RESTful WebService interface for self-registration and maintenance of user accounts. In addition, you can use all  HTTP methods via this interface to manage and update your account data yourself.

Data storage:
Two data storage mechanisms were used in this project. 
The first is a simple Java object (singleton). The implementation can be found in the InMemoryStorage class. 
Path: UserManagment/src/main/java/control/InMemoryStorage.java 
The second mechanism is the Jakarta Persistence API (JPA) for serializing Java objects into or reading data from the database using PostgreSQL. The implementation can be found in the PostgresDatabase class. 
Path: UserManagement/src/main/java/control/PostgresDatabase.java

It is important to know that all user account attributes are mandatory when creating a new account or updating an existing one. Otherwise, you'll get a 400 status code, which indicates a bad request.

With the Get request you get all account information except the password. In order for the password to be displayed, you must specify “showPassword=true” as the query parameter for get single user and “showPasswords=true” for get all users.
