# Library
A simple application to operate the library  
Project uses the **JSF Primefaces** and **postgresql** database

## pom.xml
```java
	<dependencies>

		<!-- Java Server Faces -->
		<!-- https://mvnrepository.com/artifact/com.sun.faces/jsf-api -->
		<dependency>
			<groupId>com.sun.faces</groupId>
			<artifactId>jsf-api</artifactId>
			<version>2.2.19</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/com.sun.faces/jsf-impl -->
		<dependency>
			<groupId>com.sun.faces</groupId>
			<artifactId>jsf-impl</artifactId>
			<version>2.2.19</version>
		</dependency>

		<!-- Primefaces -->
		<dependency>
			<groupId>org.primefaces</groupId>
			<artifactId>primefaces</artifactId>
			<version>7.0</version>
		</dependency>

		<!-- Postgresql driver -->
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<version>42.2.6</version>
		</dependency>
    
		<!-- annotation - PostConstruct -->
		<dependency>
  			<groupId>javax.annotation</groupId>
  			<artifactId>javax.annotation-api</artifactId>
  			<version>1.3.2</version>
		</dependency>
    
		<!-- Java Servlet - HttpServletRequest -->
		<dependency>
    		<groupId>javax.servlet</groupId>
    		<artifactId>javax.servlet-api</artifactId>
    		<version>4.0.1</version>
    		<scope>provided</scope>
		</dependency>
	</dependencies>
  ```
  ## requirements
  
  - [postgres](https://www.postgresql.org/download/) database  
  - [tomcat](http://tomcat.apache.org/) server  
  - postgresql database tables created
  
  create tadabase and tables for library  
  [file](https://github.com/damianzxc/Library/blob/master/sql.txt)

```sql
DATABASE: library
        
TABLES:  
users  
	id VARCHAR(8) primary key  
	name VARCHAR(25)  
	surname VARCHAR(25)  
	pesel VARCHAR(25)  
	active BOOL  

books  
	id VARCHAR(8) primary key  
	name VARCHAR(25)  
	describe VARCHAR(255)  
	author VARCHAR(25)  
	publisher VARCHAR(25)  
	release_date DATE  
	status VARCHAR(25)  
	active BOOL  

loans  
	id VARCHAR(8)  
	book_id VARCHAR(8) foreign key (books.id)  
	user_id VARCHAR(8) foreign key (users.id)  
	rental_date DATE  
	expected_return_date DATE  
	real_return_date DATE  
	active BOOL  
```
## run application
1. put the [war](https://github.com/damianzxc/Library/blob/master/Library.war) file in your tomcat server's webapp directory  
	-> run or restart tomcat server  
	-> go [localhost:8080/Library](http://localhost:8080/Library) to see application
2. download project and import it in IDE (eclipse or other)  
	-> add server tomcat  
	-> run application  

<img src="https://github.com/damianzxc/Library/blob/master/library_example.jpg" alt="library example"/>
