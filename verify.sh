
//Run only once
curl -d '{"accountNumber": "123", "customerName": "Me"}' -X POST http://localhost:4567/accounts

curl -d '{"accountNumber": "124", "customerName": "Him"}' -X POST http://localhost:4567/accounts

curl -d '{"accountNumber": "123", "amount": 100}' -X PUT http://localhost:4567/accounts

curl -d '{"accountNumber": "124", "amount": 100}' -X PUT http://localhost:4567/accounts

curl -X GET http://localhost:4567/accounts

curl -d '{"accountNumber": "124", "toAccountNumber":"123","amount": 100}' -X PUT http://localhost:4567/transfer


#for ((i=1;i<=10;i++));
#do
#   echo $i
#   curl -d '{"accountNumber": "123", "toAccountNumber":"124","amount": 100}' -X PUT http://localhost:4567/transfer &
#done

echo 'complete'