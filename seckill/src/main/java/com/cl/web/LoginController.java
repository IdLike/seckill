package com.cl.web;

import com.cl.entity.User;
import com.cl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value ="/DoLogin" ,method = RequestMethod.GET)
    public  String toLogin(){

        return  "login";
    }

    @RequestMapping(value = "/loginAction" ,method = RequestMethod.POST)
    public  String login(User user, HttpServletRequest request){

        User loginUser=userService.findByUserName(user.getUsername());
        if(loginUser!=null && loginUser.getPassword().equals(user.getPassword()) ){
            request.getSession().setAttribute("user",loginUser);
            return "redirect:/seckill/list";
        }
        request.setAttribute("errorMsg","用户名或密码错误");
        return  "login";


    }
}
