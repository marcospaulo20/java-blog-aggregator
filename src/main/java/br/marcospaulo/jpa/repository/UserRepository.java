package br.marcospaulo.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.marcospaulo.jpa.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
