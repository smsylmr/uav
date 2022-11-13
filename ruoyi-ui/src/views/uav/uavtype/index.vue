<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--无人机列表-->
      <el-col  :xs="24">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="型号名称" prop="uavTypeName">
            <el-input
              v-model="queryParams.uavTypeName"
              placeholder="根据型号名称模糊查询"
              clearable
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="型号备注" prop="uavRemark">
            <el-input
              v-model="queryParams.uavRemark"
              placeholder="根据型号备注模糊查询"
              clearable
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
            >新增无人机型号</el-button>
          </el-col>
          
        </el-row>

        <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
          <el-table-column type="index" label="序号" width="50" align="center" />
          <el-table-column label="型号名称" align="center" key="uavName" prop="uavTypeName" />
          <el-table-column label="机型图" align="center"  :show-overflow-tooltip="true" >
            <template slot-scope="scope">
              <img :src="'/dev-api'+scope.row.imgPath" class="user-avatar mh" min-width="50" height="50">
            </template>
          </el-table-column>

          <el-table-column label="备注" align="center" key="remark" prop="uavRemark"  :show-overflow-tooltip="true" />
          <el-table-column label="创建时间" align="createDate" prop="createDate"  width="160">
          </el-table-column>
          <el-table-column
            label="操作"
            align="center"
            width="160"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope" v-if="scope.row.userId !== 1">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
      </el-col>
    </el-row>

        <!-- 修改用户配置对话框 -->
        <el-dialog :title="title" :visible.sync="openUpdate" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="型号" prop="uavTypeName">
              <el-input v-model="form.uavTypeName" placeholder="请输入型号1名称" maxlength="30"  disabled/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="照片" prop="xx">
              <div class="user-info-head1" ><el-image v-bind:src="'/dev-api'+form.imgPath" title="点击无人机型号照片" class="img-square img-lg" >
                <div slot="error" class="image-slot">
        <i class="el-icon-picture-outline"></i>
        <br/>
        请选择型号图片
      </div>
              </el-image></div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注">
              <el-input v-model="form.uavRemark" type="textarea" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel1">取 消</el-button>
      </div>
    </el-dialog>


    <!-- 添加或修改用户配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="型号" prop="uavTypeName">
              <el-input v-model="form.uavTypeName" placeholder="请输入型号1名称" maxlength="30" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="照片" prop="xx">
              <div class="user-info-head" @click="editCropper()"><el-image v-bind:src="options.img" title="点击无人机型号照片" class="img-square img-lg" >
                <div slot="error" class="image-slot">
        <i class="el-icon-picture-outline"></i>
        <br/>
        请选择型号图片
      </div>
              </el-image></div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注">
              <el-input v-model="form.uavRemark" type="textarea" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!--修改无人机型号照片-->
    <el-dialog :title="title" :visible.sync="openFile" width="800px" append-to-body @opened="modalOpened"  @close="closeDialog">
      <el-row>
        <el-col :xs="24" :md="12" :style="{height: '350px'}">
          <vue-cropper
            ref="cropper"
            :img="options.img"
            :info="true"
            :autoCrop="options.autoCrop"
            :autoCropWidth="options.autoCropWidth"
            :autoCropHeight="options.autoCropHeight"
            :fixedBox="options.fixedBox"
            @realTime="realTime"
            v-if="visible"
          />
        </el-col>
        <el-col :xs="24" :md="12" :style="{height: '350px'}">
          <div class="avatar-upload-preview">
            <img :src="previews.url" :style="previews.img" />
          </div>
        </el-col>
      </el-row>
      <br />
      <el-row>
        <el-col :lg="2" :md="2">
          <el-upload action="#" :http-request="requestUpload" :show-file-list="false" :before-upload="beforeUpload">
            <el-button size="small">
              选择
              <i class="el-icon-upload el-icon--right"></i>
            </el-button>
          </el-upload>
        </el-col>
        <el-col :lg="{span: 1, offset: 2}" :md="2">
          <el-button icon="el-icon-plus" size="small" @click="changeScale(1)"></el-button>
        </el-col>
        <el-col :lg="{span: 1, offset: 1}" :md="2">
          <el-button icon="el-icon-minus" size="small" @click="changeScale(-1)"></el-button>
        </el-col>
        <el-col :lg="{span: 1, offset: 1}" :md="2">
          <el-button icon="el-icon-refresh-left" size="small" @click="rotateLeft()"></el-button>
        </el-col>
        <el-col :lg="{span: 1, offset: 1}" :md="2">
          <el-button icon="el-icon-refresh-right" size="small" @click="rotateRight()"></el-button>
        </el-col>
        <el-col :lg="{span: 2, offset: 6}" :md="2">
          <el-button type="primary" size="small" @click="submitImg()">确 认</el-button>
          <!--<el-button type="primary" size="small" @click="uploadImg()">提 交</el-button>-->
        </el-col>
      </el-row>
    </el-dialog>

  </div>
</template>

<script>
import {uploadTypeA,uploadTypeU,listUavType,delUavType,getUavType} from "@/api/uav/uavtype"
import { VueCropper } from "vue-cropper";
import { listUser, getUser, delUser, addUser, updateUser, resetUserPwd, changeUserStatus, deptTreeSelect } from "@/api/system/user";
import { listUav } from "@/api/uav/uav"
import { getToken } from "@/utils/auth";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import { runInThisContext } from "vm";
import { Form } from "element-ui";

export default {
  name: "User",
  dicts: ['sys_normal_disable', 'sys_user_sex'],
  components: { Treeselect,VueCropper},
  data() {
    return {
      openUpdate:false,
      pt:'/dev-api/profile/avatar/2022/10/22/blob_20221022233220A001.jpeg',
      fData:new FormData(),
      previews: {},
      visible: false,
      openFile:false,
      options: {
        img: undefined, //裁剪图片的地址
        autoCrop: true, // 是否默认生成截图框
        autoCropWidth: 20, // 默认生成截图框宽度
        autoCropHeight: 20, // 默认生成截图框高度
        fixedBox: true // 固定截图框大小 不允许改变
      },
      //用户详情
      detailOpen:false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户表格数据
      userList: null,
      // 弹出层标题
      title: "",
      // 区域树选项
      deptOptions: undefined,
      // 是否显示弹出层
      open: false,
      // 区域名称
      deptName: undefined,
      // 默认密码
      initPassword: undefined,
      // 日期范围
      dateRange: [],
      // 岗位选项
      postOptions: [],
      // 角色选项
      roleOptions: [],
      // 表单参数
      form: {},
      defaultProps: {
        children: "children",
        label: "label"
      },
      // 用户导入参数
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/system/user/importData"
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        uavTypeName: undefined,
        uavRemark: undefined
      },
      // 表单校验
      rules: {
        userName: [
          { required: true, message: "用户名称不能为空", trigger: "blur" },
          { min: 2, max: 20, message: '用户名称长度必须介于 2 和 20 之间', trigger: 'blur' }
        ],
        nickName: [
          { required: true, message: "无人机型号名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  watch: {
    // 根据名称筛选区域树
    deptName(val) {
      this.$refs.tree.filter(val);
    }
  },
  created() {
    this.getList();
    //this.getUavList();
    this.getDeptTree();
    this.getConfigKey("sys.user.initPassword").then(response => {
      this.initPassword = response.msg;
    });
  },
  methods: {
    // 向左旋转
    rotateLeft() {
      this.$refs.cropper.rotateLeft();
    },
    // 向右旋转
    rotateRight() {
      this.$refs.cropper.rotateRight();
    },
    // 图片缩放
    changeScale(num) {
      num = num || 1;
      this.$refs.cropper.changeScale(num);
    },
    // 关闭窗口
    closeDialog() {
      //this.options.img = store.getters.avatar
      this.visible = false;
    },
    submitImg(){
      //this.fData = new FormData();
      this.$refs.cropper.getCropBlob(data => {
        this.fData.append("img", data);
      });
      console.log(this.fData.img)
      this.openFile = false;
    },
    editCropper(){
      this.openFile = true;
    },
    // 打开弹出层结束时的回调
    modalOpened() {
      this.visible = true;
    },
    /** 查询用户列表 */
    getList() {
      this.loading = true;
      listUavType(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.userList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },    
    /** 查询无人机列表 */
    getUavList() {
      listUav(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          
        }
      );
    },
    /** 查询区域下拉树结构 */
    getDeptTree() {
      deptTreeSelect().then(response => {
        this.deptOptions = response.data;
      });
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.deptId = data.id;
      this.handleQuery();
    },
    // 用户状态修改
    handleStatusChange(row) {
      let text = row.status === "0" ? "启用" : "停用";
      this.$modal.confirm('确认要"' + text + '""' + row.userName + '"用户吗？').then(function() {
        return changeUserStatus(row.userId, row.status);
      }).then(() => {
        this.$modal.msgSuccess(text + "成功");
      }).catch(function() {
        row.status = row.status === "0" ? "1" : "0";
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
        // 取消按钮
        cancel1() {
      this.openUpdate = false;
      this.reset();
    },
        // 实时预览
        realTime(data) {
      this.previews = data;
    },
        // 上传预处理
        beforeUpload(file) {
      if (file.type.indexOf("image/") == -1) {
        this.$modal.msgError("文件格式错误，请上传图片类型,如：JPG，PNG后缀的文件。");
      } else {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => {
          this.options.img = reader.result;
        };
      }
    },
        // 覆盖默认的上传行为
        requestUpload() {
    },
    // 表单重置
    reset() {
      this.form = {
        userId: undefined,
        uavTypeName: undefined,
        userName: undefined,
        nickName: undefined,
        password: undefined,
        phonenumber: undefined,
        email: undefined,
        sex: undefined,
        status: "0",
        remark: undefined,
        postIds: [],
        roleIds: []
      };
      this.fData = new FormData(),
      this.options.img = undefined
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userId);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      getUser().then(response => {
        this.postOptions = response.posts;
        this.roleOptions = response.roles;
        this.open = true;
        this.title = "新增无人机型号";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const uavTypeId = row.uavTypeId;
      getUavType(uavTypeId).then(response => {
        this.form = response.data;
        this.openUpdate = true;
        this.title = "修改无人机型号";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.fData.append('uavRemark',this.form.uavRemark?this.form.uavRemark:'')
          this.fData.append('uavTypeName',this.form.uavTypeName)
          if (this.form.uavTypeId != undefined) {
            uploadTypeU({'uavTypeId':this.form.uavTypeId,'uavRemark':this.form.uavRemark?this.form.uavRemark:''}).then(response => {
              this.$modal.msgSuccess("修改无人机类型成功");
              this.openUpdate = false;
              this.getList();
            });
          } else {
            uploadTypeA(this.fData).then(response => {
              this.$modal.msgSuccess("新增无人机类型成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 查看详情操作 */
    handleDetail(row){
      this.detailOpen = true;

    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const uavTypeId = row.uavTypeId;
      this.$modal.confirm('是否确认删除当前无人机类型？').then(function() {
        return delUavType(uavTypeId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    }
  }
};
</script>
<style scoped>
.user-info-head {
  position: relative;
  display: inline-block;
  height: 120px;
  cursor: pointer;
}
.user-info-head1 {
  position: relative;
  display: inline-block;
  height: 120px;
}
.mh{
  height:30;
}
</style>