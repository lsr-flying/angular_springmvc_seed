package lsr.springmvc.controller;

import com.alibaba.fastjson.JSONObject;
import lsr.springmvc.common.ResponseHelper;
import lsr.springmvc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lsr on 2017/6/29.
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping("/insertRole")
    @ResponseBody
    public JSONObject insertRole(@RequestBody JSONObject requestJSON){
        roleService.insertRole(requestJSON);
        return ResponseHelper.generateResponseJSON("000","插入成功");
    }

    @RequestMapping("/getRoleList")
    @ResponseBody
    public JSONObject getRoleList(@RequestBody JSONObject requestJSON){
        return ResponseHelper.generateResponseJSON("000",
                "查询成功",
                roleService.getRoleList(requestJSON));
    }

    @RequestMapping("/queryRoleDetailById")
    @ResponseBody
    public JSONObject queryRoleDetailById(@RequestBody JSONObject requestJSON){
        return ResponseHelper.generateResponseJSON("000",
                "查询成功",
                roleService.queryRoleDetailById(requestJSON));
    }

    @RequestMapping("/queryRoleDetailByKey")
    @ResponseBody
    public JSONObject queryRoleDetailByKey(@RequestBody JSONObject requestJSON){
        return ResponseHelper.generateResponseJSON("000",
                "查询成功",
                roleService.queryRoleDetailByKey(requestJSON));
    }

    @RequestMapping("/updateRoleById")
    @ResponseBody
    public JSONObject updateRoleById(@RequestBody JSONObject requestJSON){
        roleService.updateRoleById(requestJSON);
        return ResponseHelper.generateResponseJSON("000","更新成功");
    }

    @RequestMapping("/updateRoleByKey")
    @ResponseBody
    public JSONObject updateRoleByKey(@RequestBody JSONObject requestJSON){
        roleService.updateRoleByKey(requestJSON);
        return ResponseHelper.generateResponseJSON("000","更新成功");
    }

    @RequestMapping("/deleteRoleById")
    @ResponseBody
    public JSONObject deleteRoleById(@RequestBody JSONObject requestJSON){
        roleService.deleteRoleById(requestJSON);
        return ResponseHelper.generateResponseJSON("000","删除成功");
    }

    @RequestMapping("/deleteRoleByKey")
    @ResponseBody
    public JSONObject deleteRoleByKey(@RequestBody JSONObject requestJSON){
        roleService.deleteRoleByKey(requestJSON);
        return ResponseHelper.generateResponseJSON("000","删除成功");
    }

    @RequestMapping("/insertUserRole")
    @ResponseBody
    public JSONObject insertUserRole(@RequestBody JSONObject requestJSON){
        roleService.insertUserRole(requestJSON);
        return ResponseHelper.generateResponseJSON("000","插入成功");
    }

    @RequestMapping("/deleteLinkUserRole")
    @ResponseBody
    public JSONObject deleteLinkUserRole(@RequestBody JSONObject requestJSON){
        roleService.deleteLinkUserRole(requestJSON);
        return ResponseHelper.generateResponseJSON("000","删除成功");
    }

    @RequestMapping("/deleteLinkRole")
    @ResponseBody
    public JSONObject deleteLinkRole(@RequestBody JSONObject requestJSON){
        roleService.deleteLinkRole(requestJSON);
        return ResponseHelper.generateResponseJSON("000","删除成功");
    }

    @RequestMapping("/deleteLinkUser")
    @ResponseBody
    public JSONObject deleteLinkUser(@RequestBody JSONObject requestJSON){
        roleService.deleteLinkUser(requestJSON);
        return ResponseHelper.generateResponseJSON("000","删除成功");
    }
}
