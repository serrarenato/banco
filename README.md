# banco


POST:
localhost:8080/banco/login

{
	"name":"renato",
	"password":"1234"
}

GET:localhost:8080/banco/extrato?login=renato

GET:localhost:8080/banco/boleto?login=renato&value=10

GET:localhost:8080/banco/saldo?login=renato

