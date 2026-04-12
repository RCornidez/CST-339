# CST-339 CLC

# How to run the application

## 1. Create the database
> [!IMPORTANT]
```bash
docker compose up -d
```
> [!NOTE] 
> INFO: Validate by connecting to the instance with dbeaver with the following credentials
> Host: localhost (or 127.0.0.1)                                              
> Port: 3306
> User: root                                                                  
> Password: root
> Database: inventory_db

> [!IMPORTANT]
> When adding a mysql connection in dbeaver, select the `Driver Properties` tab on the top left.
> set `allowPublicKeyRetrieval` to `true`. Then validate the connection using the `Test Connection` button on the bottom left.

## 2. Start the Spring Boot application in dev
> [!IMPORTANT]
> JAVA 17 SDK must be installed
> Maven CLI can be installed or you can use the built in `mvnw` commands provided by Spring Boot.

```bash
cd milestone                                                                  
./mvnw spring-boot:run      
```


## 3. Building for production and running the .jar file
```
./mvnw package                                                                
java -jar target/*.jar
```