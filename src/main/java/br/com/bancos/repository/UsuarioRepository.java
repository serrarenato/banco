package br.com.bancos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bancos.repository.model.UsuarioDataModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioDataModel, Long> {

    UsuarioDataModel findByLoginAndPassword(final String login, final String password);
}
