<template>
  <div style="padding: 10px 0">
    <div style="margin: 10px 0">
      <el-input v-model="cname" placeholder="请输入课程名称" style="width: 300px" clearable></el-input>
      <el-input v-model="cid" placeholder="请输入课程号" style="width: 300px;margin-left: 10px ;margin-right: 10px" clearable></el-input>
      <el-input v-model="tname" placeholder="请输入教师名称" style="width: 300px" clearable></el-input>
      <el-select v-model="grades" placeholder="请选择">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <el-button type="primary" style="margin: 5px" @click="load">查询</el-button>




    </div>

    <div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="course.cid" label="课程号" width="50" sortable  />
        <el-table-column prop="course.cname" label="课程名称" width="100" />
        <el-table-column prop="teacher.tname" label="任课教师" width="100" />
        <el-table-column prop="course.time" label="学时" width="100" />
        <el-table-column prop="course.score" label="学分" width="100" />
        <el-table-column prop="course.classtime" label="上课时间" width="100" />
        <el-table-column prop="course.address" label="上课地点" width="100" />
        <el-table-column prop="course.examtime" label="考试时间" width="100" />
        <el-table-column prop="student.sid" label="学号" width="100" />
        <el-table-column prop="student.sname" label="姓名" width="100" />
        <el-table-column prop="student.sex" label="性别" width="50" />
        <el-table-column prop="us" label="平时成绩" width="100" />
        <el-table-column prop="es" label="考试成绩" width="100" />
        <el-table-column prop="allScore" label="总成绩" width="100" sortable/>
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
  name:'GradesShow',


  components:{

  },
  data() {
    return {
      form:{},
      fromInline:{},
      dialogVisible : false ,
      pageSize: 6,
      cname : '',
      cid:'',
      tname:'',
      link : '',
      n  : true,
      c : true,
      grades:'',
      studentid : false,
      did : '',
      currentPage:1,
      total : 0,
      options: [
        {
          value: '',
          label: '无'
        } ,{
        value: '90',
        label: '90及以上'
      }, {
        value: '80',
        label: '80及以上'
      }, {
        value: '70',
        label: '70及以上'
      }, {
        value: '60',
        label: '及格'
      }, {
        value: '-1',
        label: '不及格'
      }],
      tableData: [

      ],
    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){     //查询
      request.get("/score/gggg", {
        params :{
          pageNumber : this.currentPage,
          pageSize : this.pageSize,
          cid : this.cid,
          cname : this.cname,
          tname : this.tname,
          grades : this.grades

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
    }
  },
}
</script>
