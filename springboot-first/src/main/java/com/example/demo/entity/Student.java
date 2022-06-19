package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("student")
public class Student  {
    @TableId("sid")
    private int sid;
    private String sname;
    private String sex;
    private String birthday;
    private int score;
    private int link;

    public String getSname() {
        return sname;
    }

}
