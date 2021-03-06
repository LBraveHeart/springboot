package com.example.springboot.controller;

import com.example.springboot.dto.LoginDTO;
import com.example.springboot.shrio.ShiroSessionCache;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * 登录、注销
 * @author liuyy
 * @date 2019年5月7日16:18:04
 */
@Controller
public class PassController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(PassController.class);

    @RequestMapping("/login")
    public String login(){

//        Subject user = SecurityUtils.getSubject();
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhangsan","zhangsan",true);
//        user.login(usernamePasswordToken);
//        ShiroSessionCache.login(user.getSession(),getCurrentUser());
        ///logger.debug("登录前："+user.isAuthenticated());
        /*if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/index";
        }*/
        return "login";
    }

    @RequestMapping("/dologin")
    public String dologin(LoginDTO loginDTO, Model model) throws IOException {
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginDTO.getUserName(),loginDTO.getPassword(),false);
        try {
            user.login(usernamePasswordToken);
            ShiroSessionCache.login(user.getSession(),getCurrentUser());
            logger.debug("登录后："+user.isAuthenticated());
            return "redirect:/index";
        }catch (UnknownAccountException e) {
            //账号不存在和下面密码错误一般都合并为一个账号或密码错误，这样可以增加暴力破解难度
            model.addAttribute("message", "账号不存在！");
        } catch (DisabledAccountException e) {
            model.addAttribute("message", "账号未启用！");
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("message", "密码错误！");
        } catch (Throwable e) {
            model.addAttribute("message", "未知错误！");
        }
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(){
        logger.debug("注销前："+SecurityUtils.getSubject().isAuthenticated());
        Subject user = SecurityUtils.getSubject();
        Session session = user.getSession();
        session.removeAttribute("userName");
        user.logout();
        logger.debug("注销后："+SecurityUtils.getSubject().isAuthenticated());
        return "redirect:/login";
    }
}
