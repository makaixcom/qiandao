package com.makaix.qiandao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.makaix.qiandao.bean.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    @Select("""
            select r.id, r.`code` 
            from t_role r 
            left join t_user_role ur on r.id = ur.role_id 
            left join t_user u on ur.user_id = u.id
            where u.id = #{id}
            """)
    List<Role> selectRolesByUserId(@Param("id") Long id);
}
