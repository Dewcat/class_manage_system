<template>
  
    <el-container style="height: 900px; border: 1px solid #eee">
      <el-container style="height:10%">
        
        <el-header style="text-align:left;front-size:30px;height:100%">
          <ScoreHeader></ScoreHeader>
        </el-header>
  
        <!--组件渲染-->
        <!--获取信息-->
        <div class="text-center"><el-button type="success" @click="getScores">点击获取学生课程成绩</el-button>
          <el-button type="primary" @click="ifnologedin">点击添加学生成绩</el-button>
        </div>
  
        <!--添加信息-->
  
  
        <el-dialog v-model="dialogVisible1" title="添加信息" width="30%" :before-close="handleClose">
          <div><span>添加成绩信息</span></div>
          <div><span>学号： </span><input class="w-50 " type="text" v-model="newscore.number"></div>
          <div><span>姓名： </span><input class="w-50" type="text" v-model="newscore.name"></div>
          <div><span>计算机组成原理：</span><input class="w-50" type="text" v-model="newscore.jz"></div>
          <div><span>计算机网络： </span><input class="w-50" type="text" v-model="newscore.jw"></div>
          <div><span>操作系统： </span><input class="w-50" type="text" v-model="newscore.czxt"></div>
          <div><span>数据结构： </span><input class="w-50" type="text" v-model="newscore.sjjg"></div>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogVisible1 = false">取消</el-button>
              <!--是否确认添加-->
              <el-button type="primary" @click="addscore(); dialogVisible2 = true">确认添加 </el-button>
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
                  <th scope="col">学号 </th>
                  <th scope="col">姓名 </th>
                  <th scope="col">计算机组成原理 </th>
                  <th scope="col">计算机网络 </th>
                  <th scope="col">操作系统 </th>
                  <th scope="col">数据结构 </th>
                  <th scope="col">更新信息 </th>
                  <th scope="col">删除信息 </th>
                </tr>
              </thead>
            </table>
  
            <tbody>
              <Score v-for="sco in score_for_page" :key="sco.id" :score="sco"></Score>
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
  import ScoreHeader from '@/components/Score/ScoreHeader.vue';
  import Score from '@/components/Score/Score.vue';
  
  export default {
    name: "ScoreLayout",
    components: {
      Score,
      Aside,
      ScoreHeader,
    },
    
    data() {
      return {
        page: 1,
        score:[],
        dialogVisible1: false,
        dialogVisible2: false,
        border: true,
        dialogVisibleregistersuccess: false,
        newscore:{
          id:"",
          number: "",
          name: "",
          jz: "",
          jw: "",
          czxt: "",
          sjjg: "",
        },
      }
    },
  
    methods: {
      getScores(){
        if (sessionStorage.getItem("isLogedin") == "true") {
          axios({
            url: 'http://localhost:8080/scores',
            method: 'GET',
          }).then(res => {
            console.log(res.data);
            this.score = res.data;
  
          })
        }
        else {
          this.$alert("请先登录后再查看");
        }
      },
      addscore() {
        axios({
          url: "http://localhost:8080/add_score",
          method: "POST",
          data: this.newscore,
  
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
      score_for_page() {
        return this.score.slice(this.page * 10 - 10, this.page * 10);
  
      }
    },
  
  };
  
  
  </script>