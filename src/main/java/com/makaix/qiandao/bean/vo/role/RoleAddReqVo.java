package com.makaix.qiandao.bean.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;

public record RoleAddReqVo(@Schema(example = "角色名称") String name,
                           @Schema(example = "角色编号") String code,
                           @Schema(example = "角色描述") String remark,
                           @Schema(example = "角色排序") Integer seq) {
}
