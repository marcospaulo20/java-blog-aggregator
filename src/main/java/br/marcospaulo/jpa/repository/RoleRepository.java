package br.marcospaulo.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.marcospaulo.jpa.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String string);

}
