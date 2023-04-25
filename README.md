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
Below on the schema you can see all methods and endpoints with exampels. 
-{code} is code of currency in international format;
-{number} is number of quataion das. For example ../GBP/last/3 means getting GBP currency rates fro last 3 working days
![Снимок экрана (4)](https://user-images.githubusercontent.com/57500808/234335055-98a45912-3457-4b32-81f0-3c3faba778c1.png)
***
## 4. Error handling
Applcation checks correctness of path, currency code, date, table and number of quotations. Below on the table you can see incorrect input and error messages.
It checks date provided, its format, also check if it is weekend day or future day.
On the next two methods it checks quotation number, it expects 0<N<256 integer number.
![Снимок экрана (5)](https://user-images.githubusercontent.com/57500808/234339932-686e04e8-aa6d-4800-8678-32d016ff7ffa.png)


