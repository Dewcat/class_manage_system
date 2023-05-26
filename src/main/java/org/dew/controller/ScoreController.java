package org.dew.controller;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import org.dew.mapper.ScoreMapper;
import org.dew.pojo.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "成绩信息接口")
@CrossOrigin("*")
@SuppressWarnings("all")
@RestController
public class ScoreController {
    Gson gson = new Gson();
    @Autowired
    private ScoreMapper scoreMapper;

    // 查询成绩
    @GetMapping("/scores")
    public String getScore() {
        List<Score> scores = scoreMapper.selectList(null);
        return gson.toJson(scores);
    }
    // 添加成绩
    @PostMapping("/add_score")
    public void addScore(@RequestBody Score score) {
        scoreMapper.insert(score);
    }

    // 删除成绩
    @PostMapping("/delete_score")
    public void deleteScore(@RequestBody Score score) {
        scoreMapper.deleteById(score);
    }

    // 修改成绩信息
    @PostMapping("/update_score")
    public void updateScore(@RequestBody Score score) {
        scoreMapper.updateById(score);
    }



}
