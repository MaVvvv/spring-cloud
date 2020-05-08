package com.hxb.shiro.web;

import com.hxb.shiro.core.shiro.PasswordHelper;
import com.hxb.shiro.po.SysUser;
import com.hxb.shiro.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主页控制层
 *
 * @author Ma_wei
 * @since 2019-07-12 16:13
 */
@RestController
@RequestMapping
public class HomeController {

    @Autowired
    private ISysUserService sysUserServiceImpl;

    @Autowired
    private PasswordHelper passwordHelper;

    @GetMapping(value = "/login")
    public String login () {
        return "login";
    }

    @GetMapping(value = "/unauthc")
    public String unauthc () {
        return "Here is Unauthc page";
    }

    @GetMapping(value = "/doLogin")
    public String doLogin (@RequestParam String userName,@RequestParam String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            return "password error!";
        } catch (UnknownAccountException uae) {
            return "username error!";
        }

        SysUser user = sysUserServiceImpl.getUserInfoByName(userName);
        subject.getSession().setAttribute("user", user);
        return "SUCCESS";
    }

    @GetMapping("register")
    public Object register(@RequestParam String username, @RequestParam String password) {
        SysUser user = new SysUser();
        user.setName(username);
        user.setPassword(password);
        passwordHelper.encryptPassword(user);

        //userService.saveUser(user);
        return "SUCCESS";
    }
}
