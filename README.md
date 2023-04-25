# Dynatrace 2023 internship task for backend
## 1. Introduction
This API provides requested data from http://api.nbp.pl/. Before returning data to user. Application does some manipulation with fetched data.
Then provides user by the given instructions.
***
## 2. Installation
In order to install this app please click "Code" button and copy link. Then open any IDE which supports Java. Choose clone and put the given link.
project folder will be initialzed. Then open **"com.dynatrace\com.dynatrace\src\main\java\com\dynatrace"** folder and run Application.java file.
Before running it make sure that JDK installed on given machine. 
After successful running you will see this nformatin on the terminal, see below:
![Снимок экрана (3)](https://user-images.githubusercontent.com/57500808/234324060-e5001060-05c8-4dda-bfa8-b5787191503a.png)
***
## 3. Usage
Above java file will run local server on Tomcat, and by default it runs on port:8080. Base url of this api is "/api/exchange". With port number it  is **"http://localhost:8080/api/exchange"**. 
***
## 4. Endpoints
Method | Type | Base URL | Endpoint | Example | Response
------------ | -------------| -------------| -------------|  -------------| 
Get average rate of given currency by date | GET|  http://localhost:8080/api/exchange | /a/{code}/{date} | http://localhost:8080/api/exchange/a/czk/2023-04-21 | 0.1955
Get maximum and minimum average rate of  given currency by quotation | GET|  http://localhost:8080/api/exchange | /a/{code}/last/{number} | http://localhost:8080/api/exchange/a/gbp/last/3 | Maximum rate average date: 2023-04-24, value is: 5.2176
Minimum rate average date: 2023-04-25, value is: 5.1958
for last 3 quotations on GBP



