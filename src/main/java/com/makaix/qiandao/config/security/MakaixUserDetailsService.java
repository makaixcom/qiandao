package com.makaix.qiandao.config.security;

import com.makaix.qiandao.bean.entity.Role;
import com.makaix.qiandao.bean.entity.User;
import com.makaix.qiandao.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class MakaixUserDetailsService implements UserDetailsService {

    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        // 查找该用户所有的角色编码
        List<Role> rolesByUserId = authService.findRolesByUserId(user.getId());
        List<SimpleGrantedAuthority> authorities = rolesByUserId.stream().map(e -> new SimpleGrantedAuthority( e.getCode())).toList();

        // 赋值一个新的 UserDetail 对象
        MakaixUserDetail userDetail = new MakaixUserDetail();
        userDetail.setId(user.getId());
        userDetail.setDisabled(false);
        userDetail.setExpired(false);
        userDetail.setLocked(false);
        userDetail.setCredentialsExpired(false);

        userDetail.getResources().addAll(authorities);

        return userDetail;
    }
}
