package lsr.springmvc.service;

import com.alibaba.fastjson.JSONObject;
import lsr.springmvc.model.User;

import java.util.Map;

public interface UserService {

	/**
	 * 添加新用户
	 * @param user
	 * @return
	 */
	int insertUser(User user);

	/**
	 * 根据用户属性
	 * 查询用户列表
	 * @param params
	 * @return
	 */
	JSONObject getUserList(Map params);

	/**
	 * 根据用户Id
	 * 查询用户详情
	 * @param params
	 * @return
	 */
	User queryUserDetailById(Map params);

	/**
	 * 根据用户Id
	 * 更新用户信息
	 * @param params
	 * @return
	 */
	int updateUserById(Map params);

	/**
	 * 根据用户Id
	 * 删除用户，物理删除
	 * @param params
	 * @return
	 */
	int deleteUserById(Map params);
}
