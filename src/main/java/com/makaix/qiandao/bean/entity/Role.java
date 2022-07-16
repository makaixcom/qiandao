package com.makaix.qiandao.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.makaix.qiandao.utils.db.Comment;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
@Setter
@Comment("角色")
@TableName("t_role")
public class Role extends BaseEntity {

    @Comment("角色名")
    private String name;

    @Comment("角色编号")
    private String code;

    @Comment("角色描述")
    private String remark;

    @Comment("角色顺序，默认是1")
    private Integer seq;

}
