<template>
<!-- ============================主页头部栏组件============================ -->
    <div class="headerStyle">
        <el-row>
            <el-col :span="12">
                <div class="grid-content ep-bg-purple headerLeft"><el-icon class="headerLeftIMG"><ElementPlus /></el-icon><p>社 区 疫 情 防 控 监 测 管 理 网 站</p></div>
            </el-col>
            <el-col :span="12">
                <div class="grid-content ep-bg-purple-light headerRight">

                    <el-tooltip class="box-item" effect="dark" content="退出当前账户" placement="bottom-end">
                        <div class="box01" text @click="centerDialogVisible = true">
                            <el-icon size="25px"><SwitchButton /></el-icon>
                            <div style="margin-left:5px">退出</div>
                        </div>
                    </el-tooltip>
                    <el-dialog class="dialogStyle" v-model="centerDialogVisible" title="退出当前账户" width="26%" align-center>
                        <div class="exitDialog">
                            <span>确认要退出当前账户吗？</span>
                        </div>
                        <template #footer>
                            <span class="dialog-footer">
                                <el-button type="primary" @click="userExit">确认</el-button>
                                <el-button @click="centerDialogVisible = false">取消</el-button>
                            </span>
                        </template>
                    </el-dialog>                    

                    <el-tooltip class="box-item" effect="dark" content="当前登陆用户" placement="bottom">
                        <div class="box02">
                            <el-icon size="25px"><User /></el-icon>
                            <div style="margin-left:5px">用户：{{userData.userName}}</div>
                        </div>
                    </el-tooltip>

                    <el-tooltip class="box-item" effect="dark" content="当前账户所属社区" placement="bottom">
                        <div class="box02">
                            <el-icon size="25px"><LocationInformation /></el-icon>
                            <div style="margin-left:5px">当前社区：{{userData.communityName}}</div>
                        </div>
                    </el-tooltip>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script lang="ts">
//引入element组件
import { ElMessage } from 'element-plus'
import { reactive, ref } from 'vue'
//引入路由
import router from '../router/index.js'
//引入element图标
import {User,SwitchButton,ElementPlus,LocationInformation} from '@element-plus/icons-vue'
//引入登出接口
import {userExitAPI} from '../http/api/userAPI.js'


export default {

    setup () {
        const userData = reactive({//数据
            userName: sessionStorage.getItem('manageName'),
            communityName: sessionStorage.getItem('communityName'),
        })

        const centerDialogVisible = ref(false)

        const userExit =()=>{ //退出当前账户
            userExitAPI().then(res =>{
                var state =  res.status; //接口状态
                if(state == 200){
                    console.log("退出成功");
                    ElMessage({
                        message: '登出成功!',
                        type: 'success',
                    });
                    sessionStorage.removeItem('manageName');
                    sessionStorage.removeItem('manageLevel');
                    sessionStorage.removeItem('communityName');
                    sessionStorage.removeItem('areaName');
                    sessionStorage.removeItem('buildName');
                    router.push('/');
                }else{
                    ElMessage.error('登出失败！请联系管理员');
                }
            })
        }

        return{
            userExit,
            userData,
            centerDialogVisible
        }
    },

    components:{ //注册组件
        //element图标组件
        User,
        SwitchButton,
        ElementPlus,
        LocationInformation
    }
}
</script>

<style lang="scss" scoped>
.headerStyle{
    width: calc(100vw);
    background-color: #213d5b;
    margin: 0px;
    height: 100%;
    color: #ffffff;
}
.headerLeft{
    display: flex;
    align-items: center;
    line-height: 60px;
    padding-left: 30px;
    p{
        padding-left: 5px;
        margin: 0px;
        font-size: 28px;
    }
}

.headerLeftIMG{
    font-size: 35px;
}
.headerRight{
    display: flex;
    line-height: 60px;
    flex-direction: row-reverse;
    padding-right: 30px;
    align-items: center;
}
.box01{
    display: flex;
    line-height: 60px;
    align-items: center;
}
.box01:hover{
    cursor: pointer;
}
.box02{
    display: flex;
    line-height: 60px;
    align-items: center;
    padding-right: 25px;
}
.box02:hover{
    cursor: pointer;
}
.dialogStyle{
    border-radius: 14px;
    background-color:yellow;
    text-align: center;
}
.exitDialog{
    width: 100%;
    display: flex;
    justify-content: center;
}

.tooltip-base-box {
  width: 600px;
}
.tooltip-base-box .row {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.tooltip-base-box .center {
  justify-content: center;
}
.tooltip-base-box .box-item {
  width: 110px;
  margin-top: 10px;
}
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>