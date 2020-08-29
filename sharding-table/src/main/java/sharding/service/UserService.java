package sharding.service;


import java.util.List;

import sharding.po.User;

public interface UserService {

	List<User> list();
	
	Long add(User user);
	
	User findById(Long id,Integer sex);
	
	User findByName(String name);
	
}
