package lsr.springmvc.dao;

import lsr.springmvc.model.Menu;
import lsr.springmvc.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MenuDAO {

	/**
	 * 新增导航菜单
	 * @param menu
	 * @return
	 */
	int insertMenu(Menu menu);
	
	Menu getMenuRecursivelyByProperty(Map params);

	Menu getMenuById(String id);

	List<Menu> getRecursiveMenuListByProperty(Map params);

	List<Menu> getMenuListByProperty(Map params);

    Menu findFirstForwardMenu(Map params);

    Menu findFirstBackwardMenu(Map params);

	int deleteMenuById(String id);

	int deleteMenuByIdList(ArrayList deleteIdList);

	int updateMenuById(Menu menu);
}
