package com.makaix.qiandao.bean.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserGetResVo (@Schema(example = "ID") Long id,
                            @Schema(example = "用户名") String username,
                            @Schema(example = "手机号") String mobile) {
}
