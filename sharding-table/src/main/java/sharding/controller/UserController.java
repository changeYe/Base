package sharding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sharding.po.User;
import sharding.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public Object list() {
		return userService.list();
	}

	@GetMapping("/add")
	public Object add() {
		for (long i = 50; i < 100; i++) {
			User user = new User();
			user.setId(i);
			user.setSex((int) (i % 2));
			user.setUserId(i);
			user.setUserName("李四");
			userService.add(user);
		}
		return "success";
	}

	@GetMapping("/users/{id}")
	public Object get(@PathVariable Long id, @RequestParam("sex")Integer sex) {
		return userService.findById(id,sex);
	}

	@GetMapping("/users/query")
	public Object get(String name) {
		return userService.findByName(name);
	}

}
