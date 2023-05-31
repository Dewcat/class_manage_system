<template>
  
  <el-aside width="200px">
    <el-menu background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" style="height: 200%;">
      <div class="toolbar">
          <!--个人中心登陆退出注册-->
        <el-dropdown :hide-on-click="false"><div style="color: white;font-size: 30px;margin-left:20px"><span>个人中心</span></div>
          <el-icon style="margin-right: 1px; margin-top: 7px">
            <CaretBottom />
          </el-icon>

          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item><el-button type="success" @click="dialogVisiblelogin = true">用户登录</el-button>
                <el-dialog v-model="dialogVisiblelogin" title="用户登录" width="30%" :before-close="handleClose">
                  <div><span>用户名:</span><input type="text" v-model="user_for_login.username"></div>
                  <div><span> 密码：</span><input type="password" v-model="user_for_login.password"></div>
                  <template #footer>
                    <span class="dialog-footer">
                      <el-button @click="dialogVisiblelogin = false">取消</el-button>
                      <el-button type="primary" @click="login">登录</el-button>
                    </span>
                  </template>
                </el-dialog></el-dropdown-item>
              <el-dropdown-item><el-button type="primary" @click="dialogVisiblenoclear = true">退出账号</el-button>
                <el-dialog v-model="dialogVisiblenoclear" title="退出账号" width="30%" :before-close="handleClose">
                  <span>确认退出？</span>
                  <template #footer>
                    <span class="dialog-footer">
                      <el-button @click="dialogVisiblenoclear = false">取消</el-button>
                      <el-button type="primary" @click="clear(); dialogVisibleclear = true">确认</el-button>
                      <el-dialog v-model="dialogVisibleclear" width="30%" :before-close="handleClose">
                        <span>退出成功！</span>
                      </el-dialog>
                    </span>
                  </template>
                </el-dialog></el-dropdown-item>

              <el-button text @click="dialogVisibleregister = true">没有账户？点击注册</el-button>
              <el-dialog v-model="dialogVisibleregister" title="注册账号" width="30%" :before-close="handleClose">
                <div><span>用户名:</span><input type="text" v-model="user_for_register.username"></div>
                <div><span> 密码：</span><input type="password" v-model="user_for_register.password"></div>
                <template #footer>
                  <span class="dialog-footer">
                    <el-button @click="dialogVisibleregister = false">取消</el-button>
                    <el-button type="primary" @click="register(); dialogVisibleregistersuccess = true">注册</el-button>
                    <el-dialog v-model="dialogVisibleregistersuccess" width="30%" :before-close="handleClose">
                      <span>注册成功!</span>
                      <template #footer>
                        <span class="dialog-footer">
                          <el-button type="primary"
                            @click="dialogVisibleregistersuccess = false; dialogVisibleregister = false;">确认</el-button>
                        </span>
                      </template>
                    </el-dialog>
                  </span>

                </template>
              </el-dialog>
            </el-dropdown-menu>
          </template>
        </el-dropdown>

      </div>
      <el-menu-item-group>
        
        <el-menu-item index="/Student" @click="toStudent">学生列表</el-menu-item>
        <el-menu-item index="/Teacher" @click="toTeacher">教师列表</el-menu-item>
        <el-menu-item index="/Score" @click="toScore">成绩查询</el-menu-item>
        <el-menu-item index="/Subject" @click="toSubject">课程信息</el-menu-item>
        
      </el-menu-item-group>

    </el-menu>
  </el-aside>

</template>
 
<script>
import axios from 'axios';
import Student from '@/components/Student/Student.vue';
export default {
  name: "Aside",
  components: {
    Student,
  },
  data() {
    return {
      dialogVisiblelogin: false,
      dialogVisibleregister: false,
      dialogVisiblenoclear: false,
      dialogVisibleclear: false,
      dialogVisibleregistersuccess: false,
      user_for_login: {
        username: "",
        password: "",
      },
      user_for_register: {
        username: "",
        password: "",
      },
    }
  },

  methods: {
    toScore(){
      console.log('to_Score')
      this.$router.push("/Score")
    },
    toSubject(){
      console.log('to_Subject')
      this.$router.push("/Subject")
    },
    toStudent(){
      console.log('to_Student')
      this.$router.push("/Student")
    },
    toTeacher(){
      console.log('to_Teacher')
      this.$router.push("/Teacher")
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
    register() {
      axios({
        url: "http://localhost:8080/register",
        method: "POST",
        data: this.user_for_register,
      })

    },
    login() {
      axios({
        url: "http://localhost:8080/login",
        method: "POST",
        data: this.user_for_login,
      }).then(res => {
        console.log(res.data);
        if (res.data == "1") {
          sessionStorage.setItem("isLogedin", "true");
        }
        else {
          alert("用户名或密码错误");
        };
      })
      this.dialogVisiblelogin = false;
    },
    clear() {
      sessionStorage.clear("isLogedin");
      location.reload();

    },
   
  },

};
</script>
<style>
.el-aside {
  color: #333;
}
</style>