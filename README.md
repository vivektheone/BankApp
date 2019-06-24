This repository contains the BankApp.

Pre-requisites:
1. Gradle
2. Java

Build:
gradle clean build 

Run: 
java -jar build/libs/bank-1.0.jar


Shows accounts in console:
curl -X GET http://localhost:4567/accounts

Create aggregate:
curl -d '{"accountNumber": "123", "customerName": "Me"}' -X POST http://localhost:4567/accounts


Update aggregate (Deposit in account):
curl -d '{"accountNumber": "123", "amount": 100}' -X PUT http://localhost:4567/accounts


Update aggregate (Withdraw from account):
curl -d '{"accountNumber": "123", "amount": -50}' -X PUT http://localhost:4567/accounts
