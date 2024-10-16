<template>
    <div class="pageStyle">
        <!-- 查询栏 -->
        <el-form ref="resetFormRef" class="myFormStyle"  :model="selectDataValue">
            <el-row style="margin-bottom: 0px">
                <el-col :span="8">
                    <el-form-item label="账户名称" prop="manageName" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.manageName" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="账户权限" prop="manageLevel" label-width="80px" style="margin-bottom: 10px">
                        <el-select v-model="selectDataValue.manageLevel" placeholder="请选择" clearable>
                            <el-option label="最高权限" value="0" />
                            <el-option label="中级权限" value="1" />
                            <el-option label="普通权限" value="2" />
                        </el-select> 
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="启用状态" prop="isEnable" label-width="80px" style="margin-bottom: 10px">
                        <el-select v-model="selectDataValue.isEnable" placeholder="请选择" clearable>
                            <el-option label="是" value="0" />
                            <el-option label="否" value="1" />
                        </el-select> 
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24" style="text-align: center">
                    <el-button type="primary" @click="queryUserData(resetFormRef)">查询</el-button>
                    <el-button type="primary" @click="resetForm(resetFormRef)">重置</el-button>
                </el-col>
            </el-row>
        </el-form>

        <!-- 数据展示栏 -->
        <el-table :data="dataValue.allData.records" border style="width: 100%" :cell-style="{'text-align':'center'}">
            <el-table-column type="index" label="序号" :index="indexMethod" width="54"/>
            <el-table-column prop="manageCode" label="账户编号" width="180" />
            <el-table-column prop="manageName" label="账户名称" width="180" />
            <el-table-column prop="phone" label="联系电话" width="180" />
            <el-table-column prop="manageLevel" label="账户权限" width="180" />
            <el-table-column prop="isEnable" label="启用状态" width="180" />
            <el-table-column prop="" label="操作">
                <template #default="scope">
                    <el-button type="primary" size="small" @click="Rename(scope.row)">
                        <el-icon><Refresh /></el-icon>重命名
                    </el-button>
                    <el-button type="warning" size="small" auto-insert-space @click="ResetPassword(scope.row)">
                        <el-icon><Edit /></el-icon>修改密码
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页栏 -->
        <el-row>
            <el-col :span="24">
                <div class="paginStyle">
                    <el-pagination :small="true" background layout="total, prev, pager, next" :page-size="Number(dataValue.allData.size)" :total="Number(dataValue.allData.total)"
                        :default-page-size="1" :current-page="selectDataValue.pageCurrent"  @current-change="CurrentChange"/>
                </div>
            </el-col>
        </el-row>
    </div>

    <!-- 重命名按键drawer页面 -->
    <el-drawer v-model="drawer_Name" title="修改账户名称" direction="rtl" close-on-press-escape style="font-weight: 600" :z-index="1000">
        <el-row>
            <el-col :span="18" :offset="3">
                <el-form label-position="top" label-width="100px" :model="drawerNameForm" class="drawerFormStyle">
                    <el-form-item label="账户名称">
                        <el-input v-model="drawerNameForm.manageName" clearable :disabled="Disabled"/>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="18" :offset="3">
                <el-button type="primary" @click="saveBtn_01" :disabled="Disabled">保存</el-button>
                <el-button type="primary" @click="cancelBtn_01()">取消</el-button>
            </el-col>
        </el-row>
    </el-drawer>

    <!-- 修改密码按键drawer页面 -->
    <el-drawer v-model="drawer_Password" title="修改目标账户密码" direction="rtl" close-on-press-escape style="font-weight: 600" :z-index="1000">
        <el-row>
            <el-col :span="18" :offset="3">
                <el-form ref="rulePasswordRef" label-position="top" :model="drawerPasswordData" status-icon label-width="100px" class="drawerFormStyle demo-ruleForm">
                    <el-form-item label="新密码" prop="password">
                        <el-input v-model="drawerPasswordData.password" type="password" autocomplete="off" :disabled="Disabled" show-password clearable/>
                    </el-form-item>
                    <el-form-item label="再次确认" prop="againPassword">
                        <el-input v-model="drawerPasswordData.againPassword" type="password" autocomplete="off" :disabled="Disabled" show-password clearable/>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="18" :offset="3">
                <el-button type="primary" @click="saveBtn_02()" :disabled="Disabled">保存</el-button>
                <el-button type="primary" @click="resetPasswordForm(rulePasswordRef)" :disabled="Disabled">重置</el-button>
                <el-button type="primary" @click="cancelBtn_02()">取消</el-button>
            </el-col>
        </el-row>
    </el-drawer>

</template>

<script lang="ts">
// vue3相关组件
import { reactive, ref, nextTick} from 'vue'
import {Delete,Refresh,Edit,FolderAdd} from '@element-plus/icons-vue'
import { ElNotification, ElMessage, ElMessageBox, } from 'element-plus'
import type { FormInstance } from 'element-plus'

// 自定义局部刷新组件
import { useRouter } from "vue-router";
// 引入查询账户信息接口
import {selectAllUserAPI} from '../../http/api/userAPI.js'
// 引入修改当前账户密码接口
import {updateOtherManagerPasswordAPI} from '../../http/api/userAPI.js'
// 引入修改当前账户密码接口
import {updateOtherManagerNameAPI} from '../../http/api/userAPI.js'


export default {
    setup () {
        const router = useRouter(); //初始化路由（自定义局部刷新）
        // nextTick(() => { // 刷新页面（局部）
        //                     router.replace({
        //                         path: '/redirect' + '/AreaManagement',
        //                     })
        //                 });
        
        const Disabled = ref(false); //用于禁用el-button

        const indexMethod = (index: number) => { //数据序号
            return index + 1;
        }

        // 数据查询展示
        const resetFormRef = ref<FormInstance>();
        const dataValue = reactive({//所有区域数据
            allData:[]
        });
        const selectDataValue = reactive({//输入框数据
            manageName: '',
            manageLevel: null,
            isEnable: null,
            pageCurrent: 1
        });

        //数据查询
        const queryUserData = (formEl: FormInstance | undefined) => { //查询按钮
            if (!formEl) return
            formEl.validate((valid) => {
                if (valid) {
                    selectAllUserAPI(selectDataValue).then(res =>{ //动态查询数据并渲染到表格中
                        var state =  res.status; //接口状态
                        var result = res.data; //接口返回值
                        console.log("查询数据："+JSON.stringify(selectDataValue));

                        console.log(res.request);

                        if(state === 200){//实现渲染
                            dataValue.allData = res.data;
                        }else{
                            ElNotification({
                                title: '操作失败',
                                message: '请和系统管理员联系',
                                type: 'error',
                                position: 'bottom-right',
                                duration: 4000,
                            });
                        }
                    })
                } else {
                    console.log('error submit!')
                    return false
                }
            })
        };
        const resetForm = (formEl: FormInstance | undefined) => { //重置按钮
            if (!formEl) return
            formEl.resetFields()
        };
        
        //数据展示
        const sellectAllUserData = selectAllUserAPI(selectDataValue).then(res =>{ //数据显示
            //console.log("数据："+JSON.stringify(res))
            dataValue.allData = res.data;
            console.log("数据："+JSON.stringify(dataValue.allData));
        });
        const CurrentChange = (currentPage: any) =>{ //分页改变当前页数
            selectDataValue.pageCurrent = currentPage;
            console.log("页数："+selectDataValue.pageCurrent)
            selectAllUserAPI(selectDataValue).then(res =>{
                dataValue.allData = res.data;
            })
        };

        //drawer抽屉组件（重命名）
        const drawer_Name = ref(false);
        const drawerNameForm = reactive({
            manageName: '',
            mname: '',
            manageCode: '',
        })
        const Rename = (row: any) =>{ //打开【重命名】页面
            drawer_Name.value = true;
            drawerNameForm.manageName = row.manageName;
            drawerNameForm.mname = row.manageName;
            drawerNameForm.manageCode = row.manageCode;

            if(row.manageName == sessionStorage.getItem("manageName")){ //满足条件开启禁止
                Disabled.value = true
            }else{
                Disabled.value = false
            }
            
        };
        const saveBtn_01 = () =>{ //保存按键
            if(drawerNameForm.manageName == '' || drawerNameForm.manageName == null){
                ElMessage({
                    message: '用户名不能为空！',
                    type: 'warning',
                })
            }else if(drawerNameForm.manageName == drawerNameForm.mname){
                ElMessage({
                    message: '不能使用同一个名称！',
                    type: 'warning',
                })
            }else{
                ElMessageBox.confirm(
                    '确认要修改当前账户名称?',
                    '注意',
                    {
                        confirmButtonText: '确认',
                        cancelButtonText: '取消',
                        type: 'warning',
                    }
                ).then(() => {
                    // 保存
                    updateOtherManagerNameAPI(drawerNameForm).then(res =>{
                        var state =  res.status; //接口状态
                        var result = res.data; //接口返回值
                        if(state == 200){
                            if(result == 3){
                                drawer_Name.value = false;
                                ElNotification({
                                    title: '操作失败！',
                                    message: '该账户名称已存在',
                                    type: 'warning',
                                    position: 'bottom-right',
                                    duration: 4000,
                                });
                            }else if(result == 1){
                                drawer_Name.value = false;

                                ElNotification({
                                    title: '操作成功！',
                                    message: '已成功更新目标数据',
                                    type: 'success',
                                    position: 'bottom-right',
                                    duration: 4000,
                                });

                                nextTick(() => { // 刷新页面（局部）
                                    router.replace({
                                        path: '/redirect' + '/CurrentAccount',
                                    })
                                });
                                
                            }else{
                                drawer_Name.value = false;
                                ElNotification({
                                    title: '操作失败',
                                    message: '请和系统管理员联系',
                                    type: 'error',
                                    position: 'bottom-right',
                                    duration: 4000,
                                })
                            }
                        }
                    })
                    drawer_Name.value = false;
                    
                }).catch(() => {
                    // catch error
                })
            }   
        }
        const cancelBtn_01 = () =>{ //取消按键
            drawer_Name.value = false;
        }

        //drawer抽屉组件（修改密码）
        const rulePasswordRef = ref<FormInstance>();

        const drawer_Password = ref(false);
        const drawerPasswordData = reactive({
            password: '',
            againPassword: '',
            manageName: '',
        });

        const ResetPassword = (row: any) =>{ //打开【修改密码】页面
            drawer_Password.value = true;
            drawerPasswordData.manageName = row.manageName;

            if(row.manageName == sessionStorage.getItem("manageName")){ //满足条件开启禁止
                Disabled.value = true
            }else{
                Disabled.value = false
            }
        }

        const saveBtn_02 = () => { //保存按键
            if(drawerPasswordData.password == '' || drawerPasswordData.password == null){
                ElMessage({
                    message: '请输入新密码！',
                    type: 'warning',
                })
            }else if(drawerPasswordData.againPassword == '' || drawerPasswordData.againPassword == null){
                ElMessage({
                    message: '请再次输入密码！',
                    type: 'warning',
                })
            }else if(drawerPasswordData.password != drawerPasswordData.againPassword){
                ElMessage({
                    message: '两次密码不一致，请再次确认！',
                    type: 'warning',
                })
            }else{

                ElMessageBox.confirm(
                    '确认要修改当前账户密码?',
                    '注意',
                    {
                        confirmButtonText: '确认',
                        cancelButtonText: '取消',
                        type: 'warning',
                    }
                ).then(() => {
                    updateOtherManagerPasswordAPI(drawerPasswordData).then(res =>{
                        var state =  res.status; //接口状态
                        var result = res.data; //接口返回值

                        if(state == 200){
                            if(result == 1){
                                drawer_Password.value = false;
                                ElNotification({
                                    title: '操作成功！',
                                    message: '密码修改成功',
                                    type: 'success',
                                    position: 'bottom-right',
                                    duration: 4000,
                                });
                                drawerPasswordData.password = '';
                                drawerPasswordData.againPassword = '';
                                drawerPasswordData.manageName = '';
                            }else{
                                drawer_Password.value = false;
                                ElNotification({
                                    title: '操作失败',
                                    message: '请和系统管理员联系',
                                    type: 'error',
                                    position: 'bottom-right',
                                    duration: 4000,
                                })
                                drawerPasswordData.password = '';
                                drawerPasswordData.againPassword = '';
                                drawerPasswordData.manageName = '';
                            }
                        }
                    });
                }).catch(() => {
                    // catch error
                })
            }
        }

        const resetPasswordForm = (formEl: FormInstance | undefined) => { //重置按键
            if (!formEl) return
            formEl.resetFields()
        }

        const cancelBtn_02 = () =>{ //取消按键
            drawerPasswordData.password = '';
            drawerPasswordData.againPassword = '';
            drawerPasswordData.manageName = '';
            drawer_Password.value = false;
        }

        return {
            router,
            resetFormRef,
            dataValue,
            selectDataValue,
            sellectAllUserData,
            drawer_Name,
            drawerNameForm,
            Disabled,
            rulePasswordRef,
            drawer_Password,
            drawerPasswordData,
            indexMethod,
            queryUserData,
            resetForm,
            CurrentChange,
            Rename,
            saveBtn_01,
            cancelBtn_01,
            ResetPassword,
            resetPasswordForm,
            saveBtn_02,
            cancelBtn_02,
        }
    },

    components:{ //注册组件
        //element图标组件
        Delete,
        Refresh,
        Edit,
        FolderAdd
    }
}
</script>

<style lang="scss" scoped>
.pageStyle{
    padding: 0px;
}
.myFormStyle{
    background-color: #ffffff;
    color: #333333;
    border: 1px solid #ebeef5;
    padding: 10px;
    margin-bottom: 1px;
}
.paginStyle{
    background-color: #ffffff;
    border: 1px solid #ebeef5;
    padding: 4px 10px 4px 10px;
    margin-top: 1px;
}
.addStyle{
    /* background-color: #e9f2fbf4; */
    background-color: #ffffff;
    border: 1px solid #ebeef5;
}
</style>

<style lang="scss" scoped>
.pageStyle{
    padding: 0px 0px 0px 0px;
}
.el-button--text {
  margin-right: 15px;
}
.el-select {
  width: 300px;
}
.el-input {
  width: 300px;
}
.dialog-footer{ //设置Dialog对话框按钮居中
    display: flex;
    justify-content: center; 
}
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>

<style lang="scss" scoped>
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>