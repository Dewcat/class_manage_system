package org.dew.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import org.dew.mapper.StudentMapper;
import org.dew.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Api(tags = "学生信息接口")
@CrossOrigin("*")
@SuppressWarnings("all")
@RestController
public class StudentController {
    Gson gson = new Gson();
    @Autowired
    private StudentMapper studentMapper;

    // 查询学生
    @GetMapping("/students")
    public String getStudent() {
        List<Student> students = studentMapper.selectList(null);
        return gson.toJson(students);
    }
    // 获取学生总数
    @GetMapping("/get_students_count")
    public long getStudentCount() {
        Long count = studentMapper.selectCount(null);
        return count;
    }
    // 添加学生
    @PostMapping("/add_student")
    public void addStudent(@RequestBody Student student) {
        studentMapper.insert(student);
    }

    // 删除学生
    @PostMapping("/delete_student")
    public void deleteStudent(@RequestBody Student student) {
        studentMapper.deleteById(student);
    }

    // 修改学生信息
    @PostMapping("/update_student")
    public void updateStudent(@RequestBody Student student) {
        studentMapper.updateById(student);
    }



}
