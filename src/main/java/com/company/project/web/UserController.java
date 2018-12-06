package com.company.project.web;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.annotation.JSONField;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.jwt.JwtToken;
import com.company.project.model.Authority;
import com.company.project.model.MyPageInfo;
import com.company.project.model.Phone;
import com.company.project.model.User;
import com.company.project.security.JwtTokenUtil;
import com.company.project.security.JwtUser;
import com.company.project.service.AuthorityService;
import com.company.project.service.PhoneService;
import com.company.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by CodeGenerator on 2018/08/30.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private PhoneService phoneService;
    @Resource
    private AuthorityService authorService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public Result add(User user) {
        User curUser = userService.findBy("username", user.getUsername()); //精确查找username
        if (curUser != null) {
            return ResultGenerator.genSuccessResult("当前用户已注册注册");
        }
        //把密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setRegisterDate(new Date());
        user.setUpdateDate(new Date());
        userService.save(user);
        return ResultGenerator.genSuccessResult("注册成功");
    }

    @PostMapping("/addAll")
    public Result addAll(List<User> users) {
        userService.save(users);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/deletManey")
    public Result deleteManey(@RequestParam String ids) {
        userService.deleteByIds(ids); //形如1,2,3
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/info")  //根据名字来查数据
    public Result info(String nickName) {
        User user = userService.findBy("nickName", nickName); //精确查找
//        User user = userService.queryNickNameLike(nickName);
//        Condition condition = new Condition(User.class);
//        condition.createCriteria().andLike("nickName", '%' + nickName + '%');
//        List<User> user = userService.findByCondition(condition);
        if (user == null) {
            return ResultGenerator.genSuccessResult(new ArrayList<>());
        }
        return ResultGenerator.genSuccessResult(user);
    }

    @RequestMapping("/index")
    public String index() {
        System.out.println("Looking in the index controller.........");
        return "index.html";
    }

    @PostMapping("/getUserByUsername")
    public Result find(String username) {
        User user = userService.findBy("username", username);
        return ResultGenerator.genSuccessResult(user);
    }


    public User findByUsername(@RequestParam String username) {
        User user = userService.findBy("username", username);
        Condition condition = new Condition(Authority.class);
        condition.createCriteria().andCondition("id=" + user.getId());
        List<Authority> authorities = authorService.findByCondition(condition);
        user.setAuthorities(authorities);
        return user;
    }

    @PostMapping("/update")
    public Result update(User user) {
        if (user.getUsername() != null) {
            return ResultGenerator.genFailResult("不能修改用户名");
        }
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        User user = userService.findById(id);
        user.setToken(null); //设置成null转成json会过滤掉
        Condition condition = new Condition(Phone.class);
        condition.createCriteria().andCondition("userid =" + user.getId());
        condition.setOrderByClause("phoneid desc");
        List<Phone> phones = phoneService.findByCondition(condition);
        user.setPhone(phones);
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/detailmany")
    public Result detail(@RequestParam String ids) {
        List<User> users = userService.findByIds(ids);
        return ResultGenerator.genSuccessResult(users);
    }

    @PostMapping("/infocondition")
    public Result info(@RequestParam Condition condition) {
        List<User> users = userService.findByCondition(condition);
        return ResultGenerator.genSuccessResult(users);
    }

    @PostMapping("/login")
    public Result login(User user) {
        if (StringUtil.isEmpty(user.getUsername())) {
            return ResultGenerator.genFailResult("请输入用户名");
        }
        if (StringUtil.isEmpty(user.getPassword())) {
            return ResultGenerator.genFailResult("请输入密码");
        }
        User currentUser = userService.findBy("username", user.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (currentUser != null && encoder.matches(user.getPassword(), currentUser.getPassword())) {
            String token = jwtTokenUtil.generateToken(new JwtUser(currentUser.getId(), currentUser.getUsername(), currentUser.getPassword(), null, true, new Date()));
            currentUser.setUpdateDate(new Date()); //更新登录时间
            currentUser.setToken(token); //更新token
            userService.update(currentUser);
            return ResultGenerator.genSuccessResult(currentUser);
        }
        return ResultGenerator.genFailResult("账号或密码错误");
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer
            size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.queryForList();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(new MyPageInfo<>(pageInfo));
    }
}
