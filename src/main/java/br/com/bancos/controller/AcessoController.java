package br.com.bancos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

/**
 * 
 * Classe principal com todas as APis disponiveis na aplicação
 *
 */

@CrossOrigin
@RequestMapping("banco")
@RestController
public class AcessoController {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private UsuarioMovimentacaoRepository usuarioMovimentacaoRepository;

	@PostMapping(value = "/login")
	public ResponseEntity<?> acesso(final @Valid @RequestBody AccessRequest accessRequest) {
		System.out.println(accessRequest);
		UsuarioDataModel usuarioDataModel = repository.findByContaAndAgenciaAndPassword(
				accessRequest.getConta().replaceAll("\\D+", ""), accessRequest.getAgencia().replaceAll("\\D+", ""),
				accessRequest.getPassword().replaceAll("\\D+", ""));

		if (usuarioDataModel != null)
			return new ResponseEntity<UsuarioDataModel>(usuarioDataModel, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/extrato")
	public ResponseEntity<List<UsuarioDataModel>> extrato(final @RequestParam String conta) {
		System.out.println(conta);
		List<UsuarioMovimentacaoModel> usuarioMovimentacaoModelList = usuarioMovimentacaoRepository
				.findByConta(conta.replaceAll("\\D+", ""));
		return new ResponseEntity(usuarioMovimentacaoModelList, HttpStatus.OK);

	}

	@RequestMapping(value = "/boleto")
	public ResponseEntity boleto(final @RequestParam String value, @RequestParam String conta) {
		System.out.println(conta + value);
		UsuarioMovimentacaoModel usuarioMovimentacaoModel = new UsuarioMovimentacaoModel();
		usuarioMovimentacaoModel.setConta(conta.replaceAll("\\D+", ""));
		Double value2= new Double(value.replaceAll("\\D+", ""));
		usuarioMovimentacaoModel.setValue(-value2);
		usuarioMovimentacaoRepository.save(usuarioMovimentacaoModel);
		return new ResponseEntity(HttpStatus.OK);

	}

	@RequestMapping(value = "/saldo")
	public ResponseEntity<Double> saldo(@RequestParam String conta) {
		System.out.println(conta);
		Double saldo = usuarioMovimentacaoRepository.getSaldo(conta.replaceAll("\\D+", ""));
		saldo = saldo == null ? 0 : saldo;
		return new ResponseEntity(saldo, HttpStatus.OK);

	}
}