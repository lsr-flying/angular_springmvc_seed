package lsr.springmvc.controller;

import com.alibaba.fastjson.JSONObject;
import lsr.springmvc.model.User;
import lsr.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsr on 2017/1/29.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/list")
    @ResponseBody
    public JSONObject queryUserList(@RequestBody JSONObject requestJSON){
        return userService.getUserList(requestJSON);
    }

    @RequestMapping("/detail")
    @ResponseBody
    public User queryUserDetail(@RequestBody JSONObject requestJSON){
        return userService.queryUserDetailById(requestJSON);
    }

    @RequestMapping("/save")
    @ResponseBody
    public int saveUser(@RequestBody JSONObject requestJSON){
        User user = JSONObject.parseObject(requestJSON.toJSONString(),User.class);
        return userService.insertUser(user);
    }

    @RequestMapping("/update")
    @ResponseBody
    public int updateUser(@RequestBody JSONObject requestJSON){
        return userService.updateUserById(requestJSON);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public int deleteUser(@RequestBody JSONObject requestJSON){
        return userService.deleteUserById(requestJSON);
    }
}
