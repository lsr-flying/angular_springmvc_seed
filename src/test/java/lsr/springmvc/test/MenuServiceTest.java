package lsr.springmvc.test;

import lsr.springmvc.model.Menu;
import lsr.springmvc.model.User;
import lsr.springmvc.service.MenuService;
import lsr.springmvc.service.UserService;
import org.apache.ibatis.ognl.IntHashMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MenuServiceTest {

	private MenuService menuService;
	
	@Before
	public void before(){                                                                    
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
				,"classpath:conf/spring-mybatis.xml"});
		menuService = (MenuService) context.getBean("menuServiceImpl");
	}

	private String testId = UUID.randomUUID().toString();

	@Test
	public void testInsertMenu(){
		Menu menu = new Menu();
		menu.setId(testId);
		menu.setName("testMenu");
		menu.setIcon("/smile");
		menu.setLink("lsr.com");
		menu.setCreatedBy("lsr");
		menu.setType("nav");
		int count = menuService.insertMenu(menu);
		Assert.assertEquals(1,count);
	}

	@Test
	public void testGetMenuRecursivelyByProperty(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id","1");
		Menu menu = menuService.getMenuRecursivelyByProperty(params);
		Assert.assertNotNull(menu);
	}

	@Test
	public void testGetRecursiveMenuListByProperty(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("parentId","0");
		List<Menu> menuList = menuService.getRecursiveMenuListByProperty(params);
		Assert.assertNotNull(menuList);
	}

	@Test
	public void testGetMenuListByProperty(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id","1");
		Menu menu = menuService.getMenuRecursivelyByProperty(params);
		Assert.assertNotNull(menu);
	}

	@Test
	public  void testDeleteMenuById(){
		int count = menuService.deleteMenuById(testId);
		Assert.assertEquals(1,count);
	}

	@Test
	public  void testDeleteMenuRecursivelyById(){
		int count = menuService.deleteMenuRecursivelyById(testId);
		Assert.assertNotEquals(0,count);
	}

	@Test
	public  void testUpdateMenuById(){
		Menu menu = new Menu();
		menu.setId(testId);
		menu.setName("testUpdatedName");
		int count= menuService.updateMenuById(menu);
		Assert.assertEquals(1,count);
	}
}
