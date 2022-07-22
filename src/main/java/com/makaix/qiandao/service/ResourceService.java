package com.makaix.qiandao.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.makaix.qiandao.bean.entity.Resource;
import com.makaix.qiandao.bean.entity.RoleResource;
import com.makaix.qiandao.bean.vo.base.BaseIdReqVo;
import com.makaix.qiandao.bean.vo.resource.*;
import com.makaix.qiandao.mapper.ResourceMapper;
import com.makaix.qiandao.mapper.RoleResourceMapper;
import com.makaix.qiandao.utils.other.MakaixBeanUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ResourceService {

    private ResourceMapper resourceMapper;

    private RoleResourceMapper roleResourceMapper;

    public ResourceListResVo list() {
        QueryWrapper<Resource> userQuery = Wrappers.query();
        userQuery.orderByAsc("seq");
        List<Resource> resources = resourceMapper.selectList(userQuery);

        List<ResourceListResVo.ResourceListDataResVo> collect = resources
                .stream()
                .map(e -> new ResourceListResVo.ResourceListDataResVo(e.getId(), e.getName(), e.getType(), e.getUrl(), e.getRemark(), e.getIcon(), e.getSeq(), e.getMethod(), e.getPid(), e.getCreateDateTime(), e.getModifyDateTime()))
                .toList();

        return new ResourceListResVo(collect);
    }

    @Transactional
    public void add(ResourceAddReqVo reqVo) {
        Resource resource = MakaixBeanUtils.copy(reqVo, Resource.class);
        resourceMapper.insert(resource);
    }

    public ResourceGetResVo get(BaseIdReqVo reqVo) {
        Resource e = resourceMapper.selectById(reqVo.id());
        return new ResourceGetResVo(e.getId(), e.getName(), e.getType(), e.getUrl(), e.getRemark(), e.getIcon(), e.getSeq(), e.getMethod(), e.getPid());
    }

    @Transactional
    public void edit(ResourceEditReqVo reqVo) {
        Resource resource = MakaixBeanUtils.copy(reqVo, Resource.class);
        resourceMapper.updateById(resource);
    }

    @Transactional
    public void delete(BaseIdReqVo reqVo) {
        resourceMapper.deleteById(reqVo.id());
    }

    @Transactional
    public void grant(ResourceGrantReqVo reqVo) {

        /* 删除原本角色资源关系 */
        QueryWrapper<RoleResource> roleResourceQuery = new QueryWrapper<>();
        roleResourceQuery.eq("role_id", reqVo.roleId());
        roleResourceMapper.delete(roleResourceQuery);

        String[] split = reqVo.resourceIds().split(",");
        /* 重新添加绑定关系 */
        for (int i = 0; i < split.length; i++) {
            Long resourceId = Long.parseLong(split[i]);

            RoleResource rr = new RoleResource();
            rr.setRoleId(reqVo.roleId());
            rr.setResourceId(resourceId);

            roleResourceMapper.insert(rr);
        }

    }

    public ResourceGrantInfoResVo grantInfo(ResourceGrantInfoReqVo reqVo) {
        QueryWrapper<RoleResource> roleResourceQuery = new QueryWrapper<>();
        roleResourceQuery.select("resource_id");
        roleResourceQuery.eq("role_id", reqVo.roleId());
        List<RoleResource> roleResources = roleResourceMapper.selectList(roleResourceQuery);
        List<Long> longs = roleResources.stream().map(RoleResource::getResourceId).toList();
        return new ResourceGrantInfoResVo(longs);
    }

    public List<ResourceMenuResVo> menu() {
        QueryWrapper<Resource> resourceQuery = Wrappers.query();
        resourceQuery.in("type", 0, 1);
        List<Resource> resources = resourceMapper.selectList(resourceQuery);

        Map<Long, ResourceMenuResVo> collect = resources
                .stream()
                .map(e -> new ResourceMenuResVo(e.getId(), e.getName(), StringUtils.hasText(e.getUrl())?1:0, e.getUrl(), e.getIcon(),StringUtils.hasText(e.getUrl())?"_iframe":null, e.getPid(), new ArrayList<>()))
                .collect(Collectors.toMap(ResourceMenuResVo::id, Function.identity()));

        List<ResourceMenuResVo> root = new ArrayList<>();

        for (ResourceMenuResVo value : collect.values()) {
            if(value.pid() == null){
                root.add(value);
            } else {
                ResourceMenuResVo testPojo = collect.get(value.pid());
                if(testPojo == null){
                    root.add(value);
                } else {
                    testPojo.children().add(value);
                }
            }
        }

        return root;
    }
}
