<template>
    <div class="pageStyle">
        <!-- 查询栏 -->
        <el-form ref="resetFormRef" class="myFormStyle"  :model="selectDataValue">
            <el-row style="margin-bottom: 0px">
                <el-col :span="8">
                    <el-form-item label="建筑ID" prop="id" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.id" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="建筑编号" prop="buildCode" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.buildCode" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="建筑名称" prop="buildName" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.buildName" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row style="margin-bottom: 0px">
                <el-col :span="8">
                    <el-form-item label="所属区域" prop="areaName" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.areaName" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="风险等级" prop="buildRisk" label-width="80px" style="margin-bottom: 10px">
                        <el-select v-model="selectDataValue.buildRisk" placeholder="请选择" clearable>
                            <el-option label="常态化" value="120021" />
                            <el-option label="低风险" value="120022" />
                            <el-option label="中风险" value="120023" />
                            <el-option label="高风险" value="120024" />
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
                <div class="flexDiv" @click="openAddBuildData()">
                    <div class="addIconStyle"><el-icon><FolderAdd /></el-icon></div>
                    <div class="addTextStyle">新增</div>
                </div> 
            </el-col>
            <el-col :span="1.5"></el-col>
        </el-row>

        <!-- 数据展示栏 -->
        <el-table :data="dataValue.allData.records" border style="width: 100%" :cell-style="{'text-align':'center'}">
            <el-table-column type="index" fixed="left" label="序号" :index="indexMethod" width="54"/>
            <el-table-column prop="id" label="建筑ID" width="224" />
            <el-table-column prop="buildCode" label="建筑编号" width="204" />
            <el-table-column prop="buildName" label="建筑名称" width="204" />
            <el-table-column prop="buildFloorName" label="建筑规格" width="204" />
            <el-table-column prop="codeName" label="风险等级" width="204" />
            <el-table-column prop="areaName" label="所属区域" width="144" />
            <!-- <el-table-column prop="isEnable" label="启用状态" width="120" /> -->
            <el-table-column fixed="right" prop="" label="操作" width="242">
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
    <el-dialog v-model="dialogFormVisible" title="居民楼信息详情" width="35%" :append-to-body=true :draggable=true :modal-append-to-body=false align-center>
        <el-form :model="detailForm">
            <el-row>
                <el-col :span="24">
                    <el-form-item label="建筑ID" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.id" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>             
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="建筑编号" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.buildCode" autocomplete="off" disabled />
                    </el-form-item>                    
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="建筑规格" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.buildFloorName" autocomplete="off" disabled />
                    </el-form-item>                    
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="建筑名称" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.buildName" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="风险等级" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.codeName" autocomplete="off" disabled />
                    </el-form-item>                 
                </el-col>  
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="所属区域" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.areaName" autocomplete="off" disabled />
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
    <el-dialog v-model="centerDialogVisible" title="居民楼信息修改" width="35%" :append-to-body=true :draggable=true :modal-append-to-body=false :z-index="1000" align-center>
        <el-form :model="detailedForm">
            <el-row>
                <el-col :span="24">
                    <el-form-item label="建筑ID" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.id" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>             
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="建筑编号" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.buildCode" autocomplete="off" disabled />
                    </el-form-item>                    
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="建筑名称" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.buildName" autocomplete="off" />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="建筑规格" :label-width="formLabelWidth">
                        <el-select v-model="detailedForm.buildFloorCode" placeholder="请选择" clearable>
                            <el-option v-for="item in dataValue.buildFloorSelectData" :label="item.buildFloorName" :value="item.buildFloorCode"/>
                        </el-select>
                    </el-form-item>   
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="所属区域" :label-width="formLabelWidth">
                        <!-- <el-input v-model="detailedForm.areaName" autocomplete="off" /> -->
                        <el-select v-model="detailedForm.areaCode" placeholder="请选择" clearable>
                            <el-option v-for="item in dataValue.areaSelectData" :label="item.areaName" :value="item.areaCode"/>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="风险等级" :label-width="formLabelWidth">
                        <el-select v-model="detailedForm.buildRisk" placeholder="请选择" :disabled="selectDisabled" clearable>
                            <el-option label="常态化" value="120021" />
                            <el-option label="低风险" value="120022" />
                            <el-option label="中风险" value="120023" />
                            <el-option label="高风险" value="120024" />
                        </el-select> 
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="updateBuildInf(detailedForm)">保存</el-button>
                <el-button @click="centerDialogVisible = false">取消</el-button>
            </span>
        </template>
    </el-dialog>

    <!-- 新增按键的Dialog页面 -->
    <el-dialog v-model="addDialogVisible" title="新增居民楼" width="35%" :append-to-body=true :draggable=true :modal-append-to-body=false :z-index="1000" align-center>
        <el-form :model="addBuildDataValue">
            <el-row>
                <el-col :span="24">
                    <el-form-item label="建筑名称" :label-width="formLabelWidth">
                        <el-input v-model="addBuildDataValue.buildName" placeholder="请输入" />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="建筑规格" :label-width="formLabelWidth">
                        <el-select v-model="addBuildDataValue.buildFloorCode" placeholder="请选择" clearable>
                            <el-option v-for="item in dataValue.buildFloorSelectData" :label="item.buildFloorName" :value="item.buildFloorCode"/>
                        </el-select>
                    </el-form-item>   
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="所属区域" :label-width="formLabelWidth">
                        <el-select v-model="addBuildDataValue.areaCode" placeholder="请选择" clearable>
                            <el-option v-for="item in dataValue.areaSelectData" :label="item.areaName" :value="item.areaCode"/>
                        </el-select>
                    </el-form-item>   
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="风险等级" :label-width="formLabelWidth">
                        <el-select v-model="addBuildDataValue.buildRisk" placeholder="请选择" :disabled="selectDisabled" clearable>
                            <el-option label="常态化" value="120021" />
                            <el-option label="低风险" value="120022" />
                            <el-option label="中风险" value="120023" />
                            <el-option label="高风险" value="120024" />
                        </el-select> 
                    </el-form-item>                
                </el-col>  
            </el-row>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="addNewBuild(addBuildDataValue)">保存</el-button>
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
import {selectAllBuildDataAPI} from '../../../http/api/buildAPI.js'
// 引入修改建筑信息接口
import {updateBuildInfByIdAPI} from '../../../http/api/buildAPI.js'
// 引入删除建筑信息接口
import {deleteBuildByIdAPI} from '../../../http/api/buildAPI.js'
// 引入新增建筑信息接口
import {addNewBuildAPI} from '../../../http/api/buildAPI.js'
// 引入区域select下拉选择器数据获取接口
import {selectAreaForSelectAPI} from '../../../http/api/publicAPI.js'
// 引入建筑规格select下拉选择器数据获取接口
import {selectBuildForFloorSelectAPI} from '../../../http/api/publicAPI.js'

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
            buildFloorSelectData:[], //建筑规格select下拉框数据
        });
        const selectDataValue = reactive({//输入框数据
            id: '',
            buildCode: '',
            buildName: '',
            areaName: '',
            buildRisk: '',
            pageCurrent: 1
        });

        //数据查询
        const queryUserData = (formEl: FormInstance | undefined) => { //查询按钮
            if (!formEl) return
            formEl.validate((valid) => {
                if (valid) {
                    selectAllBuildDataAPI(selectDataValue).then(res =>{ //动态查询数据并渲染到表格中
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
        const sellectAllBuildData = selectAllBuildDataAPI(selectDataValue).then(res =>{ //数据显示
            //console.log("数据："+JSON.stringify(res))
            dataValue.allData = res.data;
            console.log("数据："+JSON.stringify(dataValue.allData));
        });
        const CurrentChange = (currentPage: any) =>{ //分页改变当前页数
            selectDataValue.pageCurrent = currentPage;
            console.log("页数："+selectDataValue.pageCurrent)
            selectAllBuildDataAPI(selectDataValue).then(res =>{
                dataValue.allData = res.data;
            })
        }

        // 操作按键及Dialog页面
        //详情页面弹框（Dialog）
        const dialogFormVisible = ref(false);
        const formLabelWidth = '140px';
        const detailForm = reactive({
            id: '',
            buildCode: '',
            buildName: '',
            buildFloorName: '',
            areaName: '',
            codeName: '',
        });
        const Details = (row: any) =>{//详情页数据展示
            dialogFormVisible.value = true;
            detailForm.id = row.id;
            detailForm.buildCode = row.buildCode;
            detailForm.buildName = row.buildName;
            detailForm.buildFloorName = row.buildFloorName;
            detailForm.areaName = row.areaName;
            detailForm.codeName = row.codeName;
        };

        //修改页面弹框（Dialog）
        const centerDialogVisible = ref(false);
        const detailedForm = reactive({
            id: '',
            buildCode: '',
            buildName: '',
            buildFloorCode: '',
            buildFloorName: '',
            areaName: '',
            areaCode: '',
            codeName: '',
            buildRisk: '',
        });
        const selectDisabled = ref(false); //用于禁用el-select
        const DialogVisible = (row: any) =>{//修改页数据展示
            console.log(JSON.stringify(row));
            centerDialogVisible.value = true;
            detailedForm.id = row.id;
            detailedForm.buildCode = row.buildCode;
            detailedForm.buildName = row.buildName;
            detailedForm.buildFloorCode = row.buildFloorCode;
            detailedForm.buildFloorName = row.buildFloorName;
            detailedForm.areaName = row.areaName;
            detailedForm.areaCode = row.areaCode;
            detailedForm.codeName = row.codeName;
            detailedForm.buildRisk = row.buildRisk;
        };
        const updateBuildInf = (detailedForm: any) =>{// 修改页面的保存按键
            if(detailedForm.buildName == '' || detailedForm.buildName == null){
                ElMessage({
                    message: '建筑名称不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.buildFloorCode == '' || detailedForm.buildFloorCode == null){
                ElMessage({
                    message: '建筑规格不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.areaCode == '' || detailedForm.areaCode == null){
                ElMessage({
                    message: '所属区域不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.buildRisk == '' || detailedForm.buildRisk == null){
                ElMessage({
                    message: '风险等级不能为空！',
                    type: 'warning',
                })
            }else{
                updateBuildInfByIdAPI(detailedForm).then(res =>{
                    console.log("修改数据："+JSON.stringify(detailedForm));
                    var state =  res.status; //接口状态
                    var result = res.data; //接口返回值
                    if(state == 200){
                        if(result == 1){
                            nextTick(() => { // 刷新页面（局部）
                                router.replace({
                                    path: '/redirect' + '/BuildManageMent',
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
            deleteBuildByIdAPI(row.id).then(res =>{ //执行删除接口
                if(res.status == 200){
                    ElNotification({
                        title: '操作成功！',
                        message: '以将目标数据移除',
                        type: 'success',
                        position: 'bottom-right',
                        duration: 4000,
                    });

                    nextTick(() => { // 刷新页面（局部）
                        router.replace({
                            path: '/redirect' + '/BuildManageMent',
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
            })  
        };

        // 新增区域
        const addDialogVisible = ref(false);
        const addBuildDataValue = reactive({//输入框数据
            buildFloorCode: '',
            buildFloorName: '',
            buildName: '',
            buildRisk: '',
            areaName: '',
            areaCode: '',
        });
        const openAddBuildData = () =>{//打开新增dialog页面
            addDialogVisible.value = true;
        }
        const addNewBuild = (addBuildDataValue: any) =>{//新增页面保存按键
            if(addBuildDataValue.buildName == '' || addBuildDataValue.buildName == null){
                ElMessage({
                    message: '建筑名称不能为空！',
                    type: 'warning',
                })
            }else if(addBuildDataValue.buildFloorCode == '' || addBuildDataValue.buildFloorCode == null){
                ElMessage({
                    message: '建筑规格不能为空！',
                    type: 'warning',
                })
            }else if(addBuildDataValue.areaCode == '' || addBuildDataValue.areaCode == null){
                ElMessage({
                    message: '所属区域不能为空！',
                    type: 'warning',
                })
            }else if(addBuildDataValue.buildRisk == '' || addBuildDataValue.buildRisk == null){
                ElMessage({
                    message: '风险等级不能为空！',
                    type: 'warning',
                })
            }else{
                //console.log("数据："+JSON.stringify(addAreaDataValue));
                addNewBuildAPI(addBuildDataValue).then(res =>{//调用保存接口
                    var state =  res.status; //接口状态
                    var result = res.data; //接口返回值

                    console.log("数据："+JSON.stringify(addBuildDataValue));

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
                                    path: '/redirect' + '/BuildManageMent',
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

        //区域select下拉框 selectAllPersonnelDataAPI(selectDataValue).then(res =>
        const areaSelect = selectAreaForSelectAPI().then(res =>{
            dataValue.areaSelectData = res.data;
        });

        //区域select下拉框 selectAllPersonnelDataAPI(selectDataValue).then(res =>
        const buildFloorSelect = selectBuildForFloorSelectAPI().then(res =>{
            dataValue.buildFloorSelectData = res.data;
        })

        return {
            router,
            resetFormRef,
            dataValue,
            selectDataValue,
            sellectAllBuildData,
            dialogFormVisible,
            formLabelWidth,
            detailForm,
            centerDialogVisible,
            detailedForm,
            selectDisabled,
            addDialogVisible,
            addBuildDataValue,
            areaSelect,
            buildFloorSelect,
            indexMethod,
            openAddBuildData,
            queryUserData,
            CurrentChange,
            resetForm,
            Details,
            DialogVisible,
            updateBuildInf,
            cancelEvent,
            confirmEvent,
            addNewBuild,
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