package com.makaix.qiandao.controller;

import com.makaix.qiandao.bean.vo.base.BaseIdReqVo;
import com.makaix.qiandao.bean.vo.role.*;
import com.makaix.qiandao.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "角色")
@AllArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    @Operation(summary = "角色列表")
    @GetMapping("/list")
    public RoleListResVo list(RoleListReqVo reqVo){
        return roleService.list(reqVo);
    }

    @Operation(summary = "角色新增")
    @PostMapping("/add")
    public void add(@RequestBody RoleAddReqVo reqVo) {
        roleService.add(reqVo);
    }

    @Operation(summary = "角色查看")
    @GetMapping("/get")
    public RoleGetResVo get(BaseIdReqVo reqVo) {
        return roleService.get(reqVo);
    }

    @Operation(summary = "角色编辑")
    @PutMapping("/edit")
    public void edit(@RequestBody RoleEditReqVo reqVo) {
        roleService.edit(reqVo);
    }

    @Operation(summary = "角色删除")
    @DeleteMapping("/delete")
    public void delete(BaseIdReqVo reqVo) {
        roleService.delete(reqVo);
    }
}
