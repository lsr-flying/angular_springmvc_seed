package lsr.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lsr.springmvc.dao.UserDAO;
import lsr.springmvc.model.User;
import lsr.springmvc.service.UserService;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.insertUser(user);
	}

	public List<User> getUserList(Map params) {
		return userDAO.getUserList(params);
	}

}
