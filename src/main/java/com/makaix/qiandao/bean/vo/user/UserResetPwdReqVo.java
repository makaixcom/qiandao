package com.makaix.qiandao.bean.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserResetPwdReqVo(@Schema(example = "ID") Long id,
                                @Schema(example = "密码") String pwd) {
}
