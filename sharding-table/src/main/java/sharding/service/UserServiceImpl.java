package sharding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sharding.po.User;
import sharding.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> list() {
		return userRepository.list();
	}

	@Override
	public Long add(User user) {
		return userRepository.addUser(user);
	}

	@Override
	public User findById(Long id,Integer sex) {
		return userRepository.findById(id,sex);
	}

	@Override
	public User findByName(String name) {
		return userRepository.findByName(name);
	}

}
