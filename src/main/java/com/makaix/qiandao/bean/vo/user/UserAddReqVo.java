package com.makaix.qiandao.bean.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserAddReqVo {

    @Schema( example = "用户名")
    private String username;

    @Schema( example = "密码")
    private String password;

    @Schema( example = "手机号")
    private String mobile;
}
