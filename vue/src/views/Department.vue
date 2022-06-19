<template>
  <div style="padding: 10px 0">
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">录入</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-input v-model="lname" placeholder="请输入系名称" style="width: 300px" clearable></el-input>
      <el-input v-model="did" placeholder="请输入系号" style="width: 300px;margin-left: 10px ;margin-right: 10px" clearable></el-input>
      <el-button type="primary" style="margin: 5px" @click="load">查询</el-button>
    </div>
    <div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="did" label="系号" width="100" sortable  />
        <el-table-column prop="lname" label="系名称" width="100" />
        <el-table-column prop="introduce" label="系的简称" width="500" />
        <el-table-column fixed="right" label="操作" width="300">
          <template #default="scope">
            <el-button type="text" @click="handleEdit(scope.row)">编辑</el-button>
            <el-popconfirm title="靓仔，删掉这个是吧?" @confirm="handleDel(scope.row.did)">
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


      <el-dialog v-model="dialogVisible" title="系的基础信息" width="30% ">
        <el-form  :model="form" label-width="120px">
          <el-form-item  label="系号">
            <el-input v-model="form.did" :disabled="studentid"></el-input>
          </el-form-item>
          <el-form-item  label="系名称">
            <el-input v-model="form.lname"></el-input>
          </el-form-item>
          <el-form-item  label="系的简称">
            <el-input v-model="form.introduce"></el-input>
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
  name:'Department',


  components:{

  },
  data() {
    return {
      form:{},
      fromInline:{},
      dialogVisible : false ,
      pageSize: 6,
      lname : '',
      link : '',
      n  : true,
      c : true,
      studentid : false,
      did : '',
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
      request.get("/department", {
        params :{
          pageNumber : this.currentPage,
          pageSize : this.pageSize,
          lname : this.lname,
          did : this.did
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
      request.post("/department",this.form).then(res =>{
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
      if (this.form.did) { //改变内容
        request.put("/department", this.form).then(res => {
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
    handleDel(did){
      console.log(did)
      request.delete("/department/"+did).then(res =>{
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
