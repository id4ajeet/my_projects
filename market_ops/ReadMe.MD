Prerequisite
1. Java 8
2. Maven 

Steps to run program : 

1. go to directory market-ops
2. run the myprogram like 
    ```shell script
     $ ./myprogram http://someurl/marketdata.json
    ```
   Note : it will generate output file : marketdata.json
3. run the myprogram2 like
    ```shell script
     $ ./myprogram2 marketdata.json loandata.json
    ```
   Note : it will generate output file : nonsecured.json
   
If maven Build is not done, Run the commands before running the program
```shell script
$ cd market-ops
$ mvn clean install
``` 


