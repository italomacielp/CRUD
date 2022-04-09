package br.com.springboot.curso_springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.curso_springboot.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	/*Será feita uma consulta com hibernate, o like remete a procura por partes
	 * e o ?1, indica que só é um parâmetro no método de buscar por nome.*/
	@Query(value = "select u from Users u where upper(trim(u.name)) like %?1%")
	List<Users> findByName(String name);
	
}
