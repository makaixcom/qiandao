package com.makaix.qiandao.bean.vo.resource;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public record ResourceListResVo(@Schema(example = "分页数据") List<ResourceListDataResVo> data) {

    public record ResourceListDataResVo(@Schema(example = "ID") Long id,
                                        @Schema(example = "资源名称") String name,
                                        @Schema(example = "权限类型 1是菜单 2是接口") Integer type,
                                        @Schema(example = "权限地址") String url,
                                        @Schema(example = "权限描述") String remark,
                                        @Schema(example = "权限图标") String icon,
                                        @Schema(example = "权限顺序") Integer seq,
                                        @Schema(example = "新窗口打开") String target,
                                        @Schema(example = "父ID") Long pid,
                                        @Schema(example = "创建时间") LocalDateTime createDateTime,
                                        @Schema(example = "修改时间") LocalDateTime modifyDateTime){}
}
