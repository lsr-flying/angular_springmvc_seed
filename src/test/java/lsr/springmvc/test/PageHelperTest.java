package lsr.springmvc.test;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lsr.springmvc.dao.UserDAO;
import lsr.springmvc.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/6/25.
 */
public class PageHelperTest {

    private UserDAO userDao;

    @Before
    public void before(){
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
                ,"classpath:conf/spring-mybatis.xml"});
        userDao = (UserDAO) context.getBean("userDao");    }

    @Test
    public void testQueryListByPage(){
        int num = 1;

        List<User> userList = userDao.getUserList(new HashMap());
        Assert.assertNotNull(userList);
        Assert.assertNotEquals(num,userList.size());

        PageHelper.startPage(1, num);
        userList = userDao.getUserList(new HashMap());
        Assert.assertNotNull(userList);
        Assert.assertEquals(num,userList.size());

        PageInfo<User> pageInfo = new PageInfo(userList);
        System.out.println(JSONObject.toJSONString(pageInfo));
    }
}
