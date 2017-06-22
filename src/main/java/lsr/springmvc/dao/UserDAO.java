package lsr.springmvc.dao;

import lsr.springmvc.model.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {

	/**
	 * 添加新用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
	
	public List<User> getUserList(Map params);
}
