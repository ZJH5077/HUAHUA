<template>
  <div>
    <el-menu
        style="width: 200px ;min-height: calc(100vh - 50px)"
        active-text-color="#ffd04b"
        background-color="#545c64"
        class="el-menu-vertical-demo"
        default-active="home"
        router
        text-color="#fff"
        @open="handleOpen"
        @close="handleClose"
    >
      <el-sub-menu index="1">
        <template #title>
          <el-icon><location /></el-icon>
          <span>管理</span>
        </template>

          <el-menu-item index="home">信息管理</el-menu-item>
          <el-menu-item index="student">项目管理</el-menu-item>
        <el-menu-item index="teacher">时间表管理</el-menu-item>
       <el-menu-item index="department">合同签订</el-menu-item>
        <el-menu-item index="choicelesson">项目发布</el-menu-item>
        <el-menu-item index="addscore">查询</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="2">
        <template #title>
          <el-icon><location /></el-icon>
          <span>统计报表</span>
        </template>

        <el-menu-item index="gradesshow">成绩登记表</el-menu-item>
        </el-sub-menu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>

</style>