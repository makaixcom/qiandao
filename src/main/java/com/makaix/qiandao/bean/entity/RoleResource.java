package com.makaix.qiandao.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.makaix.qiandao.utils.db.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Comment("角色-资源-关联表")
@TableName("t_role_resource")
public class RoleResource extends BaseEntity {

    @Comment("角色ID")
    private Long roleId;

    @Comment("资源ID")
    private Long resourceId;


}
