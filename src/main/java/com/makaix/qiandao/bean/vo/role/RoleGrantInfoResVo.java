package com.makaix.qiandao.bean.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;

public record RoleGrantInfoResVo(@Schema(example = "用户ID") Long userId,
                                 @Schema(example = "角色ID") Long roleId) {
}
