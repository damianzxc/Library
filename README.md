# Library
A simple application to operate the library  
Project uses the **JSF Primefaces** and **postgresql** database

## pom.xml

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
  
  ## requirements
  
  - [postgres](https://www.postgresql.org/download/) database  
  - [tomcat](http://tomcat.apache.org/) server  
  - postgresql database tables created
  
  create tadabase and tables for library  
  [file](https://github.com/damianzxc/Library/blob/master/sql.txt)

DATABASE library
        
TABLE users  
	id VARCHAR primary key  
	name VARCHAR  
	surname VARCHAR  
	pesel VARCHAR  
	active BOOL  

TABLE books  
	id VARCHAR primary key  
	name VARCHAR  
	describe VARCHAR  
	author VARCHAR  
	publisher VARCHAR  
	release_date DATE  
	status VARCHAR  
	active BOOL  

TABLE loans  
	id VARCHAR  
	book_id VARCHAR foreign key (books.id)  
	user_id VARCHAR foreign key (users.id)  
	rental_date DATE  
	expected_return_date DATE  
	real_return_date DATE  
	active BOOL  
