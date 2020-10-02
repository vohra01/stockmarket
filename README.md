# stockMarket

#Notes

* Written in Java
* No database or UI is used, all data is held in memory

#How to use:
This is a maven project, use goals:
* mvn test -> to execute the unit tests.
* mvn package -> to generate the executable jar.

To run the program just run:
* java -jar target/sssmarket-0.0.1-SNAPSHOT.jar
* OR 
* Simply Go to main class and run main method

#Classes
* App -> Just a sample app using the main classes Stock
* AllStocks -> Class used to calculate the All Share Index
* Stock -> Class to manage operations against every stock
* Trade -> Just a POJO representing each trade

#Packages
* com.kunal.assignments.stockmarket 

#Code Coverage

![Alt text](src/main/resources/outputImages/Screen Shot 2020-10-02 at 5.10.27 PM?raw=true "Test Coverage")

#Sample Output

![Alt text](src/main/resources/outputImages/Screen Shot 2020-10-02 at 5.12.49 PM?raw=true "ABC STock Operations")



