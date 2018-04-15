package lsr.springmvc.dao;

import lsr.springmvc.model.Role;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsr on 2017/6/28.
 */
public class RoleDAOTest {

    private RoleDAO roleDAO;

    private String roleId = "id112233";
    private String roleKey = "test";

    @Before
    public void before() throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
                ,"classpath:conf/spring-mybatis.xml"});
        roleDAO = (RoleDAO) context.getBean("roleDAO");
        insertRole();
    }

    @After
    public void after() throws Exception{
        roleDAO.deleteRoleById(roleId);
    }

    public void insertRole() throws Exception {

        Role role = new Role();
        role.setId(roleId);
        role.setDomainId("01");
        role.setRoleKey(roleKey);
        role.setRoleName("测试插入");
        role.setCreatedBy("lsr");

        roleDAO.insertRole(role);
    }

    @Test
    public void getRoleList() throws Exception {
        List<Role> roleList = roleDAO.getRoleList(new HashMap());
        Assert.assertNotNull(roleList);
        Assert.assertNotEquals(0,roleList.size());
    }

    @Test
    public void queryRoleDetailById() throws Exception {
        Role role = roleDAO.queryRoleDetailById(roleId);
        Assert.assertNotNull(role);
    }

    @Test
    public void queryRoleDetailByKey() throws Exception {
        Role role = roleDAO.queryRoleDetailByKey(roleKey);
        Assert.assertNotNull(role);
    }

    @Test
    public void updateRoleById() throws Exception {
        Map params = new HashMap();
        params.put("id",roleId);
        params.put("updatedBy","lsr");
        params.put("roleName","升级测试角色");
        int count = roleDAO.updateRoleById(params);
        Assert.assertNotEquals(0,count);
    }

    @Test
    public void deleteRoleById() throws Exception {
        int count = roleDAO.deleteRoleById(roleId);
        Assert.assertNotEquals(0,count);
    }

    @Test
    public void deleteRoleByKey() throws Exception {
        int count = roleDAO.deleteRoleByKey(roleKey);
        Assert.assertNotEquals(0,count);
    }

    @Test
    public void insertUserRole() throws Exception {
        Map params = new HashMap();
        params.put("userId","testUserId");
        params.put("roleId","testRoleId");
        roleDAO.deleteLinkUserRole(params);

        params.clear();
        params.put("id","lnkur010101");
        params.put("userId","testUserId");
        params.put("roleId","testRoleId");
        params.put("createdBy","lsr");

        roleDAO.insertUserRole(params);
    }

    @Test
    public void queryUserRole() throws Exception {

        Map params = new HashMap();

        params.clear();
        params.put("userId","testUserId");
        params.put("roleId","testRoleId");
        roleDAO.deleteLinkUserRole(params);

        Role role = new Role();
        role.setId("testRoleId");
        role.setDomainId("01");
        role.setRoleKey("testRoleKey");
        role.setRoleName("测试插入");
        role.setCreatedBy("lsr");
        roleDAO.insertRole(role);

        params.clear();
        params.put("id","lnkur010101");
        params.put("userId","testUserId");
        params.put("roleId","testRoleId");
        params.put("createdBy","lsr");

        roleDAO.insertUserRole(params);

        params.clear();
        params.put("userId","testUserId");
        List<Role> roles = roleDAO.queryUserRole(params);

        Assert.assertNotEquals(0,roles.size());

        roleDAO.deleteRoleById("testRoleId");

        params.clear();
        params.put("userId","testUserId");
        params.put("roleId","testRoleId");
        roleDAO.deleteLinkUserRole(params);
    }

    @Test
    public void deleteLinkUserRole() throws Exception {

        Map params = new HashMap();
        params.put("id","lnkur010101");
        params.put("userId","testUserId");
        params.put("roleId","testRoleId");
        params.put("createdBy","lsr");
        roleDAO.insertUserRole(params);

        params.clear();
        params.put("userId","testUserId");
        params.put("roleId","testRoleId");

        int count = roleDAO.deleteLinkUserRole(params);
        Assert.assertNotEquals(0,count);
    }

    @Test
    public void deleteLinkRole() throws Exception {

        roleDAO.deleteLinkRole("testRoleId");

        Map params = new HashMap();
        params.put("id","lnkur010101");
        params.put("userId","testUserId");
        params.put("roleId","testRoleId");
        params.put("createdBy","lsr");
        roleDAO.insertUserRole(params);

        int count = roleDAO.deleteLinkRole("testRoleId");
        Assert.assertNotEquals(0,count);
    }

    @Test
    public void deleteLinkUser() throws Exception {
        Map params = new HashMap();
        params.put("id","lnkur010101");
        params.put("userId","testUserId");
        params.put("roleId","testRoleId");
        params.put("createdBy","lsr");
        roleDAO.insertUserRole(params);

        int count = roleDAO.deleteLinkUser("testUserId");
        Assert.assertNotEquals(0,count);
    }

}