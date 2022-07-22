package com.makaix.qiandao.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.makaix.qiandao.bean.entity.User;
import com.makaix.qiandao.bean.vo.base.BaseIdReqVo;
import com.makaix.qiandao.bean.vo.user.*;
import com.makaix.qiandao.mapper.UserMapper;
import com.makaix.qiandao.utils.other.MakaixBeanUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private UserMapper userMapper;

    private PasswordEncoder passwordEncoder;

    @Transactional
    public void add(UserAddReqVo reqVo) {
        User user = MakaixBeanUtils.copy(reqVo, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
    }

    public UserListResVo list(UserListReqVo reqVo) {

        QueryWrapper<User> userQuery = Wrappers.query();

        userQuery.eq(StringUtils.hasText(reqVo.username()), "username", reqVo.username());

        userQuery.eq(StringUtils.hasText(reqVo.mobile()), "mobile", reqVo.mobile());

        Page<User> userPage = userMapper.selectPage(Page.of(reqVo.page(), reqVo.size()), userQuery);

        List<UserListResVo.UserListDataResVo> collect = userPage.getRecords()
                .stream()
                .map(e -> new UserListResVo.UserListDataResVo(e.getId(), e.getUsername(), e.getMobile(), e.getCreateDateTime(), e.getModifyDateTime()) )
                .toList();

        return new UserListResVo(userPage.getTotal(), collect);
    }

    public UserGetResVo get(BaseIdReqVo reqVo) {
        User user = userMapper.selectById(reqVo.id());
        return new UserGetResVo(user.getId(), user.getUsername(), user.getMobile());
    }

    @Transactional
    public void edit(UserEditReqVo reqVo) {
        User user = MakaixBeanUtils.copy(reqVo, User.class);
        userMapper.updateById(user);
    }

    @Transactional
    public void delete(BaseIdReqVo reqVo) {
        userMapper.deleteById(reqVo.id());
    }

    @Transactional
    public void resetPwd(UserResetPwdReqVo reqVo) {
        User user = MakaixBeanUtils.copy(reqVo, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.updateById(user);
    }
}
