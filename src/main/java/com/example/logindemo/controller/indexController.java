package com.example.logindemo.controller;

import com.example.logindemo.Util.PhoneCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import static com.example.logindemo.Util.PhoneCode.getPhonemsg;

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
//    String str = new String(request.getParameter("参数名").getBytes("iso-8859-1"), "utf-8");

//手机登录页面
    @GetMapping("/sendcode")
    public String sendcode(){
        return "sendcode";
    }

    //验证码页面
    @GetMapping("/phonelogin")
    public String phonelogin(){
        return "phonelogin";
    }

    //注册方法
    @RequestMapping("/addregister")
    public String register(HttpServletRequest request, Map<String,Object> map) throws UnsupportedEncodingException {

        String username = new String(request.getParameter("username").getBytes("iso-8859-1"), "utf-8");
        String userPhone = new String(request.getParameter("userPhone").getBytes("iso-8859-1"), "utf-8");
        String userArea = new String(request.getParameter("userArea").getBytes("iso-8859-1"), "utf-8");
        String userSort = new String(request.getParameter("userSort").getBytes("iso-8859-1"), "utf-8");
        String password = new String(request.getParameter("password").getBytes("iso-8859-1"), "utf-8");
        String password2 = new String(request.getParameter("password2").getBytes("iso-8859-1"), "utf-8");

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
    @RequestMapping("/sendcode")
    public String sendcode(HttpServletRequest request,Map<String,Object> map){
        HttpSession session = request.getSession();
        String phone = request.getParameter("phonenumber");
        String code = PhoneCode.vcode();
        session.setAttribute("code",code);
        String sms = getPhonemsg(phone,code);
        if (sms.equals("-1")){
            map.put("msg","获取验证码失败,请稍后重试！");
            return "sendcode";
        }
        return "phonelogin";
    }

    @RequestMapping("/addphonelogin")
    public String addphonelogin(HttpServletRequest request,Map<String,Object> map){
        String code = request.getParameter("code");
        String sessCode = (String) request.getSession().getAttribute("code");
        if (!code.equals(sessCode)){
            map.put("msg","验证码输入错误！");
            return "phonelogin";
        }else {
            map.put("msg","登陆成功");
            return "index";
        }
    }

}
