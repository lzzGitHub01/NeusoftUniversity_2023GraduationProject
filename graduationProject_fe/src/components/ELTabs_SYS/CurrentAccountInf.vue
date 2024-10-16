<template>
    <div>
        <el-row class="row-bg" justify="space-evenly">
            <el-col :span="22">
                <el-card shadow="hover">
                    <el-row>
                        <el-col :span="14">
                            <div class="titLeft01">当前登陆账户：{{dataValue.currentAccountData['manageName']}}</div>
                        </el-col>
                        <el-col :span="10">
                            <div class="titRight">
                                <el-button type="info" @click="opendrawer()">重命名</el-button>
                                <el-button type="warning" @click="openPasswordDrawer()">密码修改</el-button>
                            </div>
                        </el-col>
                    </el-row>
                </el-card>
            </el-col>
        </el-row>

        <el-row class="row-bg" justify="space-evenly" style="margin-top:10px">
            <el-col :span="22">
                <el-card shadow="hover">
                    <el-row class="rowStyle">
                        <el-col :span="4">
                            <div class="titLeft">当前账户ID</div>
                        </el-col>
                        <el-col :span="20">
                            <div class="neiRong">{{dataValue.currentAccountData['id']}}</div>
                        </el-col>
                    </el-row>
                    <el-row class="rowStyle">
                        <el-col :span="4">
                            <div class="titLeft">当前账户编码</div>
                        </el-col>
                        <el-col :span="20">
                            <div class="neiRong">{{dataValue.currentAccountData['manageCode']}}</div>
                        </el-col>
                    </el-row>
                    <el-row class="rowStyle">
                        <el-col :span="4">
                            <div class="titLeft">账户权限等级</div>
                        </el-col>
                        <el-col :span="20">
                            <div class="neiRong">{{dataValue.currentAccountData['manageLevel']}}</div>
                        </el-col>
                    </el-row>
                    <el-row class="rowStyle">
                        <el-col :span="4">
                            <div class="titLeft">联系电话</div>
                        </el-col>
                        <el-col :span="20">
                            <div class="neiRong">{{dataValue.currentAccountData['phone']}}</div>
                        </el-col>
                    </el-row>
                    <el-row class="rowStyle">
                        <el-col :span="4">
                            <div class="titLeft">最近账户更新时间</div>
                        </el-col>
                        <el-col :span="20">
                            <div class="neiRong">{{dataValue.currentAccountData['updatedAt']}}</div>
                        </el-col>
                    </el-row>
                    <el-row class="rowStyle">
                        <el-col :span="4">
                            <div class="titLeft">本次账户登陆时间</div>
                        </el-col>
                        <el-col :span="20">
                            <div class="neiRong">{{dataValue.currentAccountData['loginAt']}}</div>
                        </el-col>
                    </el-row>
                    <el-row class="rowStyle">
                        <el-col :span="4">
                            <div class="titLeft">最近一次登陆时间</div>
                        </el-col>
                        <el-col :span="20">
                            <div class="neiRong">{{dataValue.currentAccountData['lastLoginAt']}}</div>
                        </el-col>
                    </el-row>
                </el-card>
            </el-col>
        </el-row>
    </div>

    <!-- 重命名按键drawer页面 -->
    <el-drawer v-model="drawer_01" title="当前账户重命名" direction="rtl" close-on-press-escape style="font-weight: 600" :z-index="1000">
        <el-row>
            <el-col :span="18" :offset="3">
                <el-form label-position="top" label-width="100px" :model="drawerForm" class="drawerFormStyle">
                    <el-form-item label="账户名称">
                        <el-input v-model="drawerForm.manageName" clearable/>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="18" :offset="3">
                <el-button type="primary" @click="saveBtn_01">保存</el-button>
                <el-button type="primary" @click="cancelBtn_01()">取消</el-button>
            </el-col>
        </el-row>
    </el-drawer>

    <!-- 修改密码按键drawer页面 -->
    <el-drawer v-model="drawer_02" title="修改当前账户密码" direction="rtl" close-on-press-escape style="font-weight: 600" :z-index="1000">
        <el-row>
            <el-col :span="18" :offset="3">
                <el-form ref="rulePasswordRef" label-position="top" :model="drawerPasswordData" status-icon label-width="100px" class="drawerFormStyle demo-ruleForm">
                    <el-form-item label="新密码" prop="password">
                        <el-input v-model="drawerPasswordData.password" type="password" autocomplete="off" show-password clearable/>
                    </el-form-item>
                    <el-form-item label="再次确认" prop="againPassword">
                        <el-input v-model="drawerPasswordData.againPassword" type="password" autocomplete="off" show-password clearable/>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="18" :offset="3">
                <el-button type="primary" @click="saveBtn_02()">保存</el-button>
                <el-button type="primary" @click="resetForm(rulePasswordRef)">重置</el-button>
                <el-button type="primary" @click="cancelBtn_02()">取消</el-button>
            </el-col>
        </el-row>
    </el-drawer>

    <!-- 倒计时页面dialog -->
    <el-dialog v-model="centerDialogVisible" title="系统即将退出！" width="30%" :z-index="1000" :show-close="false"
        :close-on-click-modal="false" :close-on-press-escape="false">
        <span>
            当前账户将在{{sms.count}}秒后退出，请再退出后重新登陆
        </span>
    </el-dialog>

</template>

<script lang="ts">
// vue3相关组件
import { reactive, ref, nextTick} from 'vue'
import {Delete,Postcard,Edit,FolderAdd} from '@element-plus/icons-vue'
import type { FormInstance } from 'element-plus'
import { ElMessage, ElMessageBox, ElNotification} from 'element-plus'

// 自定义局部刷新组件
import { useRouter } from "vue-router";
// 引入查询账户信息接口
import {selectCurrentAccountAPI} from '../../http/api/userAPI.js'
// 引入当前登陆账户修改自己的基础数据接口
import {updateCurrentAccountAPI} from '../../http/api/userAPI.js'
// 引入修改当前账户密码接口
import {changePasswordAPI} from '../../http/api/userAPI.js'
//引入登出接口
import {userExitAPI} from '../../http/api/userAPI.js'

export default {
    setup () {
        const router = useRouter(); //初始化路由（自定义局部刷新）
        // nextTick(() => { // 刷新页面（局部）
        //                     router.replace({
        //                         path: '/redirect' + '/AreaManagement',
        //                     })
        //                 });

        // 倒计时
        const sms = reactive({ //计时器
            disabled: false,
            total: 8,
            count: 0
        });
        const timerHandler = () => { //计时器处理器
            sms.count = sms.total;
            sms.disabled = true;
            let timer = setInterval(() => {
                if (sms.count > 0 && sms.count <= sms.total) {
                    sms.count--;
                    
                    if(sms.count == 0){ // 计时结束时要执行的方法
                        centerDialogVisible.value = false;
                        userExitAPI().then(res =>{ //调用登出接口
                        var state =  res.status; //接口状态
                            if(state == 200){
                                console.log("退出成功");
                                ElMessage({
                                    message: '登出成功，请重新登陆！',
                                    type: 'success',
                                });
                                sessionStorage.removeItem('manageName');
                                sessionStorage.removeItem('communityName');
                                sessionStorage.removeItem('manageLevel');
                                router.push('/');
                            }else{
                                ElMessage.error('登出失败！请联系管理员');
                            }
                        })
                    }
                } else {
                    sms.disabled = false
                    clearInterval(timer)
                };
            }, 1000)
        };
        const centerDialogVisible = ref(false);

        // 数据集
        const dataValue = reactive({
            currentAccountData: [], //当前登陆账户信息
            communityName: sessionStorage.getItem('communityName'), //当前账户所属区域
        })

        // 查询当前账户信息
        const currentAccount = selectCurrentAccountAPI().then(res =>{
            dataValue.currentAccountData = res.data[0];
            console.log(JSON.stringify(dataValue.currentAccountData))
            // console.log(dataValue.currentAccountData['id'])
        })

        //drawer抽屉组件（重命名）
        const drawer_01 = ref(false);
        const drawerForm = reactive({
            manageName: '',
        })
        const opendrawer = () =>{ //打开【重命名】页面
            drawer_01.value = true;
            drawerForm.manageName = dataValue.currentAccountData['manageName'];
        }

        const saveBtn_01 = () =>{ //保存按键
            if(drawerForm.manageName == '' || drawerForm.manageName == null){
                ElMessage({
                    message: '用户名不能为空！',
                    type: 'warning',
                })
            }else if(drawerForm.manageName == dataValue.currentAccountData['manageName']){
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
                    updateCurrentAccountAPI(drawerForm).then(res =>{
                        var state =  res.status; //接口状态
                        var result = res.data; //接口返回值
                        if(state == 200){
                            if(result == 3){
                                drawer_01.value = false;
                                ElNotification({
                                    title: '操作失败！',
                                    message: '该账户名称已存在',
                                    type: 'warning',
                                    position: 'bottom-right',
                                    duration: 4000,
                                });
                            }else if(result == 1){
                                drawer_01.value = false;

                                ElNotification({
                                    title: '操作成功！',
                                    message: '已成功更新目标数据',
                                    type: 'success',
                                    position: 'bottom-right',
                                    duration: 4000,
                                });

                                //更新session中的数据
                                sessionStorage.setItem('manageName',drawerForm.manageName);

                                nextTick(() => { // 刷新页面（局部）
                                    router.replace({
                                        path: '/redirect' + '/CurrentAccount',
                                    })
                                });
                            }else{
                                drawer_01.value = false;
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
                    drawer_01.value = false;
                }).catch(() => {
                    // catch error
                })
            }   
        }
        const cancelBtn_01 = () =>{ //取消按键
            drawer_01.value = false;
        }

        //drawer抽屉组件（修改密码）
        const rulePasswordRef = ref<FormInstance>();

        const drawer_02 = ref(false);
        const drawerPasswordData = reactive({
            password: '',
            againPassword: '',
        });

        const openPasswordDrawer = () =>{ //打开【修改密码】页面
            drawer_02.value = true;
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
                    changePasswordAPI(drawerPasswordData).then(res =>{
                        var state =  res.status; //接口状态
                        var result = res.data; //接口返回值

                        if(state == 200){
                            if(result == 1){
                                drawer_02.value = false;
                                ElNotification({
                                    title: '操作成功！',
                                    message: '密码修改成功',
                                    type: 'success',
                                    position: 'bottom-right',
                                    duration: 4000,
                                });

                                //开启倒计时，退出系统
                                centerDialogVisible.value = true;
                                timerHandler();
                            }else{
                                drawer_02.value = false;
                                ElNotification({
                                    title: '操作失败',
                                    message: '请和系统管理员联系',
                                    type: 'error',
                                    position: 'bottom-right',
                                    duration: 4000,
                                })
                            }
                        }
                    });
                }).catch(() => {
                    // catch error
                })
            }
        }

        const resetForm = (formEl: FormInstance | undefined) => { //重置按键
            if (!formEl) return
            formEl.resetFields()
        }

        const cancelBtn_02 = () =>{ //取消按键
            drawerPasswordData.password = '';
            drawerPasswordData.againPassword = '';
            drawer_02.value = false;
        }

        return {
            sms,
            router,
            dataValue,
            currentAccount,
            drawer_01,
            drawerForm,
            drawer_02,
            drawerPasswordData,
            rulePasswordRef,
            centerDialogVisible,
            timerHandler,
            opendrawer,
            cancelBtn_01,
            saveBtn_01,
            openPasswordDrawer,
            cancelBtn_02,
            saveBtn_02,
            resetForm,
        }

    },

    components:{ //注册组件
        //element图标组件
        Delete,
        Postcard,
        Edit,
        FolderAdd
    },
}
</script>

<style lang="scss" scoped>
.titLeft01{
    text-align: left;
    color: #6b778c;
    font-size: 20px;
    font-weight: 600;
}
.titLeft{
    text-align: left;
    color: #6b778c;
    font-size: 17px;
    font-weight: 550;
}
.titRight{
    text-align: right;
}
.rowStyle{
    margin-bottom:12px
}
.neiRong{
    text-align: left;
    color: #a7a6a6;
    font-size: 17px;
    font-weight: 500;
}
</style>

<style lang="scss" scoped>
.drawerFormStyle{
    max-width: 320px;
    text-align: center;
    // color: #6b778c;
    font-weight: 500;
}
</style>
