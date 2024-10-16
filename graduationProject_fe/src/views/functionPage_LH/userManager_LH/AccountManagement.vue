<template>
    <div class="pageStyle">
        <!-- 查询栏 -->
        <el-form ref="resetFormRef" class="myFormStyle"  :model="selectDataValue">
            <el-row style="margin-bottom: 0px">
                <el-col :span="8">
                    <el-form-item label="账户ID" prop="id" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.id" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="账户编号" prop="manageCode" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.manageCode" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="账户名称" prop="manageName" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.manageName" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row style="margin-bottom: 0px">
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
                <el-col :span="8"></el-col>
            </el-row>
            <el-row>
                <el-col :span="24" style="text-align: center">
                    <el-button type="primary" @click="queryUserData(resetFormRef)">查询</el-button>
                    <el-button type="primary" @click="resetForm(resetFormRef)">重置</el-button>
                </el-col>
            </el-row>
        </el-form>

        <!-- 新增数据 -->
        <el-row class="addStyle" style="margin-bottom: 0px">
            <el-col :span="1.5"><div class="addDivStyle">信息列表</div></el-col>
            <el-col :span="5">
                <div class="flexDiv" @click="openAddUserData()">
                    <div class="addIconStyle"><el-icon><FolderAdd /></el-icon></div>
                    <div class="addTextStyle">新增</div>
                </div> 
            </el-col>
            <el-col :span="1.5"></el-col>
        </el-row>

        <!-- 数据展示栏 -->
        <el-table :data="dataValue.allData.records" border style="width: 100%" :cell-style="{'text-align':'center'}" :default-sort="{ prop: 'id', order: 'ascending' }">
            <el-table-column type="index" label="序号" fixed="left" :index="indexMethod" width="54"/>
            <el-table-column prop="id" label="账户ID" width="200" />
            <el-table-column prop="manageCode" label="账户编号" width="180" />
            <el-table-column prop="manageName" label="账户名称" width="180" />
            <el-table-column prop="phone" label="联系电话" width="180" />
            <el-table-column prop="manageLevel" label="账户权限" width="120" />
            <el-table-column prop="isEnable" label="启用状态" width="120" />
            <el-table-column prop="areaCode" label="管理区域" width="180" v-if="true"/>
            <el-table-column prop="buildCode" label="管理楼组" width="180" v-if="true"/>
            <el-table-column prop="" fixed="right" label="操作" width="242">
                <template #default="scope">
                    <el-button type="primary" size="small" @click="Details(scope.row)"><el-icon><Postcard /></el-icon>详情</el-button>
                    
                    <el-button type="warning" size="small" auto-insert-space @click="DialogVisible(scope.row)"><el-icon><Edit /></el-icon>修改</el-button>

                    <el-popconfirm width="200" auto-insert-space confirm-button-text="是" cancel-button-text="否" title="是否删除当前数据" icon-color="#626AEF" @confirm="confirmEvent(scope.row,scope.$index)" @cancel="cancelEvent">
                        <template #reference>
                            <el-button type="danger" size="small"><el-icon><Delete /></el-icon>删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <el-row>
            <el-col :span="24">
                <div class="paginStyle">
                    <el-pagination :small="true" background layout="total, prev, pager, next" :page-size="Number(dataValue.allData.size)" :total="Number(dataValue.allData.total)"
                        :default-page-size="1" :current-page="selectDataValue.pageCurrent"  @current-change="CurrentChange"/>
                </div>
            </el-col>
        </el-row>
    </div>

    <!-- 详情按键的Dialog页面 -->
    <el-dialog v-model="dialogFormVisible" title="账户信息详情" width="35%" :append-to-body=true :draggable=true :modal-append-to-body=false align-center>
        <el-form :model="detailForm">
            <el-row>
                <el-col :span="24">
                    <el-form-item label="账户ID" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.id" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>             
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="账户编号" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.manageCode" autocomplete="off" disabled />
                    </el-form-item>                    
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="账户名称" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.manageName" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="联系电话" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.phone" autocomplete="off" disabled />
                    </el-form-item>                 
                </el-col>  
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="账户权限" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.manageLevel" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="启用状态" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.isEnable" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <template #footer>
            <span class="dialog-footer"> 
                <el-button type="primary" @click="dialogFormVisible = false">确认</el-button>
            </span>
        </template>
    </el-dialog>

    <!-- 修改按键的Dialog页面 -->
    <el-dialog v-model="centerDialogVisible" title="账户信息修改" width="35%" :append-to-body=true :draggable=true :modal-append-to-body=false :z-index="1000" align-center>
        <el-form :model="detailedForm">
            <el-row>
                <el-col :span="24">
                    <el-form-item label="账户ID" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.id" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>             
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="账户编号" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.manageCode" autocomplete="off" disabled />
                    </el-form-item>                    
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="账户名称" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.manageName" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="联系电话" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.phone" autocomplete="off"/>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="账户权限" :label-width="formLabelWidth">
                        <el-select v-model="detailedForm.manageLevel" placeholder="请选择" :disabled="selectDisabled" clearable>
                            <el-option label="最高权限" value="0" />
                            <el-option label="中级权限" value="1" />
                            <el-option label="普通权限" value="2" />
                        </el-select> 
                    </el-form-item>
                </el-col>
            </el-row>
            <!-- <el-row>
                <el-col :span="24">
                    <el-form-item label="所属区域" :label-width="formLabelWidth">
                        <el-select v-model="detailedForm.areaCode" placeholder="please select areaName" clearable>
                            <el-option v-for="item in dataValue.areaSelectData" :label="item.areaName" :value="item.areaCode"/>
                        </el-select>
                    </el-form-item>   
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="当前楼组" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.buildCode" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="所属楼组" :label-width="formLabelWidth">
                        <el-select v-model="detailedForm.buildCode" placeholder="please select biold" multiple clearable>
                            <el-option v-for="item in dataValue.buildSelectData" :label="item.buildName" :value="item.buildCode"/>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row> -->
            <el-row>
                <el-col :span="24">
                    <el-form-item label="启用状态" :label-width="formLabelWidth">
                        <el-select v-model="detailedForm.isEnable" placeholder="请选择" :disabled="selectDisabled" clearable>
                            <el-option label="是" value="0" />
                            <el-option label="否" value="1" />
                        </el-select> 
                    </el-form-item>                
                </el-col>  
            </el-row>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="updateUserInf(detailedForm)">保存</el-button>
                <el-button @click="centerDialogVisible = false">取消</el-button>
            </span>
        </template>
    </el-dialog>

    <!-- 新增按键的Dialog页面 -->
    <el-dialog v-model="addDialogVisible" title="新增账户" width="35%" :append-to-body=true :draggable=true :modal-append-to-body=false :z-index="1000" align-center>
        <el-form :model="addUserDataValue">
            <el-row>
                <el-col :span="24">
                    <el-form-item label="账户名称" :label-width="formLabelWidth">
                        <el-input v-model="addUserDataValue.manageName" placeholder="请输入" />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="联系电话" :label-width="formLabelWidth">
                        <el-input v-model="addUserDataValue.phone" placeholder="请输入" />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="账户权限" :label-width="formLabelWidth">
                        <el-select v-model="addUserDataValue.manageLevel" @change="levelChange()" placeholder="请选择" clearable>
                            <el-option label="最高权限" value="0" />
                            <el-option label="中级权限" value="1" />
                            <el-option label="普通权限" value="2" />
                        </el-select> 
                    </el-form-item>                
                </el-col>  
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="所属区域" :label-width="formLabelWidth">
                        <el-select v-model="addUserDataValue.areaCode" placeholder="请选择" clearable :disabled="areaControl">
                            <el-option v-for="item in dataValue.areaSelectData" :label="item.areaName" :value="item.areaCode"/>
                        </el-select>
                    </el-form-item>   
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="所属楼组" :label-width="formLabelWidth">
                        <el-select v-model="addUserDataValue.buildCode" placeholder="请选择" multiple clearable :disabled="buildControl">
                            <el-option v-for="item in dataValue.buildSelectData" :label="item.buildName" :value="item.buildCode"/>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="启用状态" :label-width="formLabelWidth">
                        <el-select v-model="addUserDataValue.isEnable" placeholder="请选择" clearable>
                            <el-option label="是" value="0" />
                            <el-option label="否" value="1" />
                        </el-select> 
                    </el-form-item>                
                </el-col>  
            </el-row>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="addNewUser(addUserDataValue)">保存</el-button>
                <el-button @click="addDialogVisible = false">取消</el-button>
            </span>
        </template>
    </el-dialog>

</template>

<script lang="ts">
// vue3相关组件
import { reactive, ref, nextTick} from 'vue'
import {Delete,Postcard,Edit,FolderAdd} from '@element-plus/icons-vue'
import { ElMessage, ElNotification} from 'element-plus'
import type { FormInstance } from 'element-plus'

// 自定义局部刷新组件
import { useRouter } from "vue-router";
// 引入查询账户信息接口
import {selectAllUserAPI} from '../../../http/api/userAPI.js'
// 引入根据ID修改账户信息接口
import {changeUserByIDAPI} from '../../../http/api/userAPI.js'
// 引入根据ID删除账户信息接口
import {deleteUserByIDAPI} from '../../../http/api/userAPI.js'
// 引入根据ID删除账户信息接口
import {createNewUserAPI} from '../../../http/api/userAPI.js'
// 引入区域select下拉选择器数据获取接口
import {selectAreaForSelectAPI} from '../../../http/api/publicAPI.js'
// 引入楼组select下拉选择器数据获取接口
import {selectBuildForSelectAPI} from '../../../http/api/publicAPI.js'
import { fi } from 'element-plus/es/locale/index.js'


export default {
    setup () {
        const router = useRouter(); //初始化路由（自定义局部刷新）
        // nextTick(() => { // 刷新页面（局部）
        //                     router.replace({
        //                         path: '/redirect' + '/AreaManagement',
        //                     })
        //                 });

        const indexMethod = (index: number) => { //数据序号
            return index + 1;
        }

        // 数据查询展示
        const resetFormRef = ref<FormInstance>();
        const dataValue = reactive({//所有区域数据
            allData:[],
            areaSelectData:[], //区域select下拉框数据
            buildSelectData:[], //建筑select下拉框数据
        });
        const selectDataValue = reactive({//输入框数据
            id: '',
            manageCode: '',
            manageName: '',
            phone: '',
            manageLevel: null,
            isEnable: null,
            pageCurrent: 1
        });

        //区域select下拉框 selectAllPersonnelDataAPI(selectDataValue).then(res =>
        const areaSelect = selectAreaForSelectAPI().then(res =>{
            dataValue.areaSelectData = res.data;
        });
        //建筑select下拉框（联动未实现-待优化）
        const buildSelect = selectBuildForSelectAPI(selectDataValue).then(res =>{
            dataValue.buildSelectData = res.data;
        })
        
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
                        console.log(typeof(selectDataValue.id))

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
        }

        // 操作按键及Dialog页面
        //详情页面弹框（Dialog）
        const dialogFormVisible = ref(false);
        const formLabelWidth = '140px';
        const detailForm = reactive({
            id: '',
            manageCode: '',
            manageName: '',
            phone: '',
            manageLevel: '',
            isEnable: '',
        });
        const Details = (row: any) =>{//详情页数据展示
            dialogFormVisible.value = true;
            detailForm.id = row.id;
            detailForm.manageCode = row.manageCode;
            detailForm.manageName = row.manageName;
            detailForm.phone = row.phone;
            detailForm.manageLevel = row.manageLevel;
            detailForm.isEnable = row.isEnable;
        };

        //修改页面弹框（Dialog）
        const centerDialogVisible = ref(false);
        const detailedForm = reactive({
            id: '',
            manageCode: '',
            manageName: '',
            phone: '',
            manageLevel: '',
            isEnable: '',
            areaName: '',
            areaCode: '',
            buildCode: '',
            buildName: '',
        });
        
        const selectDisabled = ref(false); //用于禁用el-select
        const DialogVisible = (row: any) =>{//修改页数据展示
            console.log("行数据："+JSON.stringify(row));
            centerDialogVisible.value = true;
            detailedForm.id = row.id;
            detailedForm.manageCode = row.manageCode;
            detailedForm.manageName = row.manageName;
            detailedForm.phone = row.phone;
            detailedForm.manageLevel = row.manageLevel;
            detailedForm.isEnable = row.isEnable;
            detailedForm.areaCode = row.areaCode;
            //detailedForm.buildCode = row.buildCode;
            detailedForm.buildCode = addUserDataValue.buildCode;
            
            if(detailedForm.manageName == sessionStorage.getItem('manageName')){
                selectDisabled.value = true;
            }else{
                selectDisabled.value = false;
            }
        };
        const updateUserInf = (detailedForm: any) =>{// 修改页面的保存按键
            if(detailedForm.manageName == '' || detailedForm.manageName == null){
                ElMessage({
                    message: '账户名称不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.phone == '' || detailedForm.phone == null){
                ElMessage({
                    message: '联系电话不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.manageLevel == '' || detailedForm.manageLevel == null){
                ElMessage({
                    message: '账户权限不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.isEnable == '' || detailedForm.isEnable == null){
                ElMessage({
                    message: '启用状态不能为空！',
                    type: 'warning',
                })
            }else{
                changeUserByIDAPI(detailedForm).then(res =>{
                    console.log("修改数据："+JSON.stringify(detailedForm));
                    var state =  res.status; //接口状态
                    var result = res.data; //接口返回值
                    if(state == 200){
                        if(result == 1){
                            nextTick(() => { // 刷新页面（局部）
                                router.replace({
                                    path: '/redirect' + '/AccountManagement',
                                })
                            });
                            ElNotification({
                                title: '操作成功！',
                                message: '已成功更新目标数据',
                                type: 'success',
                                position: 'bottom-right',
                                duration: 4000,
                            });
                            centerDialogVisible.value = false;
                        }else{
                            ElNotification({
                                title: '操作失败',
                                message: '请和系统管理员联系',
                                type: 'error',
                                position: 'bottom-right',
                                duration: 4000,
                            })
                            centerDialogVisible.value = true;
                        }
                    }
                }) 
            }
            
        };

        //删除按键
        const cancelEvent = () => {// 删除弹框单机事件
            console.log('cancel!')
        };
        const confirmEvent = (row: any, index: any) => {// 删除弹框单机事件
            deleteUserByIDAPI(row.id).then(res =>{ //执行删除接口
                var state =  res.status; //接口状态
                var result = res.data; //接口返回值
                if(result == 2){
                    ElNotification({
                        title: '操作失败！',
                        message: '不能删除当前登陆账户',
                        type: 'warning',
                        position: 'bottom-right',
                        duration: 4000,
                    });
                }
                if(result == 1){
                    if(state == 200){
                        ElNotification({
                            title: '操作成功！',
                            message: '以将目标数据移除',
                            type: 'success',
                            position: 'bottom-right',
                            duration: 4000,
                        });

                        nextTick(() => { // 刷新页面（局部）
                            router.replace({
                                path: '/redirect' + '/AccountManagement',
                            })
                        }); 
                    }else{
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
        };

        // 新增区域
        const addDialogVisible = ref(false);
        const areaControl = ref(true); //用于禁用区域el-select下拉框
        const buildControl = ref(true); //用于禁用楼组el-select下拉框
        const addUserDataValue = reactive({//输入框数据
            manageName: '',
            phone: '',
            manageLevel: '',
            isEnable: '',
            areaName: '',
            areaCode: '',
            buildCode: '',
            buildName: '',
        });
        const openAddUserData = () =>{//打开新增dialog页面
            addDialogVisible.value = true;
        }
        const levelChange = () =>{//详情页数据展示
            if(addUserDataValue.manageLevel == "1" || addUserDataValue.manageLevel == "2"){
                areaControl.value = false;
            }else{
                areaControl.value = true;
            }

            if(addUserDataValue.manageLevel == "2"){
                buildControl.value = false;
            }else{
                //areaControl.value = true;
                buildControl.value = true;
            }
        };
        const addNewUser = (addUserDataValue: any) =>{//新增页面保存按键
            if(addUserDataValue.manageName == '' || addUserDataValue.manageName == null){
                ElMessage({
                    message: '账户名称不能为空！',
                    type: 'warning',
                })
            }else if(addUserDataValue.phone == '' || addUserDataValue.phone == null){
                ElMessage({
                    message: '联系电话不能为空！',
                    type: 'warning',
                })
            }else if(addUserDataValue.manageLevel == '' || addUserDataValue.manageLevel == null){
                ElMessage({
                    message: '账户权限不能为空！',
                    type: 'warning',
                })
            }else if(((addUserDataValue.manageLevel == "1" || addUserDataValue.manageLevel == "2") && addUserDataValue.areaCode == '') || ((addUserDataValue.manageLevel == "1" || addUserDataValue.manageLevel == "2") && addUserDataValue.areaCode == null)){
                ElMessage({
                    message: '所属区域不能为空！',
                    type: 'warning',
                })
            }else if((addUserDataValue.manageLevel == "2" && addUserDataValue.buildCode == '') || (addUserDataValue.manageLevel == "2" && addUserDataValue.buildCode == null)){
                ElMessage({
                    message: '所属楼组不能为空！',
                    type: 'warning',
                })
            }else if(addUserDataValue.isEnable == '' || addUserDataValue.isEnable == null){
                ElMessage({
                    message: '启用状态不能为空！',
                    type: 'warning',
                })
            }else{
                addUserDataValue.buildCode = addUserDataValue.buildCode.toString()
                //console.log("数据："+JSON.stringify(addAreaDataValue));
                createNewUserAPI(addUserDataValue).then(res =>{//调用保存接口
                    var state =  res.status; //接口状态
                    var result = res.data; //接口返回值
                    
                    console.log("数据："+JSON.stringify(addUserDataValue));

                    if(state === 200){
                        if(result == 0){
                            ElNotification({
                                title: '操作失败',
                                message: '用户名称已存在',
                                type: 'warning',
                                position: 'bottom-right',
                                duration: 4000,
                            });
                        }
                        if(result == 1){
                            addDialogVisible.value = false;
                            nextTick(() => { // 刷新页面（局部）
                                router.replace({
                                    path: '/redirect' + '/AccountManagement',
                                })
                            });
                            ElNotification({
                                title: '操作成功！',
                                message: '已成功新增数据',
                                type: 'success',
                                position: 'bottom-right',
                                duration: 4000,
                            });
                        }
                        if(result != 1 && result != 0){
                            ElNotification({
                            title: '操作失败',
                            message: '保存失败！请和系统管理员联系',
                            type: 'error',
                            position: 'bottom-right',
                            duration: 4000,
                        });
                        }
                        
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
            }
        }

        return {
            router,
            resetFormRef,
            selectDataValue,
            dataValue,
            areaSelect,
            sellectAllUserData,
            dialogFormVisible,
            formLabelWidth,
            detailForm,
            centerDialogVisible,
            detailedForm,
            addDialogVisible,
            selectDisabled,
            areaControl,
            buildControl,
            addUserDataValue,
            levelChange,
            indexMethod,
            CurrentChange,
            resetForm,
            queryUserData,
            Details,
            cancelEvent,
            DialogVisible,
            updateUserInf,
            confirmEvent,
            addNewUser,
            openAddUserData,
        }
    },

    components:{ //注册组件
        //element图标组件
        Delete,
        Postcard,
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
.addDivStyle{
    padding-bottom: 3px;
    padding-top: 3px;
    padding-left: 11px;
    color: #909399;
    font-size: 13px;
    font-weight: 800;
    display: flex;
    justify-content: flex-start;
    align-items: center;
}
.flexDiv{
    display: flex;
    flex-direction: row;
    cursor:pointer;
}
.addIconStyle{ 
    padding-top: 4px;
    padding-left: 15px;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    color: #409EFF;
}
.addTextStyle{
    padding-top: 4px;
    padding-left: 2px;
    color: #409EFF;
    font-size: 11px;
    font-weight: 800;
    display: flex;
    justify-content: flex-start;
    align-items: center;
}
.dialog-footer{ //设置Dialog对话框按钮居中
    display: flex;
    justify-content: center; 
}
</style>

<style lang="scss">//删除按钮样式 
.el-popconfirm__main {
  margin: 8px 20px 12px 20px;
  font-size: 14px;
  font-family: MicrosoftYaHei;
  color: #333333;
}
.el-popconfirm__action {
  margin-bottom: 12px;
  padding-bottom: 12px;
  // 交换 确定 和 取消 按钮的位置
  .el-button:nth-child(1) {
    float: right;
    margin-right: 23%;
    width: 42px;
    height: 22px;
    background: #055fe7;
    border-radius: 4px;
    font-size: 10px;
    font-family: MicrosoftYaHei;
    color: #ffffff;
  }
  .el-button:nth-child(2) {
    float: left;
    margin-left: 23%;
    width: 42px;
    height: 22px;
    background: #ffffff;
    border-radius: 4px;
    border: 1px solid #055fe7;
    font-size: 10px;
    font-family: MicrosoftYaHei;
    color: #2e3846;
  }
}
</style>

<style lang="scss" scoped>
/* el组件样式修改 */
.el-select {
  width: 300px;
}
.el-input {
  width: 300px;
}
</style>