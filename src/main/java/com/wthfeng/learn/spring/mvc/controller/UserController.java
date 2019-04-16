package com.wthfeng.learn.spring.mvc.controller;

import com.wthfeng.learn.spring.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangtonghe
 * @since 2019/4/3 20:12
 */

@Controller
public class UserController {


    @RequestMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("message", "I am ok");
        return "index";
    }

    @RequestMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("message", "I am ok");
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/user")
    public UserInfo user() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser("wthfeng");
        userInfo.setPassword("123445");
        return userInfo;
    }
}
