package com.makaix.qiandao.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.makaix.qiandao.utils.db.Comment;
import lombok.Getter;
import lombok.Setter;

//@Accessors(chain = true)
@Getter
@Setter
@Comment("用户")
@TableName("t_user")
public class User extends BaseEntity {

    @Comment("用户名")
    private String username;

    @Comment("密码")
    private String password;

    @Comment("手机号")
    private String mobile;

}
