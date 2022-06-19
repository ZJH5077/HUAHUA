package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Mapper.DepartmentMapper;
import com.example.demo.Mapper.TeacherMapper;
import com.example.demo.common.Result;
import com.example.demo.entity.Department;
import com.example.demo.entity.Teacher;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    DepartmentMapper departmentMapper;

    @PostMapping
    public Result<?> save(@RequestBody Department department ){
        departmentMapper.insert(department);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Department department){
        departmentMapper.updateById(department);
        return Result.success();
    }

    @DeleteMapping("/{did}")
    public Result<?> delete(@PathVariable Long did){
        departmentMapper.deleteById(did);
        return Result.success();
    }


    @GetMapping
    public Result<?> findPage(@RequestParam (defaultValue = "1") Integer pageNumber,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String lname,
                              @RequestParam String did){
        LambdaQueryWrapper<Department> wrapper =  Wrappers.<Department>lambdaQuery();
        if(StrUtil.isNotBlank(lname)){
            wrapper.like(Department::getLname,lname);
        }
        if(StrUtil.isNotEmpty(did)){
            wrapper.like(Department::getDid,did);
        }
        return Result.success(departmentMapper.selectPage(new Page<>(pageNumber,pageSize), wrapper));
    }
}
