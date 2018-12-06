package br.com.bancos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bancos.repository.model.UsuarioDataModel;

/**
 * 
 *  Acesso a tabela usuario
 */
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioDataModel, Long> {

    UsuarioDataModel findByContaAndAgenciaAndPassword(final String agencia,final String conta ,final String password);
}
