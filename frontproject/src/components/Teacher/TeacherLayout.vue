<template>
  
  <el-container style="height: 900px; border: 1px solid #eee">
    <el-container style="height:10%">
      
      <el-header style="text-align:left;front-size:30px;height:100%">
        <TeacherHeader></TeacherHeader>
      </el-header>

      <!--组件渲染-->
      <!--获取信息-->
      <div class="text-center"><el-button type="success" @click="getTeachers">点击获取教师信息</el-button>
        <el-button type="primary" @click="ifnologedin">点击添加教师信息</el-button>
      </div>

      <!--添加信息-->


      <el-dialog v-model="dialogVisible1" title="添加信息" width="30%" :before-close="handleClose">
        <div><span>添加教师信息</span></div>
        <div><span>教职工号： </span><input type="text" v-model="newteacher.number"></div>
        <div><span>姓名： </span><input type="text" v-model="newteacher.name"></div>
        <div><span>年龄： </span><input type="text" v-model="newteacher.age"></div>
        <div><span>性别： </span><input type="text" v-model="newteacher.sex"></div>

        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible1 = false">取消</el-button>
            <!--是否确认添加-->
            <el-button type="primary" @click="addteacher(); dialogVisible2 = true">确认添加 </el-button>
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
                <th scope="col">教职工号 </th>
                <th scope="col">姓名 </th>
                <th scope="col">年龄 </th>
                <th scope="col">性别 </th>
                <th scope="col">更新信息 </th>
                <th scope="col">删除信息 </th>
              </tr>
            </thead>
          </table>

          <tbody>
            <Teacher v-for="tea in teacher_for_page" :key="tea.id" :teacher="tea"></Teacher>
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
import TeacherHeader from '@/components/Teacher/TeacherHeader.vue';
import Teacher from '@/components/Teacher/Teacher.vue';

export default {
  name: "TeacherLayout",
  components: {
    Teacher,
    Aside,
    TeacherHeader,
  },
  
  data() {
    return {
      page: 1,
      teacher:[],
      dialogVisible1: false,
      dialogVisible2: false,
      border: true,
      dialogVisibleregistersuccess: false,
      newteacher:{
        number: "",
        name: "",
        age: "",
        sex: "",
        
      },
    }
  },

  methods: {
    getTeachers(){
      if (sessionStorage.getItem("isLogedin") == "true") {
        axios({
          url: 'http://localhost:8080/teachers',
          method: 'GET',
        }).then(res => {
          console.log(res.data);
          this.teacher = res.data;

        })
      }
      else {
        this.$alert("请先登录后再查看");
      }
    },
    addteacher() {
      axios({
        url: "http://localhost:8080/add_teacher",
        method: "POST",
        data: this.newteacher,

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
    pre_page() {
      this.page -= 1;

    },
    next_page() {
      this.page += 1;

    }
  },
  computed: {
    teacher_for_page() {
      return this.teacher.slice(this.page * 10 - 10, this.page * 10);

    }
  },

};


</script>