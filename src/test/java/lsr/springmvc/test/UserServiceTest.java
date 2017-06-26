package lsr.springmvc.test;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lsr.springmvc.model.User;
import lsr.springmvc.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceTest {

	private UserService userService;
	
	@Before
	public void before(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
				,"classpath:conf/spring-mybatis.xml"});
		userService = (UserService) context.getBean("userServiceImpl");
	}
	
	@Test
	public void addUser(){
		User user = new User();
		user.setNickname("Super");
		user.setState(3);
		Assert.assertNotEquals(0,userService.insertUser(user));
	}

	@Test
	public void getUserList(){
		JSONObject userListJSON = userService.getUserList(new HashMap());
		Assert.assertNotNull(userListJSON);
		Assert.assertNotEquals(0,
				userListJSON.getJSONArray("list").size());
	}

	@Test
	public void updateUserById(){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id","1");
		param.put("nickname","Peter_updated");
		Assert.assertNotEquals(0,userService.updateUserById(param));
	}

	@Test
	public void deleteUserById(){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id","5");
		Assert.assertNotEquals(0,userService.deleteUserById(param));
	}
}
