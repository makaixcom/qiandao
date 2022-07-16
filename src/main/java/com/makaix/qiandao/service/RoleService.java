package com.makaix.qiandao.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.makaix.qiandao.bean.entity.Role;
import com.makaix.qiandao.bean.vo.base.BaseIdReqVo;
import com.makaix.qiandao.bean.vo.role.*;
import com.makaix.qiandao.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@AllArgsConstructor
@Service
public class RoleService {

    private RoleMapper roleMapper;

    public RoleListResVo list(RoleListReqVo reqVo) {
        QueryWrapper<Role> userQuery = Wrappers.query();

        userQuery.eq(StringUtils.hasText(reqVo.name()), "name", reqVo.name());

        userQuery.eq(StringUtils.hasText(reqVo.code()), "code", reqVo.code());

        Page<Role> userPage = roleMapper.selectPage(Page.of(reqVo.page(), reqVo.size()), userQuery);

        List<RoleListResVo.RoleListDataResVo> collect = userPage.getRecords()
                .stream()
                .map(e -> new RoleListResVo.RoleListDataResVo(e.id(), e.name(), e.code(), e.remark(), e.seq(), e.createDateTime(), e.modifyDateTime()) )
                .toList();

        return new RoleListResVo(userPage.getTotal(), collect);
    }

    @Transactional
    public void add(RoleAddReqVo reqVo) {
        Role role = new Role();
        role.name(reqVo.name());
        role.code(reqVo.code());
        role.remark(reqVo.remark());
        role.seq(reqVo.seq());
        roleMapper.insert(role);
    }

    public RoleGetResVo get(BaseIdReqVo reqVo) {
        Role role = roleMapper.selectById(reqVo.id());
        return new RoleGetResVo(role.id(), role.name(), role.code(), role.remark(), role.seq());
    }

    @Transactional
    public void edit(RoleEditReqVo reqVo) {
        Role role = new Role();
        role.id(reqVo.id());
        role.name(reqVo.name());
        role.code(reqVo.code());
        role.remark(reqVo.remark());
        role.seq(reqVo.seq());

        roleMapper.updateById(role);
    }

    @Transactional
    public void delete(BaseIdReqVo reqVo) {
        roleMapper.deleteById(reqVo.id());
    }


}
