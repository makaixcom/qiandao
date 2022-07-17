package com.makaix.qiandao.bean.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record RoleSelectResVo(@Schema(example = "分页数据") List<RoleSelectDataResVo> data) {

    public record RoleSelectDataResVo(@Schema(example = "文本") String text,
                                      @Schema(example = "值") Long value){

    }
}
