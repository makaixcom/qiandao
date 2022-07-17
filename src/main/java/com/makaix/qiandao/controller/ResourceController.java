package com.makaix.qiandao.controller;

import com.makaix.qiandao.bean.vo.base.BaseIdReqVo;
import com.makaix.qiandao.bean.vo.resource.*;
import com.makaix.qiandao.service.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "角色")
@AllArgsConstructor
@RestController
@RequestMapping("/resource")
public class ResourceController {

    private ResourceService resourceService;

    @Operation(summary = "角色列表")
    @GetMapping("/list")
    public ResourceListResVo list(){
        return resourceService.list();
    }

    @Operation(summary = "角色新增")
    @PostMapping("/add")
    public void add(@RequestBody ResourceAddReqVo reqVo) {
        resourceService.add(reqVo);
    }

    @Operation(summary = "角色查看")
    @GetMapping("/get")
    public ResourceGetResVo get(BaseIdReqVo reqVo) {
        return resourceService.get(reqVo);
    }

    @Operation(summary = "角色编辑")
    @PutMapping("/edit")
    public void edit(@RequestBody ResourceEditReqVo reqVo) {
        resourceService.edit(reqVo);
    }

    @Operation(summary = "角色删除")
    @DeleteMapping("/delete")
    public void delete(BaseIdReqVo reqVo) {
        resourceService.delete(reqVo);
    }

}
