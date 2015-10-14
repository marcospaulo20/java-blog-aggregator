package br.marcospaulo.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.marcospaulo.jpa.entity.Blog;
import br.marcospaulo.jpa.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer>{

	List<Blog> findByUser(User User);
	
}
