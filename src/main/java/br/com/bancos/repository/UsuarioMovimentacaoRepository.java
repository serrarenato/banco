package br.com.bancos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.bancos.repository.model.UsuarioMovimentacaoModel;
/**
 * 
 * Acessos a tabela Usuario Movimentação
 *
 */
@Repository
public interface UsuarioMovimentacaoRepository extends JpaRepository<UsuarioMovimentacaoModel, Long> {

	List<UsuarioMovimentacaoModel> findByConta(final String login);
	
	@Query("SELECT sum(u.value) FROM UsuarioMovimentacaoModel u WHERE u.conta = :conta")
	Double getSaldo(@Param("conta") String conta);
}
