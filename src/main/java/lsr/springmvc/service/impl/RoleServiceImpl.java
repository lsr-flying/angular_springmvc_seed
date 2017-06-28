package lsr.springmvc.service.impl;

import com.alibaba.fastjson.JSONObject;
import lsr.springmvc.dao.RoleDAO;
import lsr.springmvc.enums.DataStatus;
import lsr.springmvc.model.Role;
import lsr.springmvc.service.RoleService;
import lsr.springmvc.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2017/6/28.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDAO roleDAO;

    public void insertRole(JSONObject param) {
        Role role = new Role();
        String roleId = UUIDUtil.getUUID();
        role.setId(roleId);
        role.setRoleName(param.getString("roleName"));
        role.setRoleKey(param.getString("roleKey"));
        role.setCreatedBy(param.getString("createdBy"));
        role.setDomainId(param.getString("domainId"));
        role.setDataStatus(DataStatus.ENABLE.getValue());
        roleDAO.insertRole(role);
    }

    public List<Role> getRoleList(JSONObject param) {
        return roleDAO.getRoleList(param);
    }

    public Role queryRoleDetailById(JSONObject param) {
        String roleId = param.getString("roleId");
        return roleDAO.queryRoleDetailById(roleId);
    }

    public Role queryRoleDetailByKey(JSONObject param) {
        String roleKey = param.getString("roleKey");
        return roleDAO.queryRoleDetailByKey(roleKey);
    }

    public int updateRoleById(JSONObject param) {
        return roleDAO.updateRoleById(param);
    }

    public int updateRoleByKey(JSONObject param) {
        return roleDAO.updateRoleByKey(param);
    }

    public int deleteRoleById(JSONObject param) {
        String roleId = param.getString("roleId");
        return roleDAO.deleteRoleById(roleId);
    }

    public int deleteRoleByKey(JSONObject param) {
        String roleKey = param.getString("roleKey");
        return roleDAO.deleteRoleByKey(roleKey);
    }

    public void insertUserRole(JSONObject param) {
        String id = UUIDUtil.getUUID();
        param.put("id",id);
        roleDAO.insertUserRole(param);
    }

    public int deleteLinkUserRole(JSONObject param) {
        return roleDAO.deleteLinkUserRole(param);
    }

    public int deleteLinkRole(JSONObject param) {
        String roleId = param.getString("roleId");
        return roleDAO.deleteLinkRole(roleId);
    }

    public int deleteLinkUser(JSONObject param) {
        String userId = param.getString("userId");
        return roleDAO.deleteLinkUser(userId);
    }
}
