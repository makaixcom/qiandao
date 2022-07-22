package com.makaix.qiandao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.makaix.qiandao.bean.entity.Resource;
import com.makaix.qiandao.bean.pojo.resource.ResourcePojo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ResourceMapper extends BaseMapper<Resource> {

    @Select("""
            select re.url ,re.method, r.`code`  from t_role_resource rr 
            left join t_role r on rr.role_id = r.id 
            left join t_resource re on rr.resource_id = re.id 
            """)
    List<ResourcePojo> resourceRole();
}
