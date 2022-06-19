<template>
    <div style="width:100% ; height: 100vh ;background-color: coral;overflow:hidden">
      <div style="width: 400px;margin: 150px auto">
        <div style="color: aliceblue ;font-size: 30px;text-align: center">欢迎登录</div>
        <el-form ref="form" :model="form" size="normal">
            <el-form-item>
              <el-input placeholder="Please input username" v-model="from.name"></el-input>
            </el-form-item>
          <el-form-item>
            <el-input  v-model="from.password" placeholder="Please input password" show-password ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button style="width: 50%" type="primary" @click="login">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
      <router-view style="flex: 1"/>
    </div>

</template>

<script>
import request from "../utils/request";

export default {
  name: "Login",
  data(){
    return{
      from:{}
    }
  },
  methods:{
    login(){
      request.get("/user", {
        params :{
          username : this.from.name,
          password : this.from.password
        }
      }).then(res =>{
        if(res.code === '0'){
          this.$router.push("/Layout")  //页面跳转
        }else {
          this.$message({
            type : "error",
            message : "用户名或密码错误"
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>