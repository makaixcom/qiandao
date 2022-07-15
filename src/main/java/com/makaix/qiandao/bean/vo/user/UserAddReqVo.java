package com.makaix.qiandao.bean.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserAddReqVo(@Schema(example = "用户名") String username,
                           @Schema(example = "密码") String password,
                           @Schema(example = "手机号") String mobile) {

}
