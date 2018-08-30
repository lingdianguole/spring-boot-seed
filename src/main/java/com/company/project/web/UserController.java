package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CodeGenerator on 2018/08/30.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result add(User user) {
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
        User user = userService.findBy("nickName", nickName);
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/update")
    public Result update(User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        User user = userService.findById(id);
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

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
