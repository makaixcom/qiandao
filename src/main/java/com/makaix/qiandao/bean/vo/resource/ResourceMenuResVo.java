package com.makaix.qiandao.bean.vo.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;


public record ResourceMenuResVo(@Schema(example = "ID") @JsonSerialize(using = ToStringSerializer.class) Long id,
                                @Schema(example = "资源名称") String title,
                                @Schema(example = "权限类型 0是菜单目录 1是菜单叶子节点 2是接口") Integer type,
                                @Schema(example = "权限地址") String href,
                                @Schema(example = "权限图标") String icon,
                                @Schema(example = "新窗口打开") @JsonInclude(JsonInclude.Include.NON_NULL) String openType,
                                @Schema(example = "父ID") @JsonIgnore  Long pid,
                                @Schema(example = "父ID") @JsonInclude(JsonInclude.Include.NON_EMPTY) List<ResourceMenuResVo> children) {
}
