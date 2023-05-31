<template>
  <div id="app">
    <div class="input-group mb-3 input-center">
  <span class="input-group-text" id="basic-addon1">@</span>
  <input type="text" class="form-control" placeholder="qindarkstone" aria-label="Username" aria-describedby="basic-addon1">
</div>
  <el-container style="height: 900px; border: 1px solid #eee">
    <Aside></Aside>
      <router-view></router-view>
    </el-container>

</div>

</template>

<script>

import axios from 'axios'
import Aside from '@/components/Aside.vue';
import Student from '@/components/Student/Student.vue';
import StudentLayout from '@/components/Student/StudentLayout.vue';
import TeacherLayout from '@/components/Teacher/TeacherLayout.vue';
import SubjectLayout from '@/components/Subject/SubjectLayout.vue';
import ScoreLayout from '@/components/Score/ScoreLayout.vue';

export default {
  name: 'App',
  components: {
    Student,
    Aside,
    StudentLayout,
    TeacherLayout,
    ScoreLayout,
    SubjectLayout,
  
  },/*
  data(){
    return{
      page:1,
      student:[],
      dialogVisible1: false,
      dialogVisible2: false,
      dialogVisiblelogin:false,
      dialogVisibleregister:false,
      dialogVisibleifclear:false,
      dialogVisibleclear:false,
      dialogVisibleregistersuccess:false,
      user_for_login:{
        username:"",
        password:"",
      },
      user_for_register:{
        username:"",
        password:"",
      },
      newstudent:{
        number:"",
        name:"",
        age:"",
        sex:"",
        banji:"",
        
      }
    
     }
  },

  methods:{
    getStudents(){
      if(sessionStorage.getItem("isLogedin")=="true"){
        axios({
        url:'http://localhost:8080/students',
        method:'GET',
      }).then(res=>{
        console.log(res.data);
        this.student=res.data;

      })
      }
      else{
        this.$alert("请先登录后再查看");
      }
     
    },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      ifnologedin(){
        if(sessionStorage.getItem("isLogedin")=="true"){
          this.dialogVisible1 = true;
      }
      else{
        this.$alert("请先登录后再添加");
        this.dialogVisible1 = false;
      }
      
      },
      addstudent(){
          axios({
          url:"http://localhost:8080/add_student",
          method:"POST",
          data:this.newstudent,
         
        })
        this.dialogVisible2 = false;
      
    },
    register(){
      axios({
          url:"http://localhost:8080/register",
          method:"POST",
          data:this.user_for_register,
      })
      
    },
    login(){
      axios({
        url:"http://localhost:8080/login",
        method:"POST",
        data:this.user_for_login,  
      }).then(res=>{
          console.log(res.data);
          if(res.data =="1"){
            sessionStorage.setItem("isLogedin", "true");
          }
          else{
            alert("用户名或密码错误");
          }; 
      })
      this.dialogVisiblelogin = false;
    },
    clear(){
      sessionStorage.clear("isLogedin");
      location.reload();

    },
      pre_page(){
        this.page-=1;

      },
      next_page(){
        this.page+=1;
        
      }
    },
    computed: {
      student_for_page() {
        return this.student.slice(this.page*5-5,this.page*5);

    }
  }  
   */
  }
   
</script>


<style>


</style>

