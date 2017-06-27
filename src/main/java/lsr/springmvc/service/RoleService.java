package lsr.springmvc.service;

import lsr.springmvc.model.Menu;
import lsr.springmvc.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

	String insertRole(Role role);

	List<Role> getRoleList(Map params);

	Role queryRoleDetailById(String id);

	Role queryRoleDetailByKey(String key);

	int updateRoleById(Map params);

	int updateRoleByKey(Map params);

	int deleteRoleById(String id);

	int deleteRoleByKey(String key);

	void insertUserRole(Map params);

	int deleteLinkUserRole(Map params);

	int deleteLinkRole(String roleId);

	int deleteLinkUser(String userId);

}
