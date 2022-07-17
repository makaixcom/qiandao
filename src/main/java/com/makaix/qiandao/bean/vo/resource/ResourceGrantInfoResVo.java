package com.makaix.qiandao.bean.vo.resource;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ResourceGrantInfoResVo(@Schema(example = "资源ID集合") List<Long> roleIds) {
}
