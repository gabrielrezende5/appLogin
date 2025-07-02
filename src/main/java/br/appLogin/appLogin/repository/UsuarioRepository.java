package br.appLogin.appLogin.repository;

import org.springframework.data.repository.CrudRepository;

import br.appLogin.appLogin.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	
	Usuario findById(long id);
	

}
