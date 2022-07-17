package com.makaix.qiandao.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.makaix.qiandao.bean.entity.Resource;
import com.makaix.qiandao.bean.vo.base.BaseIdReqVo;
import com.makaix.qiandao.bean.vo.resource.ResourceAddReqVo;
import com.makaix.qiandao.bean.vo.resource.ResourceEditReqVo;
import com.makaix.qiandao.bean.vo.resource.ResourceGetResVo;
import com.makaix.qiandao.bean.vo.resource.ResourceListResVo;
import com.makaix.qiandao.mapper.ResourceMapper;
import com.makaix.qiandao.utils.other.MakaixBeanUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ResourceService {

    private ResourceMapper resourceMapper;

    public ResourceListResVo list() {
        QueryWrapper<Resource> userQuery = Wrappers.query();
        userQuery.orderByAsc("seq");
        List<Resource> resources = resourceMapper.selectList(userQuery);

        List<ResourceListResVo.ResourceListDataResVo> collect = resources
                .stream()
                .map(e -> new ResourceListResVo.ResourceListDataResVo(e.getId(), e.getName(), e.getType(), e.getUrl(), e.getRemark(), e.getIcon(), e.getSeq(), e.getTarget(), e.getPid(), e.getCreateDateTime(), e.getModifyDateTime()))
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
        return new ResourceGetResVo(e.getId(), e.getName(), e.getType(), e.getUrl(), e.getRemark(), e.getIcon(), e.getSeq(), e.getTarget(), e.getPid());
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

}
