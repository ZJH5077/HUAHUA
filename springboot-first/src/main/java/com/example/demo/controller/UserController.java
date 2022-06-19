package com.example.demo.controller;

import com.example.demo.common.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public Result<?> save(@RequestParam String username,@RequestParam String password){
        if(username.equals("3119005077")&&password.equals("123456")){
            return Result.success();
        }
        return Result.error("-1","用户名或密码错误");
    }


}
