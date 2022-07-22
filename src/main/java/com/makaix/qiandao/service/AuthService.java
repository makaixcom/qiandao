package com.makaix.qiandao.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.makaix.qiandao.bean.entity.Role;
import com.makaix.qiandao.bean.entity.User;
import com.makaix.qiandao.bean.pojo.resource.ResourcePojo;
import com.makaix.qiandao.mapper.ResourceMapper;
import com.makaix.qiandao.mapper.RoleMapper;
import com.makaix.qiandao.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AuthService {

    private UserMapper userMapper;

    private RoleMapper roleMapper;

    private ResourceMapper resourceMapper;

    public User findByUsername(String username) {
        QueryWrapper<User> userQuery = new QueryWrapper<>();
        userQuery.eq("username", username);
        return userMapper.selectOne(userQuery);
    }

    public List<Role> findRolesByUserId(Long id) {
        return roleMapper.selectRolesByUserId(id);
    }

    public List<ResourcePojo> listAll() {
        return resourceMapper.resourceRole();
    }
}
