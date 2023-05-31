<template>
  
</template>


<script>

export default {
  name: "TeacherLayout",
  components: {
    Student,
    Aside,
    Header,
  },
  data() {

    return {
      page: 1,
      student: [],
      dialogVisible1: false,
      dialogVisible2: false,
      dialogVisiblelogin: false,
      dialogVisibleregister: false,
      dialogVisibleifclear: false,
      dialogVisibleclear: false,
      border: true,
      dialogVisibleregistersuccess: false,
      newstudent1: Array(10).fill(newstudent10),
      user_for_login: {
        username: "",
        password: "",
      },
      user_for_register: {
        username: "",
        password: "",
      },
      newstudent: {
        number: "",
        name: "",
        age: "",
        sex: "",
        banji: "",

      }

    }
  },

  methods: {
    getStudents() {
      if (sessionStorage.getItem("isLogedin") == "true") {
        axios({
          url: 'http://localhost:8080/students',
          method: 'GET',
        }).then(res => {
          console.log(res.data);
          this.student = res.data;

        })
      }
      else {
        this.$alert("请先登录后再查看");
      }

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
    pre_page() {
      this.page -= 1;

    },
    next_page() {
      this.page += 1;

    }
  },
  computed: {
    student_for_page() {
      return this.student.slice(this.page * 10 - 10, this.page * 10);

    }
  },

};


</script>
<style>

</style>