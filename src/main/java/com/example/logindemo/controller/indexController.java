package com.example.logindemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/front/*")
public class indexController {


    @GetMapping("/")
    public String index(HttpServletRequest request){
        return "index";
    }

    //注册页面
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    //登录页面
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //注册方法
    @RequestMapping("/addregister")
    public String register(HttpServletRequest request, Map<String,Object> map){
        String username = request.getParameter("username");
        String userPhone = request.getParameter("userPhone");
        String userArea = request.getParameter("userArea");
        String userSort = request.getParameter("userSort");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        if (userArea.equals("暂无")){
            map.put("msg","请选择考生所在省份!");
            return "register";
        }
        else if (userSort.equals("暂无")){
            map.put("msg","请选择考生文理科!");
            return "register";
        }
            return "redirect:http://54.223.179.35:9090//front/addregister?username="+username+"&userPhone="+userPhone
                    +"&userArea="+userArea+"&userSort="+userSort+"&password="+password+"&password2="+password2;
        }


    //登陆方法
    @RequestMapping("/addlogin")
    public String addlogin(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return "redirect:http://54.223.179.35:9090//front/addlogin?username="+username+"&password="+password;

    }
}
