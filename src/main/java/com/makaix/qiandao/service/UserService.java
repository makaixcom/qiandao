package com.makaix.qiandao.service;

import com.makaix.qiandao.bean.entity.User;
import com.makaix.qiandao.bean.vo.user.UserAddReqVo;
import com.makaix.qiandao.mapper.UserMapper;
import com.makaix.qiandao.utils.other.MakaixBeanUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UserService {

    private UserMapper userMapper;

    @Transactional
    public void add(UserAddReqVo reqVo) {
        User user = MakaixBeanUtils.copy(reqVo, User.class);
        userMapper.insert(user);
    }
}
