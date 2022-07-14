package com.makaix.qiandao.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.makaix.qiandao.utils.db.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Comment("用户-角色-关联表")
@TableName("t_user_role")
public class UserRole extends BaseEntity {

    @Comment("用户ID")
    private Long userId;

    @Comment("角色ID")
    private Long roleId;

}
