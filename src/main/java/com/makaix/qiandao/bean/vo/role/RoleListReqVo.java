package com.makaix.qiandao.bean.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;

public record RoleListReqVo(@Schema(example = "页码") Integer page,
                            @Schema(example = "每页记录数") Integer size,
                            @Schema(example = "角色名称") String name,
                            @Schema(example = "角色编号") String code) {

    public RoleListReqVo{
        page = ( page == null ? 1: page);
        size = ( size == null ? 10: size);
    }
}
