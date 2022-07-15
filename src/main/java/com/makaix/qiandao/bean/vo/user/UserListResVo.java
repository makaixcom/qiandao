package com.makaix.qiandao.bean.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public record UserListResVo(@Schema(example = "总记录数") Long total,
                            @Schema(example = "分页数据") List<UserListDataResVo> data) {

    public record UserListDataResVo(@Schema(example = "ID") Long id,
                                    @Schema(example = "用户名") String username,
                                    @Schema(example = "手机号") String mobile,
                                    @Schema(example = "创建时间") LocalDateTime createDateTime,
                                    @Schema(example = "修改时间") LocalDateTime modifyDateTime){

    }

}
