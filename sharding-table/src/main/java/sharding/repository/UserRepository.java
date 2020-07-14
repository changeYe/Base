package sharding.repository;

import org.apache.ibatis.annotations.Mapper;
import sharding.po.User;

import java.util.List;


@Mapper
public interface UserRepository {
	
	Long addUser(User user);
	
	List<User> list();
	
	User findById(Long id);
	
	User findByName(String name);
}
