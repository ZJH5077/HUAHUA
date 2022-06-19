package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("teacher")
public class Teacher  {
    @TableId("tid")
    private int tid;
    private String tname;
    private String sex;
    private String birthday;
    private int link;
    private String job;
    private String object;
    private String teach;
}
