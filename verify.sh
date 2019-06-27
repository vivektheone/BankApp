curl -d '{"accountNumber": "123", "customerName": "Me"}' -X POST http://localhost:4567/accounts

curl -d '{"accountNumber": "124", "customerName": "Him"}' -X POST http://localhost:4567/accounts

curl -d '{"accountNumber": "123", "amount": 100}' -X PUT http://localhost:4567/accounts

curl -d '{"accountNumber": "124", "amount": 100}' -X PUT http://localhost:4567/accounts

curl -X GET http://localhost:4567/accounts

curl -d '{"accountNumber": "124", "toAccountNumber":"123","amount": 100}' -X PUT http://localhost:4567/transfer


echo 'complete'