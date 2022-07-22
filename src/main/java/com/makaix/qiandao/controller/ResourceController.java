package com.makaix.qiandao.controller;

import com.makaix.qiandao.bean.vo.base.BaseIdReqVo;
import com.makaix.qiandao.bean.vo.resource.*;
import com.makaix.qiandao.service.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "资源")
@AllArgsConstructor
@RestController
@RequestMapping("/resource")
public class ResourceController {

    private ResourceService resourceService;

    @Operation(summary = "资源列表")
    @GetMapping("/list")
    public ResourceListResVo list(){
        return resourceService.list();
    }

    @Operation(summary = "资源新增")
    @PostMapping("/add")
    public void add(@RequestBody ResourceAddReqVo reqVo) {
        resourceService.add(reqVo);
    }

    @Operation(summary = "资源查看")
    @GetMapping("/get")
    public ResourceGetResVo get(BaseIdReqVo reqVo) {
        return resourceService.get(reqVo);
    }

    @Operation(summary = "资源编辑")
    @PutMapping("/edit")
    public void edit(@RequestBody ResourceEditReqVo reqVo) {
        resourceService.edit(reqVo);
    }

    @Operation(summary = "资源删除")
    @DeleteMapping("/delete")
    public void delete(BaseIdReqVo reqVo) {
        resourceService.delete(reqVo);
    }

    @Operation(summary = "资源授权")
    @PutMapping("/grant")
    public void grant(@RequestBody ResourceGrantReqVo reqVo) {
        resourceService.grant(reqVo);
    }
    @Operation(summary = "资源授权获得")
    @GetMapping("/grantInfo")
    public ResourceGrantInfoResVo grantInfo(ResourceGrantInfoReqVo reqVo) {
        return resourceService.grantInfo(reqVo);
    }

    @Operation(summary = "用户菜单")
    @GetMapping("/menu")
    public List<ResourceMenuResVo> menu() {
        return resourceService.menu();
    }

}
