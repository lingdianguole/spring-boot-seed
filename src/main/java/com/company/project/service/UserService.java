package com.company.project.service;

import com.company.project.model.User;
import com.company.project.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2018/08/30.
 */
public interface UserService extends Service<User> {
    List<User> queryForList();//获取所有

    User queryNickNameLike(String username); //模糊查询

}
