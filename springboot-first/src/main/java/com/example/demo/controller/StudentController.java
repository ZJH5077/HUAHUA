package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Mapper.CourseMapper;
import com.example.demo.Mapper.StudentMapper;
import com.example.demo.common.Result;
import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    StudentMapper studentMapper;

    @PostMapping
    public Result<?> save(@RequestBody Student student){
        studentMapper.insert(student);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Student student){
        studentMapper.updateById(student);
        return Result.success();
    }

    @DeleteMapping("/{sid}")
    public Result<?> delete(@PathVariable Long sid){
        studentMapper.deleteById(sid);
        return Result.success();
    }


    @GetMapping
    public Result<?> findPage(@RequestParam (defaultValue = "1") Integer pageNumber,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String sname,
                              @RequestParam String sid,
                              @RequestParam String link){
        LambdaQueryWrapper<Student> wrapper =  Wrappers.<Student>lambdaQuery();
        if(StrUtil.isNotBlank(sname)){
            wrapper.like(Student::getSname,sname);
        }
        if(StrUtil.isNotEmpty(sid)){
            wrapper.like(Student::getSid,sid);
        }
        if(StrUtil.isNotEmpty(link)){
            wrapper.like(Student::getLink,link);
        }
        return Result.success(studentMapper.selectPage(new Page<>(pageNumber,pageSize), wrapper));
    }
}
