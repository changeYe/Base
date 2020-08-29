package sharding.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import sharding.po.User;

@Mapper
public interface UserRepository {
	
	Long addUser(User user);

	List<User> list();

	User findById(Long id,Integer sex);

	User findByName(String name);
}
