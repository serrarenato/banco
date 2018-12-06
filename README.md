# banco


POST:
localhost:8080/banco/login


{
	"conta":"9999",
	"agencia":"4455",
	"password":"1234"
}

GET:localhost:8080/banco/extrato?conta=9999

GET:localhost:8080/banco/boleto?conta=9999&value=10

GET:localhost:8080/banco/saldo?conta=9999

docker-compose up

INSERT INTO public.usuario
(id, login, nome, "password", agencia, conta)
VALUES(1, 'renato', 'Renato', '1234', '4455', '9999');


