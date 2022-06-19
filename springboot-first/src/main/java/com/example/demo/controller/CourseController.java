package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Mapper.CourseMapper;
import com.example.demo.common.Result;
import com.example.demo.entity.Course;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;

@RestController
@RequestMapping("/course")
public class CourseController {

                                            @Resource
    CourseMapper courseMapper;

    @PostMapping
    public Result<?> save(@RequestBody Course course){
        courseMapper.insert(course);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Course course){
        courseMapper.updateById(course);
        return Result.success();
    }

    @DeleteMapping("/{cid}")
    public Result<?> delete(@PathVariable Long cid){
        courseMapper.deleteById(cid);
        return Result.success();
    }


    @GetMapping
    public Result<?> findPage(@RequestParam (defaultValue = "1") Integer pageNumber,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "")String cname,
                              @RequestParam String cid,
                              @RequestParam String tid){
       LambdaQueryWrapper<Course> wrapper =  Wrappers.<Course>lambdaQuery();
       if(StrUtil.isNotBlank(cname)){
           wrapper.like(Course::getCname,cname);
       }
        if(StrUtil.isNotEmpty(cid)){
            wrapper.like(Course::getCid,cid);
        }
        if(StrUtil.isNotEmpty(tid)){
            wrapper.like(Course::getTeacherId,tid);
        }
        return Result.success(courseMapper.selectPage(new Page<>(pageNumber,pageSize), wrapper));
    }
}
