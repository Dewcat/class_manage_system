<template> 

 <tr>
  <td v-show="!is_edit"   width="220px">{{ student.id }}</td>
    <td v-show="!is_edit" width="280px">{{ student.number }}</td>
    <td v-show="!is_edit" width="350px">{{ student.name }}</td>
    <td v-show="!is_edit" width="320px">{{ student.age }}</td>
    <td v-show="!is_edit" width="350px">{{ student.sex }}</td>
    <td v-show="!is_edit" width="320px">{{ student.banji }}</td>
    <!--<td>space</td>-->
    <td v-show="!is_edit" width="520px"><el-button type="warning"  @click="modify">修改信息</el-button>
    </td>
    <td v-show="!is_edit" width="500px"><el-button type="danger" @click="deletestudent">删除信息</el-button>
    </td>
    <td v-show="is_edit" class="align-top"></td>
    <td v-show="is_edit" class="align-top"><input class="w-50" type="text" v-model.number="student.number"></td>
    <td v-show="is_edit" class="align-top"><input class="w-50" type="text" v-model="student.name"></td>
    <td v-show="is_edit" class="align-top"><input class="w-50" type="text" v-model.number="student.age"></td>
    <td v-show="is_edit" class="align-top"><input class="w-50" type="text" v-model="student.sex"></td>
    <td v-show="is_edit" class="align-top"><input class="w-50" type="text" v-model="student.banji"></td>
    <!--<td>space</td>-->
    <td v-show="is_edit"><el-button type="warning" @click="save">保存信息</el-button>
    </td>
    <td v-show="is_edit"><el-button type="danger" @click="deletestudent">删除信息</el-button>
    </td>

  </tr>
  
</template>
  


<script>

import axios from 'axios';

export default{
   name:"Student",
    props:["student"],
    data(){
      return{
        is_edit:false
      }
    },

    methods:{
      modify(){
        this.is_edit=true;
      },

      save(){
        axios({
          url:"http://localhost:8080/update_student",
          method:"POST",
          data:this.student
        })
        this.is_edit=false;
        
        
      },

      deletestudent(){
        axios({
          url:"http://localhost:8080/delete_student",
          method:"POST",
          data:this.student
        })
        this.is_edit=false;
        this.$alert("删除成功");
        location.reload();
        
      }
    }
}
</script>

<style >

</style>