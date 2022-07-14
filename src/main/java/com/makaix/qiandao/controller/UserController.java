package com.makaix.qiandao.controller;

import com.makaix.qiandao.bean.vo.base.BaseResVo;
import com.makaix.qiandao.bean.vo.user.UserAddReqVo;
import com.makaix.qiandao.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户模块")
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Operation(summary = "用户新增")
    @GetMapping("/add")
    public BaseResVo add(UserAddReqVo reqVo) {
        userService.add(reqVo);
        return BaseResVo.ok();
    }
}
