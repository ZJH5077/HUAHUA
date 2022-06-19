package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Course;
import com.example.demo.entity.Score;
import org.apache.ibatis.annotations.Param;

public interface ScoreMapper extends BaseMapper<Score> {
    public Page<Score> selectChoiceLession(Page<Score> page,@Param("sid") String sid,
                                           @Param("tname") String tname,@Param("cname") String cname,
                                            @Param("sname") String sname);

    public void delete(@Param("sid") Long sid,@Param("cid") Long cid);

    public void updateScore(@Param("sid") Long sid,@Param("cid") Long cid,@Param("es") int es
    ,@Param("us") int us,@Param("allscore") int allscore);

    public Page<Score> selectGrades(Page<Score> page,@Param("cid") String cid,@Param("cname") String cname,
                                    @Param("tname") String tname,@Param("grades") String grades);
}
