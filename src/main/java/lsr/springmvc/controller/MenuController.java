package lsr.springmvc.controller;

import lsr.springmvc.model.User;
import lsr.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public List<User> queryUserList(){
        Map<String,String> param = new HashMap();
        return userService.getUserList(param);
    }
}
