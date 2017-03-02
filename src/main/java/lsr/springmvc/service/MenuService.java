package lsr.springmvc.service;

import lsr.springmvc.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

	 int insertUser(User user);

	 List<User> getUserList(Map params);
}
