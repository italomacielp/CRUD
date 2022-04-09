package br.com.springboot.curso_springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.curso_springboot.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

}
