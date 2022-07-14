package com.makaix.qiandao.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.makaix.qiandao.utils.db.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Comment("资源")
@TableName("t_resource")
public class Resource extends BaseEntity {

    @Comment("权限名")
    private String name;

    @Comment("权限类型 1是菜单 2是接口")
    private Integer type;

    @Comment("权限地址")
    private String url;

    @Comment("权限描述")
    private String description;

    @Comment("权限图片")
    private String iconCls;

    @Comment("权限顺序")
    private Integer seq;

    @Comment("新窗口打开")
    private String target;
}
