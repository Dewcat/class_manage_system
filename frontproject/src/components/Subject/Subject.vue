<template> 

    <tr>
     <td v-show="!is_edit"   width="250px">{{ subject.id }}</td>
       <td v-show="!is_edit" width="580px">{{ subject.number }}</td>
       <td v-show="!is_edit" width="350px">{{ subject.name }}</td>
       <td v-show="!is_edit" width="350px">{{ subject.teacher }}</td>
       <td v-show="!is_edit" width="350px">{{ subject.classroom }}</td>
       <!--<td>space</td>-->
       <td v-show="!is_edit" width="520px"><el-button type="warning"  @click="modify">修改信息</el-button>
       </td>
       <td v-show="!is_edit" width="500px"><el-button type="danger" @click="deletesubject">删除信息</el-button>
       </td>
       <td v-show="is_edit" class="align-top"></td>
       <td v-show="is_edit" class="align-top"><input class="w-50" type="text" v-model.number="subject.number"></td>
       <td v-show="is_edit" class="align-top"><input class="w-50" type="text" v-model="subject.name"></td>
       <td v-show="is_edit" class="align-top"><input class="w-50" type="text" v-model="subject.teacher"></td>
       <td v-show="is_edit" class="align-top"><input class="w-50" type="text" v-model="subject.classroom"></td>

       <!--<td>space</td>-->
       <td v-show="is_edit"><el-button type="warning" @click="save">保存信息</el-button>
       </td>
       <td v-show="is_edit"><el-button type="danger" @click="deletesubject">删除信息</el-button>
       </td>
   
     </tr>
     
   </template>
     
   
   
   <script>
   
   import axios from 'axios';
   
   export default{
      name:"Subject",
       props:["subject"],
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
             url:"http://localhost:8080/update_subject",
             method:"POST",
             data:this.subject
           })
           this.is_edit=false;
           
           
         },
   
         deletesubject(){
           axios({
             url:"http://localhost:8080/delete_subject",
             method:"POST",
             data:this.subject
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