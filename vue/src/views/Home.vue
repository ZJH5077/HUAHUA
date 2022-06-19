<template>
  <div style="padding: 10px 0">
    <div style="margin: 10px 0">
    <el-button type="primary" @click="add">新增</el-button>
  </div>
  <div style="margin: 10px 0">
    <el-input v-model="cname" placeholder="请输入课程名称" style="width: 300px" clearable></el-input>
    <el-input v-model="cid" placeholder="请输入课程编号" style="width: 300px;margin-left: 10px ;margin-right: 10px" clearable></el-input>
    <el-input v-model="tid" placeholder="请输入教师编号" style="width: 300px" clearable></el-input>
    <el-button type="primary" style="margin: 5px" @click="load">查询</el-button>
  </div>
  <div>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="cid" label="ID" width="100" sortable  />
      <el-table-column prop="cname" label="课程名称" width="100" />
      <el-table-column prop="teacherId" label="任课教师号" width="100" />
      <el-table-column prop="time" label="学时" width="100" />
      <el-table-column prop="score" label="学分" width="100" />
      <el-table-column prop="classtime" label="上课时间" />
      <el-table-column prop="address" label="上课地点" />
      <el-table-column prop="examtime" label="考试时间" />
      <el-table-column fixed="right" label="操作" width="300">
      <template #default="scope">
            <el-button type="text" @click="handleEdit(scope.row)">编辑</el-button>
        <el-popconfirm title="靓仔，删掉这个是吧?" @confirm="handleDel(scope.row.cid)">
            <template #reference>
              <el-button>Delete</el-button>
            </template>
          </el-popconfirm>
      </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px 0">
      <el-pagination
          v-model:currentPage="currentPage"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>


    <el-dialog v-model="dialogVisible" title="课程信息" width="30%">
      <el-form :model="form" label-width="120px">
        <el-form-item  label="课程名称">
          <el-input v-model="form.cname"></el-input>
        </el-form-item>
        <el-form-item  label="任课教师号">
          <el-input v-model="form.teacherId"></el-input>
        </el-form-item>
        <el-form-item  label="学时">
          <el-input v-model="form.time"></el-input>
        </el-form-item>
        <el-form-item  label="学分">
          <el-input v-model="form.score"></el-input>
        </el-form-item>
        <el-form-item  label="上课时间">
          <el-input v-model="form.classtime"></el-input>
        </el-form-item>
        <el-form-item  label="上课地点">
          <el-input v-model="form.address"></el-input>
        </el-form-item>
        <el-form-item  label="考试时间">
          <el-input v-model="form.examtime"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确定</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
  </div>
</template>

<script>

import request from "../utils/request";

export default {
    name:'Home',


   components:{

   },
  data() {
    return {
      form:{},
      dialogVisible : false ,
      pageSize: 6,
      cname : '',
      cid : '',
      tid : '',
      currentPage:1,
      total : 0,
      tableData: [

      ],
    }
  },
  created() {
      this.load()
  },
  methods: {
      load(){     //查询
        request.get("/course", {
            params :{
              pageNumber : this.currentPage,
              pageSize : this.pageSize,
              cname : this.cname,
              cid : this.cid,
              tid : this.tid
            }
        }).then(res => {
          console.log(res)
          this.tableData = res.data.records
          this.total = res.data.total
        })
      },
      add(){   //打开弹窗 新增按钮
        this.dialogVisible = true
        this.form = {}
      },
    save(){  // 保存数据
        if(this.form.cid){
          request.put("/course",this.form).then(res =>{
            console.log(res);
            if(res.code == '0'){
              this.$message({
                type : "success",
                message : "更改 成功"
              })
            }else {
              this.$message({
                type : "error",
                message : "更改失败"
              })
            }
            this.load()
            this.dialogVisible = false
        })
        }else {
          request.post("/course",this.form).then(res =>{
            console.log(res);
            if(res.code == '0'){
              this.$message({
                type : "success",
                message : "更改 成功"
              })
            }else {
              this.$message({
                type : "error",
                message : "更改失败"
              })
            }
            this.load()
            this.dialogVisible = false
          })
        }
    },
    handleClick() {
      console.log('click')
    },
    handleSizeChange(){
        this.load()
    },
    handleCurrentChange(){
      this.load()
    },
    handleEdit(row){
        this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },
    handleDel(cid){
        console.log(cid)
        request.delete("/course/"+cid).then(res =>{
          if(res.code === '0'){
            this.$message({
              type : "success",
              message : "更改 成功"
            })
          }else {
            this.$message({
              type : "error",
              message : "更改失败"
            })
          }
          this.load()
        })
    }
  },
}
</script>
