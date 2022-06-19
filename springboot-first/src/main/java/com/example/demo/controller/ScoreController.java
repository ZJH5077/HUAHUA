package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Mapper.DepartmentMapper;
import com.example.demo.Mapper.ScoreMapper;
import com.example.demo.Service.scoreService;
import com.example.demo.common.Result;
import com.example.demo.entity.Department;
import com.example.demo.entity.Score;
import com.example.demo.entity.Student;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/score")
public class ScoreController {
    @Resource
    ScoreMapper scoreMapper;
    scoreService scoreService;

    @PostMapping
    public Result<?> save(@RequestBody Score score){
        scoreMapper.insert(score);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Score score){
        Long sid = Long.valueOf(score.getSid());
        Long cid = Long.valueOf(score.getCid());
        int es = score.getEs();
        int us = score.getUs();
        int allscore = score.getAllScore();
        scoreMapper.updateScore(sid,cid,es,us,allscore);
        return Result.success();
    }

    @DeleteMapping("/{sid}/{cid}")
    public Result<?> delete(@PathVariable Long sid,@PathVariable Long cid){
        System.out.println(sid);
        System.out.println(cid);
        scoreMapper.delete(sid,cid);
        return Result.success();
    }


    @GetMapping
    public Result<?> findPage(@RequestParam (defaultValue = "1") Integer pageNumber,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String sid,
                              @RequestParam String tname,
                              @RequestParam String cname,
                              @RequestParam String sname){
        Page<Score> page = new Page<>(pageNumber,pageSize);
        return Result.success(scoreMapper.selectChoiceLession(page,sid,tname,cname,sname));
    }


    @GetMapping("/gggg")
    public Result<?> findshow(@RequestParam Integer pageNumber,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam String cid,
                              @RequestParam String cname,
                              @RequestParam String tname,
                              @RequestParam String grades) {
        Page<Score> page = new Page<>(pageNumber, pageSize);
        return Result.success(scoreMapper.selectGrades(page,cid,cname,tname,grades));
    }
}
