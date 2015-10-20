package br.marcospaulo.jpa.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.authentication.encoding.Md4PasswordEncoder;
import org.springframework.stereotype.Service;

import br.marcospaulo.jpa.entity.Blog;
import br.marcospaulo.jpa.entity.Item;
import br.marcospaulo.jpa.entity.Role;
import br.marcospaulo.jpa.entity.User;
import br.marcospaulo.jpa.repository.BlogRepository;
import br.marcospaulo.jpa.repository.ItemRepository;
import br.marcospaulo.jpa.repository.RoleRepository;
import br.marcospaulo.jpa.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	BlogRepository blogRepository;

	@Autowired
	ItemRepository itemRepository;

	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(Integer id) {
		return userRepository.findOne(id);
	}
	
	public User findOne(String username) {
		return userRepository.findByName(username);
	}

	@Transactional
	public User findOneWithBlogs(int id) {
		User user = findOne(id);
		List<Blog> blogs = blogRepository.findByUser(user);
		for(Blog blog : blogs) {
			List<Item> items = itemRepository.findByBlog(blog, new PageRequest(0, 10, Direction.DESC, "publishedDate"));
			blog.setItems(items);
		}
		user.setBlogs(blogs);
		return user;
	}

	public void save(User user) {		
		user.setEnabled(true);
		Md4PasswordEncoder encoder = new Md4PasswordEncoder();
		user.setPassword(encoder.encodePassword(user.getPassword(), ""));
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);
		
		userRepository.save(user);
	}

	public User findOneWithBlogs(String name) {
		User user = userRepository.findByName(name);
		return findOneWithBlogs(user.getId());
	}

	public void delete(int id) {
		userRepository.delete(id);
	}
	
}
