package com.makaix.qiandao.bean.vo.resource;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ResourceGrantReqVo(@Schema(example = "角色ID") Long roleId,
                                 @Schema(example = "资源ID列表") List<Long> resourceIds) {


}
