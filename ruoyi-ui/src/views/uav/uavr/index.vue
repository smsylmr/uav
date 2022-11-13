<!--无人机状态数据管理-->
<template>
  <div class="app-container">
    <el-row >
      <!--用户数据-->
      <el-col  :xs="24">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="维保编号" prop="repairNo">
            <el-input
              v-model="queryParams.repairNo"
              placeholder="根据维保编号模糊搜索"
              clearable
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="序列号" prop="uavNo">
            <el-input
              v-model="queryParams.uavNo"
              placeholder="根据序列号模糊查询"
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

       <!-- <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['system:user:add']"
            >新增维保</el-button>
          </el-col>
        </el-row>-->

        <el-table v-loading="loading" :data="userList" >
          <el-table-column type="index" label="序号"  width="50" align="center" />
          <el-table-column label="维保编号" align="center" key="repairNo" prop="repairNo"/>
          <el-table-column label="序列号" align="center" key="uavNo" prop="uavNo"/>
          <el-table-column label="创建者" align="center" width="100" key="uname" prop="uname"/>
          <el-table-column label="创建时间" align="center" width="200" key="createDate" prop="createDate"/>
          <el-table-column
            label="维保数据"
            align="center"
            width="110"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope" >
              <el-button type="text"  icon="el-icon-s-claim" @click="handleRepairDetail(scope.row)">维保信息</el-button>
              </template>
          </el-table-column>
          <el-table-column label="维保状态" align="center"  prop="netStatus" :show-overflow-tooltip="true" >
            <template slot-scope="scope">
              <el-button type="text" :style="{'color':scope.row.netStatus==1?'#409EFF':'#F56C6C'}" >{{scope.row.netStatus==1?'已入网':'未入网'}}</el-button>
            </template>
          </el-table-column>
          <el-table-column
            label="维保反馈"
            align="center"
            width="110"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope" >
              <span v-if="scope.row.repairStatus!=1">尚无反馈</span>
              <el-button v-if="scope.row.repairStatus==1" type="text"  icon="el-icon-s-claim" @click="handleRepairDetailF(scope.row)">详细反馈</el-button>
              </template>
          </el-table-column>
          <el-table-column label="维保详细信息" align="center" width="200" key="createDate" prop="createDate"/>
          <!--<el-table-column
            label="维保"
            align="center"
            width="160"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope" v-if="scope.row.deviceStatus==2">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-s-tools"
                @click="handleUpdate(scope.row)"
              >一键维保</el-button>
            </template>
          </el-table-column>-->
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

    <!-- 添加或修改用户配置对话框 -->
    <el-dialog title="无人机维保" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="机器名称" prop="uavName">
              <el-input v-model="form.uavName" placeholder="请输入无人机名称" maxlength="30" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="序列编号" prop="uavNo">
              <el-input v-model="form.uavNo" placeholder="请输入无人机序列号" maxlength="30" :disabled='this.form.uavId?true:false'/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="所属区域" prop="areaId">
              <treeselect v-model="form.areaId" :options="deptOptions" :show-count="true" placeholder="请选择归属区域" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="机器类型" prop="uavTypeId">
              <el-select v-model="form.uavTypeId" filterable placeholder="请选择机器类型">
                <el-option
                  v-for="dict in uavTypes"
                  :key="dict.uavTypeId"
                  :label="dict.uavTypeName"
                  :value="dict.uavTypeId"
                ></el-option>
              </el-select>
            </el-form-item>
            </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="保险单号" prop="chitNo">
              <el-input v-model="form.chitNo" placeholder="请输入保险单号" maxlength="30" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="购买日期" prop="createDate">
              <el-date-picker v-model="form.createDate" type="date" placeholder="选择日期"  value-format="yyyy-MM-dd" />
            </el-form-item>
          </el-col>
        </el-row>
        
        <el-row>
          <el-col :span="12">
            <el-form-item label="是否入网" prop="netStatus">
              <el-radio-group v-model="form.netStatus">
                <el-radio :label="0">未入网</el-radio>
                <el-radio :label="1">已入网</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="所属用户" prop="userId">
              <el-select v-model="form.userId" filterable placeholder="请选择所属用户">
                <el-option
                  v-for="dict in users"
                  :key="dict.userId"
                  :label="dict.nickName"
                  :value="dict.userId"
                ></el-option>
              </el-select>
            </el-form-item>
            </el-col>
        </el-row>
        
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

        <!-- 添加或修改用户配置对话框 -->
    <el-dialog title="无人机维保" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="机器名称" prop="uavName">
              <el-input v-model="form.uavName" placeholder="请输入无人机名称" maxlength="30" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="序列编号" prop="uavNo">
              <el-input v-model="form.uavNo" placeholder="请输入无人机序列号" maxlength="30" :disabled='this.form.uavId?true:false'/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="所属区域" prop="areaId">
              <treeselect v-model="form.areaId" :options="deptOptions" :show-count="true" placeholder="请选择归属区域" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="机器类型" prop="uavTypeId">
              <el-select v-model="form.uavTypeId" filterable placeholder="请选择机器类型">
                <el-option
                  v-for="dict in uavTypes"
                  :key="dict.uavTypeId"
                  :label="dict.uavTypeName"
                  :value="dict.uavTypeId"
                ></el-option>
              </el-select>
            </el-form-item>
            </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="保险单号" prop="chitNo">
              <el-input v-model="form.chitNo" placeholder="请输入保险单号" maxlength="30" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="购买日期" prop="createDate">
              <el-date-picker v-model="form.createDate" type="date" placeholder="选择日期"  value-format="yyyy-MM-dd" />
            </el-form-item>
          </el-col>
        </el-row>
        
        <el-row>
          <el-col :span="12">
            <el-form-item label="是否入网" prop="netStatus">
              <el-radio-group v-model="form.netStatus">
                <el-radio :label="0">未入网</el-radio>
                <el-radio :label="1">已入网</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="所属用户" prop="userId">
              <el-select v-model="form.userId" filterable placeholder="请选择所属用户">
                <el-option
                  v-for="dict in users"
                  :key="dict.userId"
                  :label="dict.nickName"
                  :value="dict.userId"
                ></el-option>
              </el-select>
            </el-form-item>
            </el-col>
        </el-row>
        
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

        <!--状态信息-->
    <el-dialog title="维保数据" :visible.sync="openDetail" width="600px" append-to-body>
      <el-descriptions title="地址信息" direction="vertical" :column="3" border>
        <el-descriptions-item label="详细地址" >{{row.repairAddress}}</el-descriptions-item>
        <el-descriptions-item label="联系电话" > {{row.phoneNumber}}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>

    <!--维保反馈-->
    <el-dialog title="维保反馈" :visible.sync="openDetailF" width="600px" append-to-body>
      <el-descriptions title="反馈信息" direction="vertical" :column="4" border>
        <el-descriptions-item label="反馈人" :span="2">{{row.fname}}</el-descriptions-item>
        <el-descriptions-item label="反馈时间"  :span="2"> {{row.feedbackDate}}</el-descriptions-item>
        <el-descriptions-item label="反馈描述"  :span="2"> {{row.repairFeedback}}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>


  </div>
</template>

<script>
import {uavRepairList} from "@/api/uav/uavr"
import { listUavTypeTs } from "@/api/uav/uavtype";
import { uavInsert,uavUpdate,uavList,uavDelete,uavInfo,listUavUsers} from "@/api/uav/uav";
import { listUser, getUser, delUser, addUser, updateUser, resetUserPwd, changeUserStatus, deptTreeSelect } from "@/api/system/user";
import { getToken } from "@/utils/auth";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "User",
  dicts: ['sys_normal_disable', 'sys_user_sex'],
  components: { Treeselect },
  data() {
    return {
      openDetail:false,
      openDetailF:false,
      row:{
      },
      d1:['#67C23A','#E6A23C','#F56C6C','#409EFF','#909399'],
      d2:['状态好','状态良','状态差','维保中','已报废'],
      users:[],
      uavTypes:[],
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
        userName: undefined,
        phonenumber: undefined,
        status: undefined,
        areaId: undefined
      },
      // 表单校验
      rules: {
        uavName: [
          { required: true, message: "无人机名称不能为空", trigger: "blur" },
        ],
        uavNo: [
          { required: true, message: "无人机序列号不能为空", trigger: "blur" },
          { min: 3, max: 30, message: '无人机名称长度必须介于 2 和 50 之间', trigger: 'blur' }
        ],
        areaId: [
          { required: true, message: "无人机所属不能为空", trigger: "blur" }
        ],
        uavTypeId: [
          { required: true, message: "无人机类型不能为空", trigger: "blur" }
        ],
        chitNo: [
          { required: true, message: "机器编号不能为空", trigger: "blur" }
        ],
        netStatus: [
          { required: true, message: "入网状态不能为空", trigger: "blur" }
        ],
        createDate: [
          { required: true, message: "购买日期不能为空", trigger: "blur" }
        ]
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
    this.getUsers()
    this.getTypeTs()
    this.getList();
    this.getDeptTree();
    this.getConfigKey("sys.user.initPassword").then(response => {
      this.initPassword = response.msg;
    });
  },
  methods: {
    handleRepairDetail(row){
      this.openDetail = true
      this.row = row
    },
    handleRepairDetailF(row){
      this.openDetailF = true
      this.row = row
    },
    getUsers(){
      this.loading = true;
      listUavUsers({}).then(response => {
          this.users = response.rows;
          this.loading = false;
        }
        );

    },
    /**查询类型列表 */
    getTypeTs(){
      this.loading = true;
      listUavTypeTs({}).then(response => {
          this.uavTypes = response.rows;
          this.loading = false;
        }
        );

    },
    /** 查询用户列表 */
    getList() {
      this.loading = true;
      uavRepairList(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.userList = response.rows;
          this.total = response.total;
          this.loading = false;
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
      this.queryParams.areaId = data.id;
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
    // 表单重置
    reset() {
      this.form = {
        userId: undefined,
        areaId: undefined,
        deptId: undefined,
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      getUser().then(response => {
        this.postOptions = response.posts;
        this.roleOptions = response.roles;
        this.open = true;
        this.title = "新增无人机";
        this.form.password = this.initPassword;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const uavId = row.uavId
      uavInfo(uavId).then(response => {
        this.form = response.data;
        this.postOptions = response.posts;
        this.roleOptions = response.roles;
        this.form.postIds = response.postIds;
        this.form.roleIds = response.roleIds;
        this.open = true;
        this.title = "修改无人机信息";
        this.form.password = "";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.uavId != undefined) {
            uavUpdate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            uavInsert(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const uavId = row.uavId;
      this.$modal.confirm('是否确认删除序列号为"' + row.uavNo + '"的无人机？').then(function() {
        return uavDelete(uavId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/user/export', {
        ...this.queryParams
      }, `user_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "用户导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/user/importTemplate', {
      }, `user_template_${new Date().getTime()}.xlsx`)
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