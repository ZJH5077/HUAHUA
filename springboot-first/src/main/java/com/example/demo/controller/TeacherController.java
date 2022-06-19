package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Mapper.StudentMapper;
import com.example.demo.Mapper.TeacherMapper;
import com.example.demo.common.Result;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    TeacherMapper teacherMapper;

    @PostMapping
    public Result<?> save(@RequestBody Teacher teacher){
        teacherMapper.insert(teacher);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Teacher teacher){
        teacherMapper.updateById(teacher);
        return Result.success();
    }

    @DeleteMapping("/{tid}")
    public Result<?> delete(@PathVariable Long tid){
        teacherMapper.deleteById(tid);
        return Result.success();
    }


    @GetMapping
    public Result<?> findPage(@RequestParam (defaultValue = "1") Integer pageNumber,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String tname,
                              @RequestParam String tid,
                              @RequestParam String link){
        LambdaQueryWrapper<Teacher> wrapper =  Wrappers.<Teacher>lambdaQuery();
        if(StrUtil.isNotBlank(tname)){
            wrapper.like(Teacher::getTname,tname);
        }
        if(StrUtil.isNotEmpty(tid)){
            wrapper.like(Teacher::getTid,tid);
        }
        if(StrUtil.isNotEmpty(link)){
            wrapper.like(Teacher::getLink,link);
        }
        return Result.success(teacherMapper.selectPage(new Page<>(pageNumber,pageSize), wrapper));
    }
}
