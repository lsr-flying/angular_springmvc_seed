package lsr.springmvc.controller;

import com.alibaba.fastjson.JSONObject;
import lsr.springmvc.model.Menu;
import lsr.springmvc.model.User;
import lsr.springmvc.service.MenuService;
import lsr.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lsr on 2017/1/29.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping("/list")
    @ResponseBody
    public List<Menu> queryMenuList(){
        Map<String,Object> param = new HashMap();
        List<Menu> menuList = menuService.getMenuListByProperty(param);
        return menuList;
    }

    @RequestMapping("/hierachyList")
    @ResponseBody
    public List<Menu> queryMenuHierachyList(){
        Map<String,Object> param = new HashMap();
        List<Menu> menuList = menuService.getRecursiveMenuListByProperty(param);
        return menuList;
    }

    @RequestMapping("/create")
    @ResponseBody
    public JSONObject createMenu(@RequestBody JSONObject requestJSON){
        Menu createMenu = new Menu();
        createMenu.setId(UUID.randomUUID().toString());
        createMenu.setName(requestJSON.getString("name"));
        createMenu.setIcon(requestJSON.getString("icon"));
        createMenu.setType(requestJSON.getString("type"));
        createMenu.setLink(requestJSON.getString("link"));
        createMenu.setDataStatus(StringUtils.isEmpty(requestJSON.getString("dataStatus"))
                ?"1":requestJSON.getString("dataStatus"));
        createMenu.setParentId(requestJSON.getString("parentId"));
        createMenu.setCreatedBy(requestJSON.getString("createdBy"));
        String order = requestJSON.getString("order");
        if(!StringUtils.isEmpty(order)){
            Integer orderInt = Integer.parseInt(order);
            createMenu.setOrder(orderInt);
        }
        int addedCount = menuService.insertMenu(createMenu);
        JSONObject rtnJSON = new JSONObject();
        rtnJSON.put("rtnCode",addedCount>0?"000":"999");
        return rtnJSON;
    }

    @ResponseBody
    @RequestMapping("/deleteMenuById")
    public JSONObject deleteMenuById(@RequestBody JSONObject requestJSON){
        JSONObject rtnJSON = new JSONObject();
        try {
            String menuId = requestJSON.getString("id");
            String isRecursively = requestJSON.getString("isRecursively");
            int delCount;
            if("Y".equalsIgnoreCase(isRecursively)) {
                delCount = menuService.deleteMenuById(menuId);
            }else{
                delCount = menuService.deleteMenuRecursivelyById(menuId);
            }
            rtnJSON.put("delCount",delCount);
            rtnJSON.put("rtnCode","000");
        }
        catch(Exception e){
            e.printStackTrace();
            rtnJSON.put("rtnCode","999");
        }
        return rtnJSON;
    }

    @RequestMapping("/update")
    @ResponseBody
    public JSONObject updateMenu(@RequestBody JSONObject requestJSON){
        JSONObject rtnJSON = new JSONObject();
        String id = requestJSON.getString("id");
        if(StringUtils.isEmpty(id)){
            rtnJSON.put("rtnCode","999");
            rtnJSON.put("rtnMsg","必填字段id缺失");
            return rtnJSON;
        }
        try {
            Menu updateMenu = new Menu();
            updateMenu.setId(id);
            updateMenu.setName(requestJSON.getString("name"));
            updateMenu.setIcon(requestJSON.getString("icon"));
            updateMenu.setType(requestJSON.getString("type"));
            updateMenu.setLink(requestJSON.getString("link"));
            updateMenu.setDataStatus(StringUtils.isEmpty(requestJSON.getString("dataStatus"))
                    ?"1":requestJSON.getString("dataStatus"));
            updateMenu.setParentId(requestJSON.getString("parentId"));
            updateMenu.setUpdatedBy(requestJSON.getString("createdBy"));
            String order = requestJSON.getString("order");
            if(!StringUtils.isEmpty(order)){
                Integer orderInt = Integer.parseInt(order);
                updateMenu.setOrder(orderInt);
            }
            int updateCount = menuService.updateMenuById(updateMenu);
            rtnJSON.put("rtnCode","000");
            rtnJSON.put("updateCount",updateCount);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            rtnJSON.put("rtnCode","999");
        }
        return rtnJSON;
    }

    @RequestMapping("/changeOrder")
    @ResponseBody
    public JSONObject changeOrder(@RequestBody JSONObject requestJSON){
        JSONObject rtnJSON = new JSONObject();
        String id = requestJSON.getString("id");
        String direction = requestJSON.getString("direction");
        if(StringUtils.isEmpty(id) || StringUtils.isEmpty(direction)){
            rtnJSON.put("rtnCode","999");
            rtnJSON.put("rtnMsg","必填字段缺失");
            return rtnJSON;
        }
        try {
            int updateCount = menuService.changeMenuOrder(requestJSON);
            rtnJSON.put("rtnCode",updateCount>0?"000":"111");
            rtnJSON.put("rtnMsg","成功");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            rtnJSON.put("rtnCode","999");
        }
        return rtnJSON;
    }
}
