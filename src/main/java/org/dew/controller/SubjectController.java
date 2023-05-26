package org.dew.controller;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import org.dew.mapper.SubjectMapper;
import org.dew.pojo.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "课程信息接口")
@CrossOrigin("*")
@SuppressWarnings("all")
@RestController
public class SubjectController {
    Gson gson = new Gson();
    @Autowired
    private SubjectMapper subjectMapper;

    // 查询课程
    @GetMapping("/subjects")
    public String getSubject() {
        List<Subject> subjects = subjectMapper.selectList(null);
        return gson.toJson(subjects);
    }
    // 添加课程
    @PostMapping("/add_subject")
    public void addSubject(@RequestBody Subject subject) {
        subjectMapper.insert(subject);
    }

    // 删除课程
    @PostMapping("/delete_subject")
    public void deleteSubject(@RequestBody Subject subject) {
        subjectMapper.deleteById(subject);
    }

    // 修改课程信息
    @PostMapping("/update_subject")
    public void updateSubject(@RequestBody Subject subject) {
        subjectMapper.updateById(subject);
    }



}
