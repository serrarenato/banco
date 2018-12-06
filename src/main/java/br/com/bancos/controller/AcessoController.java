package br.com.bancos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancos.controller.AccessRequest.AccessRequest;
import br.com.bancos.repository.UsuarioMovimentacaoRepository;
import br.com.bancos.repository.UsuarioRepository;
import br.com.bancos.repository.model.UsuarioDataModel;
import br.com.bancos.repository.model.UsuarioMovimentacaoModel;

@RequestMapping
@RestController
public class AcessoController {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private UsuarioMovimentacaoRepository usuarioMovimentacaoRepository;

	@PostMapping(value = "/login")
	public ResponseEntity<?> acesso(final @Valid @RequestBody AccessRequest accessRequest) {
		System.out.println(accessRequest);
		UsuarioDataModel usuarioDataModel = repository.findByLoginAndPassword(accessRequest.getName(),
				accessRequest.getPassword());
		if (usuarioDataModel != null)
			return new ResponseEntity<UsuarioDataModel>(usuarioDataModel, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/extrato")
	public ResponseEntity<List<UsuarioDataModel>> extrato(final @RequestParam String login) {
		System.out.println(login);
		List<UsuarioMovimentacaoModel> usuarioMovimentacaoModelList = usuarioMovimentacaoRepository.findByLogin(login);
		return new ResponseEntity(usuarioMovimentacaoModelList, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/boleto")
	public ResponseEntity boleto(final @RequestParam Double value, @RequestParam String login) {
		System.out.println(login + value);
		UsuarioMovimentacaoModel usuarioMovimentacaoModel = new UsuarioMovimentacaoModel();
		usuarioMovimentacaoModel.setLogin(login);
		usuarioMovimentacaoModel.setValue(-value);
		usuarioMovimentacaoRepository.save(usuarioMovimentacaoModel);
		return new ResponseEntity(HttpStatus.OK);

	}
	@RequestMapping(value = "/saldo")
	public ResponseEntity<Double> saldo(@RequestParam String login) {
		System.out.println(login );

		Double saldo =usuarioMovimentacaoRepository.getSaldo(login);
		return new ResponseEntity(saldo, HttpStatus.OK);

	}
}