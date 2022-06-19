package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("course")  //shujuku 表名
@Data
public class Course implements Serializable {
    @TableId(type = IdType.AUTO)
    private int cid;
    private String cname;
    private int teacherId;
    private int time;
    private int score;
    private String classtime;
    private String address;
    private String examtime;

    public int getCid() {
        return cid;
    }

    public String getCname() {
        return cname;
    }

    public int getTeacherId() {
        return teacherId;
    }
}
