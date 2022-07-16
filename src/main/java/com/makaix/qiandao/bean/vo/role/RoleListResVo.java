package com.makaix.qiandao.bean.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public record RoleListResVo(@Schema(example = "总记录数") Long total,
                            @Schema(example = "分页数据") List<RoleListDataResVo> data) {

    public record RoleListDataResVo(@Schema(example = "ID") Long id,
                                    @Schema(example = "角色名称") String name,
                                    @Schema(example = "角色编号") String code,
                                    @Schema(example = "角色描述") String remark,
                                    @Schema(example = "角色排序") Integer seq,
                                    @Schema(example = "创建时间") LocalDateTime createDateTime,
                                    @Schema(example = "修改时间") LocalDateTime modifyDateTime){}
}
