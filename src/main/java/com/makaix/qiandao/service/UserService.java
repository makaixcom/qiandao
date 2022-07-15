package com.makaix.qiandao.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.makaix.qiandao.bean.entity.User;
import com.makaix.qiandao.bean.vo.base.BaseIdReqVo;
import com.makaix.qiandao.bean.vo.user.*;
import com.makaix.qiandao.mapper.UserMapper;
import com.makaix.qiandao.utils.security.DigestUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private UserMapper userMapper;

    @Transactional
    public void add(UserAddReqVo reqVo) {
        User user = new User();
        user.username(reqVo.username());
        user.password(DigestUtils.sha1(reqVo.password()));
        user.mobile(reqVo.mobile());
        userMapper.insert(user);
    }

    public UserListResVo list(UserListReqVo reqVo) {

        QueryWrapper<User> userQuery = Wrappers.query();

        userQuery.eq(StringUtils.hasText(reqVo.username()), "username", reqVo.username());

        userQuery.eq(StringUtils.hasText(reqVo.mobile()), "mobile", reqVo.mobile());

        Page<User> userPage = userMapper.selectPage(Page.of(reqVo.page(), reqVo.size()), userQuery);

        List<UserListResVo.UserListDataResVo> collect = userPage.getRecords()
                .stream()
                .map(e -> new UserListResVo.UserListDataResVo(e.id(), e.username(), e.mobile(), e.createDateTime(), e.modifyDateTime()) )
                .toList();

        return new UserListResVo(userPage.getTotal(), collect);
    }

    public UserGetResVo get(BaseIdReqVo reqVo) {
        User user = userMapper.selectById(reqVo.id());
        return new UserGetResVo(user.id(), user.username(), user.mobile());
    }

    @Transactional
    public void edit(UserEditReqVo reqVo) {
        User user = new User();
        user.id(reqVo.id());
        user.username(reqVo.username());
        user.mobile(reqVo.mobile());

        userMapper.updateById(user);
    }

    @Transactional
    public void delete(BaseIdReqVo reqVo) {
        userMapper.deleteById(reqVo.id());
    }

    @Transactional
    public void resetPwd(UserResetPwdReqVo reqVo) {
        User user = new User();
        user.id(reqVo.id());
        user.password(DigestUtils.sha1(reqVo.pwd()));
        userMapper.updateById(user);
    }
}
