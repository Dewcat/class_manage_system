import {createRouter,createWebHashHistory } from "vue-router";
import TeacherLayout from "@/components/Teacher/TeacherLayout.vue"
import StudentLayout from "@/components/Student/StudentLayout.vue"
import Subject from "@/components/Subject/Subject.vue"

const router = createRouter({
    history:createWebHashHistory(),
    routes:[
        {
            path:"/Teacher",
            name:"Teacher",
            meta:{
                title:'教师列表'
            },
            component:()=>import('@/components/Teacher/TeacherLayout.vue')

         
        },

        {
            path:"/Subject",
            name:"Subject",
            meta:{
                title:'课程信息'
            },
            component:()=>import('@/components/Subject/SubjectLayout.vue')
        },

        {
            path:"/Student",
            name:"Student",
            meta:{
                title:'学生列表'
            },
            component:()=>import('@/components/Student/StudentLayout.vue')
        },
        {
            path:"/Score",
            name:"Score",
            meta:{
                title:'课程成绩'
            },
            component:()=>import('@/components/Score/ScoreLayout.vue')

         
        },
    ]

});

export default router;