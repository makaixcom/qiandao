package com.makaix.qiandao.bean.vo.resource;

import io.swagger.v3.oas.annotations.media.Schema;

public record ResourceAddReqVo(@Schema(example = "资源名称") String name,
                               @Schema(example = "权限类型 1是菜单 2是接口") Integer type,
                               @Schema(example = "权限地址") String url,
                               @Schema(example = "权限描述") String remark,
                               @Schema(example = "权限图标") String icon,
                               @Schema(example = "权限顺序") Integer seq,
                               @Schema(example = "新窗口打开") String target,
                               @Schema(example = "父ID") Long pid) {
}
