<template> 

    <tr>
     <td v-show="!is_edit"   width="250px">{{ teacher.id }}</td>
       <td v-show="!is_edit" width="580px">{{ teacher.number }}</td>
       <td v-show="!is_edit" width="350px">{{ teacher.name }}</td>
       <td v-show="!is_edit" width="350px">{{ teacher.age }}</td>
       <td v-show="!is_edit" width="350px">{{ teacher.sex }}</td>
       <!--<td>space</td>-->
       <td v-show="!is_edit" width="520px"><el-button type="warning"  @click="modify">修改信息</el-button>
       </td>
       <td v-show="!is_edit" width="500px"><el-button type="danger" @click="deleteteacher">删除信息</el-button>
       </td>
       <td v-show="is_edit" class="align-top"></td>
       <td v-show="is_edit" class="align-top"><input class="w-50" type="text" v-model.number="teacher.number"></td>
       <td v-show="is_edit" class="align-top"><input class="w-50" type="text" v-model="teacher.name"></td>
       <td v-show="is_edit" class="align-top"><input class="w-50" type="text" v-model.number="teacher.age"></td>
       <td v-show="is_edit" class="align-top"><input class="w-50" type="text" v-model="teacher.sex"></td>

       <!--<td>space</td>-->
       <td v-show="is_edit"><el-button type="warning" @click="save">保存信息</el-button>
       </td>
       <td v-show="is_edit"><el-button type="danger" @click="deleteteacher">删除信息</el-button>
       </td>
   
     </tr>
     
   </template>
     
   
   
   <script>
   
   import axios from 'axios';
   
   export default{
      name:"Teacher",
       props:["teacher"],
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
             url:"http://localhost:8080/update_teacher",
             method:"POST",
             data:this.teacher
           })
           this.is_edit=false;
           
           
         },
   
         deleteteacher(){
           axios({
             url:"http://localhost:8080/delete_teacher",
             method:"POST",
             data:this.teacher
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