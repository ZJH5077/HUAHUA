import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/Layout.vue'
import Login from '../views/Login.vue';



const routes = [
  {
    path:'/',
    name:'Login',
    component: Login,
  },
  {
    path: '/layout',
    name: 'Layout',
    component: Layout,
    redirect: "/home",   //重定向实现路由自动跳转
    children:[
      {
        path: '/home',
        name: 'Home',
        component: () => import("@/views/Home"),
      },
      {
        path:'/student',
        name:'Student',
        component:() => import("@/views/Student")
      },
      {
        path:'/teacher',
        name:'Teacher',
        component:() => import("@/views/Teacher")
      },
      {
        path:'/department',
        name:'Department',
        component:() => import("@/views/Department")
      },
      {
        path:'/choiceLesson',
        name:'ChoiceLesson',
        component:() => import("@/views/ChoiceLesson")
      },
      {
        path:'/test',
        name:'test',
        component:() => import("@/views/test")
      },
      {
        path:'/addscore',
        name:'addscore',
        component:() => import("@/views/AddScore")
      },
      {
        path:'/gradesshow',
        name:'GradesShow',
        component:() => import("@/views/GradesShow")
      }
    ]
  },

  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router


