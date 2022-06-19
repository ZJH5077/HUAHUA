<template>
  <div style="padding: 10px 0">
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">录入</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="sname" placeholder="请输入姓名" style="width: 300px" clearable></el-input>
      <el-input v-model="sid" placeholder="请输入学生学号" style="width: 300px;margin-left: 10px ;margin-right: 10px" clearable></el-input>
      <el-input v-model="link" placeholder="请输入系号" style="width: 300px" clearable></el-input>
      <el-button type="primary" style="margin: 5px" @click="load">查询</el-button>
    </div>
    <div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="sid" label="学号" width="100" sortable  />
        <el-table-column prop="sname" label="姓名" width="100" />
        <el-table-column prop="sex" label="性别" width="100" />
        <el-table-column prop="birthday" label="出生日期" width="100" />
        <el-table-column prop="score" label="入学成绩" width="100" />
        <el-table-column prop="link" label="所属系号" />
        <el-table-column fixed="right" label="操作" width="300">
          <template #default="scope">
            <el-button type="text" @click="handleEdit(scope.row)">编辑</el-button>
            <el-popconfirm title="靓仔，删掉这个是吧?" @confirm="handleDel(scope.row.sid)">
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


      <el-dialog v-model="dialogVisible" title="学生信息" width="30% ">
        <el-form  :model="form" label-width="120px">
          <el-form-item  label="学号">
            <el-input v-model="form.sid" :disabled="studentid"></el-input>
          </el-form-item>
          <el-form-item  label="姓名">
            <el-input v-model="form.sname"></el-input>
          </el-form-item>
          <el-form-item  label="性别">
            <el-radio v-model="form.sex" label="男">男</el-radio>
            <el-radio v-model="form.sex" label="女">女</el-radio>
          </el-form-item>
          <el-form-item  label="出生日期">
            <el-input v-model="form.birthday"></el-input>
          </el-form-item>
          <el-form-item  label="入学成绩">
            <el-input v-model="form.score"></el-input>
          </el-form-item>
          <el-form-item  label="所属系号">
            <el-input v-model="form.link"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save" :disabled="n">新增</el-button>
        <el-button type="primary" @click="change" :disabled="c">更改</el-button>
      </span>
        </template>
      </el-dialog>


    </div>
  </div>
</template>

<script>

import request from "../utils/request";

export default {
  name:'Student',


  components:{

  },
  data() {
    return {
      form:{},
      fromInline:{},
      dialogVisible : false ,
      pageSize: 6,
      sname : '',
      link : '',
      n  : true,
      c : true,
      studentid : false,
      sid : '',
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
      request.get("/student", {
        params :{
          pageNumber : this.currentPage,
          pageSize : this.pageSize,
          sname : this.sname,
          sid : this.sid,
          link : this.link
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
      this.n = false
      this.c = true
      this.studentid = false
    },
    save(){  // 一个录入
        request.post("/student",this.form).then(res =>{
          console.log(res);
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
          this.dialogVisible = false
        })
      },
    change() {
      if (this.form.sid) { //改变内容
        request.put("/student", this.form).then(res => {
          console.log(res);
          if (res.code == '0') {
            this.$message({
              type: "success",
              message: "更改 成功"
            })
          } else {
            this.$message({
              type: "error",
              message: "更改失败"
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
      this.n = true
      this.c = false
      this.studentid = true
    },
    handleDel(sid){
      console.log(sid)
      request.delete("/student/"+sid).then(res =>{
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
