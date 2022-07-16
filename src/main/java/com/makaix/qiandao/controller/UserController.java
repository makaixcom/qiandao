package com.makaix.qiandao.controller;

import com.makaix.qiandao.bean.vo.base.BaseIdReqVo;
import com.makaix.qiandao.bean.vo.user.*;
import com.makaix.qiandao.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户")
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Operation(summary = "用户列表")
    @GetMapping("/list")
    public UserListResVo list(UserListReqVo reqVo) {
        return userService.list(reqVo);
    }

    @Operation(summary = "用户新增")
    @PostMapping("/add")
    public void add(@RequestBody UserAddReqVo reqVo) {
        userService.add(reqVo);
    }

    @Operation(summary = "用户查看")
    @GetMapping("/get")
    public UserGetResVo get(BaseIdReqVo reqVo) {
        return userService.get(reqVo);
    }

    @Operation(summary = "用户编辑")
    @PutMapping("/edit")
    public void edit(@RequestBody UserEditReqVo reqVo) {
        userService.edit(reqVo);
    }

    @Operation(summary = "用户删除")
    @DeleteMapping("/delete")
    public void delete(BaseIdReqVo reqVo) {
        userService.delete(reqVo);
    }

    @Operation(summary = "重置密码")
    @PutMapping("/resetPwd")
    public void resetPwd(@RequestBody UserResetPwdReqVo reqVo) {
        userService.resetPwd(reqVo);
    }


}
