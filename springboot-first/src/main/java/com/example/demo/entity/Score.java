package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("score")
public class Score {
    @TableId("sid")
       private int sid;
       private int cid;
       private int us;
       private int es;
       private int allScore;

       @TableField(exist = false)
       private Student student;

       @TableField(exist = false)
       private Course course;
       @TableField(exist = false)
       private Teacher teacher;
}
