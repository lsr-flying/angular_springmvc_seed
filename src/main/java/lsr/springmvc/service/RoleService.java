package lsr.springmvc.service;

import com.alibaba.fastjson.JSONObject;
import lsr.springmvc.model.Menu;
import lsr.springmvc.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

	void insertRole(JSONObject param);

	List<Role> getRoleList(JSONObject param);

	Role queryRoleDetailById(JSONObject param);

	Role queryRoleDetailByKey(JSONObject param);

	int updateRoleById(JSONObject param);

	int updateRoleByKey(JSONObject param);

	int deleteRoleById(JSONObject param);

	int deleteRoleByKey(JSONObject param);

	void insertUserRole(JSONObject param);

	void saveOrUpdateRole(JSONObject param);

	List<Role>  queryUserRole(JSONObject param);

	int deleteLinkUserRole(JSONObject param);

	int deleteLinkRole(JSONObject param);

	int deleteLinkUser(JSONObject param);

}
