package com.example.demo.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Score;

public interface scoreService {
    public Page<Score> selectChoicePage(Page<Score> page);
}
