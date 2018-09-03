package com.company.project.web;

import com.alibaba.druid.util.StringUtils;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.jwt.JwtToken;
import com.company.project.model.MyPageInfo;
import com.company.project.model.Phone;
import com.company.project.model.User;
import com.company.project.service.PhoneService;
import com.company.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @PostMapping("/add")
    public Result add(User user) throws InterruptedException {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
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

    @GetMapping("/info")  //根据名字来查数据
    public Result info(String nickName) {
//      User user = userService.findBy("nickName", nickName); //精确查找
//      User user = userService.queryNickNameLike(nickName);
        Condition condition = new Condition(User.class);
        condition.createCriteria().andLike("nickName", '%' + nickName + '%');
        List<User> user = userService.findByCondition(condition);
        if (user == null) {
            return ResultGenerator.genSuccessResult(new ArrayList<>());
        }
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/getUserByUsername")
    public Result find(String userName) {
        User user = userService.findBy("username", userName);
        return ResultGenerator.genSuccessResult(user);
    }


    public User findByNickname(String userName) {
        User user = userService.findBy("nickName","ygl");
        return user;
    }

    @PostMapping("/update")
    public Result update(User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        User user = userService.findById(id);
        Condition condition = new Condition(Phone.class);
        condition.createCriteria().andCondition("userid =" + user.getId());
        condition.setOrderByClause("phoneid desc");
        List<Phone> phones = phoneService.findByCondition(condition);
        user.setPhone(phones);
        return ResultGenerator.genSuccessResult(user);
    }

    @GetMapping("/detailmany")
    public Result detail(@RequestParam String ids) {
        List<User> users = userService.findByIds(ids);
        return ResultGenerator.genSuccessResult(users);
    }

    @GetMapping("/infocondition")
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
        if (currentUser != null && user.getPassword().equals(currentUser.getPassword())) {
            return ResultGenerator.genSuccessResult("登录成功");
        }
        return ResultGenerator.genFailResult("账号或密码错误");
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer
            size) {
        PageHelper.startPage(page, size);
//      List<User> list = userService.findAll();
        List<User> list = userService.queryForList();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(new MyPageInfo<>(pageInfo));
    }
}
