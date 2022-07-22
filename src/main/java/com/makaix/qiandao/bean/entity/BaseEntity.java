package com.makaix.qiandao.bean.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.makaix.qiandao.utils.db.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    @Comment("主键ID")
    private Long id;

    @Comment("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDateTime;

    @Comment("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @OrderBy
    private LocalDateTime modifyDateTime;

    @Comment("是否删除")
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;


}
