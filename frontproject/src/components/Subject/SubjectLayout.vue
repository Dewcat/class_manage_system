<template>
  
    <el-container style="height: 900px; border: 1px solid #eee">
      <el-container style="height:10%">
        
        <el-header style="text-align:left;front-size:30px;height:100%">
          <SubjectHeader></SubjectHeader>
        </el-header>
  
        <!--组件渲染-->
        <!--获取信息-->
        <div class="text-center"><el-button type="success" @click="getSubjects">点击获取课程信息</el-button>
          <el-button type="primary" @click="ifnologedin">点击添加课程</el-button>
        </div>
  
        <!--添加信息-->
  
  
        <el-dialog v-model="dialogVisible1" title="添加信息" width="30%" :before-close="handleClose">
          <div><span>添加课程信息</span></div>
          <div><span>课程编号： </span><input type="text" v-model="newsubject.number"></div>
          <div><span>课程名称： </span><input type="text" v-model="newsubject.name"></div>
          <div><span>授课老师： </span><input type="text" v-model="newsubject.teacher"></div>
          <div><span>授课教室： </span><input type="text" v-model="newsubject.classroom"></div>
  
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogVisible1 = false">取消</el-button>
              <!--是否确认添加-->
              <el-button type="primary" @click="addsubject(); dialogVisible2 = true">确认添加 </el-button>
              <el-dialog v-model="dialogVisible2" width="30%" :before-close="handleClose">
                <span>添加成功！</span>
                <template #footer>
                  <span class="dialog-footer">
                    <el-button type="primary" @click="dialogVisible2 = false">确定</el-button>
                  </span>
                </template>
              </el-dialog>
  
            </span>
          </template>
        </el-dialog>
  
        <table class="table caption-top table-hover table-striple">
  
          <!--信息表头-->
  
  
          <div class="table-responsive">
            <table class="table table-bordered caption-top table-hover table-striple" style="table-layout: auto">
              <thead>
                <tr>
                  <th scope="col">id</th>
                  <th scope="col">课程编号 </th>
                  <th scope="col">课程名称 </th>
                  <th scope="col">授课老师 </th>
                  <th scope="col">授课教室 </th>
                  <th scope="col">更新信息 </th>
                  <th scope="col">删除信息 </th>
                </tr>
              </thead>
            </table>
  
            <tbody>
              <Subject v-for="sub in subject_for_page" :key="sub.id" :subject="sub"></Subject>
            </tbody>
          </div>
        </table>
        <div class="text-center">
          <el-button-group>
            <el-button type="primary" @click="pre_page">上一页</el-button>
            <el-button type="primary" @click="next_page">下一页</el-button>
          </el-button-group>
  
        </div>
      </el-container>
    </el-container>
  </template>
  
  <style>
  .el-aside {
    color: #333;
  }
  </style>
  
  <script>
  import axios from 'axios';
  import Aside from '@/components/Aside.vue';
  import SubjectHeader from '@/components/Subject/SubjectHeader.vue';
  import Subject from '@/components/Subject/Subject.vue';
  
  export default {
    name: "TeacherLayout",
    components: {
      SubjectHeader,
      Aside,
      Subject,
    },
    
    data() {
      return {
        page: 1,
        subject:[],
        dialogVisible1: false,
        dialogVisible2: false,
        border: true,
        dialogVisibleregistersuccess: false,
        newsubject:{
            number: "",
            name: "",
            teacher: "",
            classroom: "",

        },
  
      }
    },
  
    methods: {
      getSubjects(){
        if (sessionStorage.getItem("isLogedin") == "true") {
          axios({
            url: 'http://localhost:8080/subjects',
            method: 'GET',
          }).then(res => {
            console.log(res.data);
            this.subject = res.data;
  
          })
        }
        else {
          this.$alert("请先登录后再查看");
        }
      },
      addsubject() {
        axios({
          url: "http://localhost:8080/add_subject",
          method: "POST",
          data: this.newsubject,
  
        })
        this.dialogVisible2 = false;
  
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => { });
      },
      ifnologedin() {
        if (sessionStorage.getItem("isLogedin") == "true") {
          this.dialogVisible1 = true;
        }
        else {
          this.$alert("请先登录后再添加");
          this.dialogVisible1 = false;
        }
  
      },
      addstudent() {
        axios({
          url: "http://localhost:8080/add_student",
          method: "POST",
          data: this.newstudent,
  
        })
        this.dialogVisible2 = false;
  
      },
      pre_page() {
        this.page -= 1;
  
      },
      next_page() {
        this.page += 1;
  
      }
    },
    computed: {
      subject_for_page() {
        return this.subject.slice(this.page * 10 - 10, this.page * 10);
  
      }
    },
  
  };
  
  
  </script>