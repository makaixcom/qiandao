package com.makaix.qiandao.bean.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserListReqVo(@Schema(example = "页码") Integer page,
                            @Schema(example = "每页记录数")Integer size,
                            @Schema(example = "用户名")String username,
                            @Schema(example = "手机号")String mobile) {

    public UserListReqVo {
        page = ( page == null ? 1: page);
        size = ( size == null ? 10: size);
    }

}
