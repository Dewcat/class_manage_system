<template> 

    <tr>
     <td v-show="!is_edit"   width="250px">{{ score.id }}</td>
       <td v-show="!is_edit" width="580px">{{ score.number }}</td>
       <td v-show="!is_edit" width="350px">{{ score.name }}</td>
       <td v-show="!is_edit" width="350px">{{ score.jz }}</td>
       <td v-show="!is_edit" width="350px">{{ score.jw }}</td>
       <td v-show="!is_edit" width="350px">{{ score.czxt }}</td>
       <td v-show="!is_edit" width="350px">{{ score.sjjg }}</td>
       <!--<td>space</td>-->
       <td v-show="!is_edit" width="520px"><el-button type="warning"  @click="modify">修改信息</el-button>
       </td>
       <td v-show="!is_edit" width="500px"><el-button type="danger" @click="deletescore">删除信息</el-button>
       </td>
       <td v-show="is_edit" class="align-top align-right"><input class="w-50" type="text" v-model.number="score.id"></td>
       <td v-show="is_edit" class="align-top align-right"><input class="w-50" type="text" v-model.number="score.number"></td>
       <td v-show="is_edit" class="align-top align-right"><input class="w-50" type="text" v-model="score.name"></td>
       <td v-show="is_edit" class="align-top align-right"><input class="w-50" type="text" v-model="score.jz"></td>
       <td v-show="is_edit" class="align-top align-right"><input class="w-50" type="text" v-model="score.jw"></td>
       <td v-show="is_edit" class="align-top align-right"><input class="w-50" type="text" v-model="score.czxt"></td>
       <td v-show="is_edit" class="align-top align-right"><input class="w-50" type="text" v-model="score.sjjg"></td>

       <!--<td>space</td>-->
       <td v-show="is_edit"><el-button type="warning" @click="save">保存信息</el-button>
       </td>
       <td v-show="is_edit"><el-button type="danger" @click="deletescore">删除信息</el-button>
       </td>
   
     </tr>
     
   </template>
     
   
   
   <script>
   
   import axios from 'axios';
   
   export default{
      name:"Score",
       props:["score"],
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
             url:"http://localhost:8080/update_score",
             method:"POST",
             data:this.score
           })
           this.is_edit=false;
           
           
         },
   
         deletescore(){
           axios({
             url:"http://localhost:8080/delete_score",
             method:"POST",
             data:this.score
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