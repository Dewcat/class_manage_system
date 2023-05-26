package org.dew.controller;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import org.dew.mapper.TeacherMapper;
import org.dew.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "教师信息接口")
@CrossOrigin("*")
@SuppressWarnings("all")
@RestController
public class TeacherController {
    Gson gson = new Gson();
    @Autowired
    private TeacherMapper teacherMapper;

    // 查询教师
    @GetMapping("/teachers")
    public String getTeacher() {
        List<Teacher> teachers = teacherMapper.selectList(null);
        return gson.toJson(teachers);
    }
    // 添加教师
    @PostMapping("/add_teacher")
    public void addTeacher(@RequestBody Teacher teacher) {
        teacherMapper.insert(teacher);
    }

    // 删除教师
    @PostMapping("/delete_teacher")
    public void deleteTeacher(@RequestBody Teacher teacher) {
        teacherMapper.deleteById(teacher);
    }

    // 修改教师信息
    @PostMapping("/update_teacher")
    public void updateTeacher(@RequestBody Teacher teacher) {
        teacherMapper.updateById(teacher);
    }



}
