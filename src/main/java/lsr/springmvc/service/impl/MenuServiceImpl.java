package lsr.springmvc.service.impl;

import lsr.springmvc.dao.MenuDAO;
import lsr.springmvc.model.Menu;
import lsr.springmvc.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsr on 2017/2/26.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDAO menuDAO;

    public int insertMenu(Menu menu) {
        return menuDAO.insertMenu(menu);
    }

    public Menu getMenuRecursivelyByProperty(Map params) {
        return menuDAO.getMenuRecursivelyByProperty(params);
    }

    public List<Menu> getRecursiveMenuListByProperty(Map params) {
        return menuDAO.getRecursiveMenuListByProperty(params);
    }

    public List<Menu> getMenuListByProperty(Map params) {
        return menuDAO.getMenuListByProperty(params);
    }

    public int deleteMenuById(String id) {
        return menuDAO.deleteMenuById(id);
    }

    public int deleteMenuRecursivelyById(String id) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("id",id);
        Menu deleteMenu = menuDAO.getMenuRecursivelyByProperty(param);
        ArrayList<String> deleteIdList = new ArrayList<String>();
        getSubMenuIdRecursively(deleteMenu,deleteIdList);
        return menuDAO.deleteMenuByIdList(deleteIdList);
    }

    private void getSubMenuIdRecursively(Menu pMenu,ArrayList<String> menuIdList){
        if(pMenu==null) {
            return;
        }
        String deleteMenuId = pMenu.getId();
        menuIdList.add(deleteMenuId);
        List<Menu> childrenMenu = pMenu.getChildren();
        if(childrenMenu==null){
            return;
        }
        for(Menu cMenu:childrenMenu){
            getSubMenuIdRecursively(cMenu,menuIdList);
        }
    }

    public int updateMenuById(Menu menu) {
        return menuDAO.updateMenuById(menu);
    }

    public int changeMenuOrder(Map params) {
        String curMenuId = (String)params.get("id");
        String direction  = (String)params.get("direction");
        Menu curMenu = menuDAO.getMenuById(curMenuId);
        int order = curMenu.getOrder();

        Map<String,Object> switchParam = new HashMap<String,Object>();
        switchParam.put("parentId",curMenu.getParentId());
        switchParam.put("order",order);
        Menu switchMenu=null;

        if("forward".equalsIgnoreCase(direction)){
            switchMenu = menuDAO.findFirstForwardMenu(switchParam);
        }
        else if("backward".equalsIgnoreCase(direction)){
            switchMenu = menuDAO.findFirstBackwardMenu(switchParam);
        }

        if(switchMenu==null){
            return 0;
        }

        int switchOrder = switchMenu.getOrder();
        switchMenu.setOrder(order);
        menuDAO.updateMenuById(switchMenu);

        curMenu.setOrder(switchOrder);
        return menuDAO.updateMenuById(curMenu);
    }

}
