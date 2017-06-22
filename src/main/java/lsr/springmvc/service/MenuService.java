package lsr.springmvc.service;

import lsr.springmvc.model.Menu;
import lsr.springmvc.model.User;

import java.util.List;
import java.util.Map;

public interface MenuService {

	 int insertMenu(Menu menu);

	 Menu getMenuRecursivelyByProperty(Map params);

	 List<Menu> getRecursiveMenuListByProperty(Map params);

	List<Menu> getMenuListByProperty(Map params);

	int deleteMenuById(String id);

	int deleteMenuRecursivelyById(String id);

	int updateMenuById(Menu menu);

	int changeMenuOrder(Map params);
}
