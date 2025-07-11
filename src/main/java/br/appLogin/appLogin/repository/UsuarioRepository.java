package br.appLogin.appLogin.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.appLogin.appLogin.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	
	Usuario findById(long id);
	
	Usuario findByEmail(String email);
	
	@Query(value="select * from applogin.usuario where email = :email and password = :password", nativeQuery = true)
	public Usuario login(String email, String password);
	

}
