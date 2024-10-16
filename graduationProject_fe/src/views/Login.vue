<template>
<div class="style">
    <div class="common-layout">
        <el-row>
            <el-col :span="16"><div class="grid-content ep-bg-purple" /></el-col>
            <el-col :span="8">
                <div class="grid-content ep-bg-purple inputDiv">
                    <div class="myForm">
                        <el-form ref="ruleFormRef" :model="dataValue" status-icon :rules="rules" label-width="0px" hide-required-asterisk @submit="userLogin">
                            <!-- label="账户名称" label="账户密码"-->
                            <el-row>
                                <el-col :span="2"></el-col>
                                <el-col :span="3"><el-divider></el-divider></el-col>
                                <el-col :span="7"><div class="textStyle">账号密码登陆</div></el-col>
                                <el-col :span="10"><el-divider></el-divider></el-col>
                                <el-col :span="2"></el-col>
                            </el-row>  
                            <el-form-item prop="manageName" label-width="46px" class="special">
                                <el-input class="elInputStyle" v-model="dataValue.manageName" placeholder="账户名称" clearable />
                            </el-form-item>

                            <el-form-item prop="password" label-width="46px" class="special">
                                <el-input class="elInputStyle" v-model="dataValue.password" type="password" placeholder="账户密码" show-password/>
                            </el-form-item>

                            <div class="btnStyle">
                                <el-button class="myBtn" type="primary" @click="submitForm(ruleFormRef)">登陆</el-button>
                            </div>
                            <div class="btnStyle">
                                <el-button class="myBtn" @click="resetForm(ruleFormRef)">重置</el-button> 
                            </div>
                        </el-form>
                    </div>
                </div>
            </el-col>
        </el-row>
    </div> 
</div>

</template>

<script lang="ts">
//form表单Start
import { reactive, ref } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import { ElMessage } from 'element-plus'
//引入路由
import router from '../router/index.js'
//引入登陆接口
import {userLoginAPI} from '../http/api/userAPI.js'
//引入获取用户等级接口
import {getUserLevelAPI} from '../http/api/userAPI.js'
//引入获取用户所属社区接口
import {selectUserCommunityAPI} from '../http/api/userAPI.js'
//引入获取用户所属区域接口
import {selectUserAreaAPI} from '../http/api/userAPI.js'
//引入获取用户所属楼组接口
import {selectUserBuildAPI} from '../http/api/userAPI.js'

//暴漏一个定义好的组件
export default {
    name: 'Login',
    //setup组合API的入口
    setup (){
        
        const ruleFormRef = ref<FormInstance>();
        const formSize = ref('default');

        const dataValue = reactive({//数据
            manageName: '',
            password: '',
        })

        const rules = reactive<FormRules>({//表单校验
            manageName: [
                {
                    required: true,
                    message: '请输入用户名',
                    trigger: 'change',  
                },
            ],
            password: [
                {
                    required: true,
                    message: '请输入密码',
                    trigger: 'change',
                },
            ],
        })

        const submitForm = async (formEl: FormInstance | undefined) => {
            if (!formEl) return
            await formEl.validate((valid, fields) => {
                if (valid) {
                    // 提交接口编写处
                    userLoginAPI(dataValue).then(res =>{
                        var state =  res.status; //接口状态
                        var result = res.data; //接口返回值

                        if(state == 200){           
                            if(result == 1){
                                // 将当前用户信息存放在session中
                                var getUserInf = JSON.parse(res.config.data);
                                sessionStorage.setItem('manageName',getUserInf.manageName);

                                //获取当前账户所在社区，存于session
                                selectUserCommunityAPI(dataValue.manageName).then(res =>{
                                    sessionStorage.setItem('communityName',res.data)
                                })
                                
                                //调用获取当前用户等级的接口
                                getUserLevelAPI(dataValue.manageName).then(res =>{
                                    //console.log("用户等级："+JSON.stringify(res))
                                    sessionStorage.setItem('manageLevel',res.data)

                                    var userLevel = Number(sessionStorage.getItem('manageLevel'));
                                        
                                        if(userLevel == 0){
                                            router.push('/homePage_LH');
                                        }else if(userLevel == 1){
                                            //获取当前账户所在社区，存于session
                                            selectUserAreaAPI(dataValue.manageName).then(res =>{
                                                sessionStorage.setItem('areaName',res.data)
                                            })
                                            router.push('/homePage_LM');
                                        }else if(userLevel == 2){
                                            //获取当前账户所在社区，存于session
                                            selectUserAreaAPI(dataValue.manageName).then(res =>{
                                                sessionStorage.setItem('areaName',res.data)
                                            })
                                            selectUserBuildAPI(dataValue.manageName).then(res =>{
                                                sessionStorage.setItem('buildName',res.data)
                                            })
                                            router.push('/homePage_LN');
                                        }else if(userLevel == 3){
                                            //获取当前账户所在社区，存于session
                                            selectUserAreaAPI(dataValue.manageName).then(res =>{
                                                sessionStorage.setItem('areaName',res.data)
                                            })
                                            router.push('/homePage_SYS');
                                        }
                                })
                                ElMessage({
                                    message: '登陆成功！  '+dataValue.manageName+'欢迎回来！',
                                    type: 'success',
                                });

                            };
                            if(result == 2){
                                ElMessage.error('用户不存在或未启用');
                            };
                            if(result == 0){
                                ElMessage.error('用户名或密码错误');
                            };
                        }
                        
                    })
                    //userLogin(dataValue)
                } else {                   
                    // 失败弹窗编写处
                }
            })
        };

        const resetForm = (formEl: FormInstance | undefined) => {
            if (!formEl) return
            formEl.resetFields()
        };

        // 获取表单元素
        const formEl = ref<HTMLFormElement | null>(null);

        const userLogin = () => {
            formEl.value!.validate().then((ok: boolean) =>{
                console.log(ok);
            })
        };

        return{ //返回一个对象（const对象）
            ruleFormRef,
            formSize,
            dataValue,
            rules,
            submitForm,
            resetForm,
            formEl,
            userLogin,
            getUserLevelAPI
        }
    },

    //注册组件
    components:{
        //注册子组件
    },

    data(){
        return{}
    },

    //定义方法
    methonds:{

    }
}

</script>

<style lang="scss" scoped>
.style{
    // background-color:  #A8D2FF;
    //background-image:linear-gradient(120deg,#e0c3fc 2%, #8ec5fc 100%);
    background-image: url("@/assets/loginImg.jpg");
    background-size: cover;
    height: 100%;
    
}
.inputDiv{
    width: auto;
    //height: 300px;
    padding-top: 20px;
    padding-bottom: 20px;
    padding-left: 70px;
    padding-right: 70px;
    //padding-top: 100px;
    border: 0px solid #FCF7F8;
    //border-radius: 14px;
    //background-color: #FCF7F8;
    //background:rgba(0,0,0,.5);
    background: rgba($color: #000000, $alpha: .4);
    height: calc(100vh - 40px);
    display: flex;
}
.myForm{
    width: 372px;
    height: 300px;
    border: 0px solid #FCF7F8;
    border-radius: 10px;
    background: rgba($color: #FCF7F8, $alpha: .6);
    //background-color: #FCF7F8;
    padding-top: 18px;
    margin-top: 170px;
}
.textStyle{
    height: 50px;
    line-height: 50px;
    color: #ffff;
    font-weight: 600px;
    font-size: 18px;
}
.elInputStyle{
    width: 280px;
    // margin-left: 46px;
    // margin-right: 41px;
}
.btnStyle{
    width: 372px;
    display: flex;
    justify-content: center;
}
.myBtn{
    width: 280px;
    margin-bottom: 18px;
}
</style>

<style lang="scss">
.special{
    .el-form-item__label{
        font-weight: 600;
        color: #ffff;
    }
}
</style>