<template><!-- 楼组规格信息管理页面 -->
    <div  class="pageStyle">
        <!-- 查询栏 -->
        <el-form ref="resetFormRef" class="myFormStyle"  :model="selectDataValue">
            <el-row style="margin-bottom: 0px">
                <el-col :span="8">
                    <el-form-item label="规则ID" prop="id" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.id" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="规则编号" prop="buildFloorCode" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.buildFloorCode" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="楼层限制" prop="buildFloorNum" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.buildFloorNum" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row style="margin-bottom: 0px">
                <el-col :span="8">
                    <el-form-item label="住户限制" prop="communityOccupant" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.communityOccupant" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="8"></el-col>
                <el-col :span="8"></el-col>
            </el-row>
            <el-row>
                <el-col :span="24" style="text-align: center">
                    <el-button type="primary" @click="queryBuildFloorData(resetFormRef)">查询</el-button>
                    <el-button type="primary" @click="resetForm(resetFormRef)">重置</el-button>
                </el-col>
            </el-row>
        </el-form>

        <!-- 新增数据 -->
        <el-row class="addStyle" style="margin-bottom: 0px">
            <el-col :span="1.5"><div class="addDivStyle">信息列表</div></el-col>
            <el-col :span="5">
                <div class="flexDiv" @click="openAddBuildFloorData()">
                    <div class="addIconStyle"><el-icon><FolderAdd /></el-icon></div>
                    <div class="addTextStyle">新增</div>
                </div> 
            </el-col>
            <el-col :span="1.5"></el-col>
        </el-row>

        <!-- 数据展示栏 -->
        <el-table :data="dataValue.allData.records" border style="width: 100%" :cell-style="{'text-align':'center'}">
            <el-table-column type="index" fixed="left" label="序号" :index="indexMethod" width="54"/>
            <el-table-column prop="id" label="建筑规格ID" width="200" />
            <el-table-column prop="buildFloorCode" label="建筑规格编号" width="180" />
            <el-table-column prop="buildFloorName" label="建筑规格名称" width="180" />
            <el-table-column prop="communityCode" label="所属社区" width="180" />
            <el-table-column prop="buildFloorNum" label="楼层数" width="180" />
            <el-table-column prop="communityOccupant" label="每层最大住户容量" width="180" />
            <el-table-column prop="createdBy" label="创建人" width="144" />
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
    <el-dialog v-model="dialogFormVisible" title="社区人员信息详情" width="54%" :append-to-body=true :draggable=true :modal-append-to-body=false align-center>
        <el-form :model="detailForm">
            <el-row justify="space-between">
                <el-col :span="12">
                    <el-form-item label="规则ID" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.id" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="规则编号" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.buildFloorCode" autocomplete="off" disabled />
                    </el-form-item>                    
                </el-col>
            </el-row>
            
            <el-row justify="space-between">
                <el-col :span="12">
                    <el-form-item label="所属社区" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.communityCode" autocomplete="off" disabled />
                    </el-form-item>                    
                </el-col>
                <el-col :span="12">
                    <el-form-item label="最大楼层" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.buildFloorNum" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>                  
            </el-row>
            <el-row justify="space-between">
                <el-col :span="12">
                    <el-form-item label="每层住户" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.communityOccupant" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="创建人" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.createdBy" autocomplete="off" disabled />
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
    <el-dialog v-model="centerDialogVisible" title="居民楼信息修改" width="54%" :append-to-body=true :draggable=true :modal-append-to-body=false :z-index="1000" align-center>
        <el-form :model="detailedForm">
            <el-row justify="space-between">
                <el-col :span="12">
                    <el-form-item label="规则ID" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.id" autocomplete="off" disabled/>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="规则编号" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.buildFloorCode" autocomplete="off" disabled/>
                    </el-form-item>                    
                </el-col>
            </el-row>
            
            <el-row justify="space-between">
                <el-col :span="12">
                    <el-form-item label="所属社区" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.communityCode" autocomplete="off" disabled/>
                    </el-form-item>                    
                </el-col>
                <el-col :span="12">
                    <el-form-item label="最大楼层" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.buildFloorNum" autocomplete="off" />
                    </el-form-item>
                </el-col>                  
            </el-row>
            <el-row justify="space-between">
                <el-col :span="12">
                    <el-form-item label="每层住户" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.communityOccupant" autocomplete="off" />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="创建人" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.createdBy" autocomplete="off" disabled/>
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
        <el-form :model="addBuildFloorDataValue">
            <el-row>
                <el-col :span="24">
                    <el-form-item label="最大楼层数" label-width="140px">
                        <el-input v-model="addBuildFloorDataValue.buildFloorNum" placeholder="请输入" />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="每层最大住户数" label-width="140px">
                        <el-input v-model="addBuildFloorDataValue.communityOccupant" placeholder="请输入" />
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="addNewBuildFloor(addBuildFloorDataValue)">保存</el-button>
                <el-button @click="addDialogVisible = false">取消</el-button>
            </span>
        </template>
    </el-dialog>

</template>

<script  lang="ts">
// vue3相关组件
import { reactive, ref, nextTick} from 'vue'
import {Delete,Postcard,Edit,FolderAdd} from '@element-plus/icons-vue'
import { ElMessage,ElNotification} from 'element-plus'
import type { FormInstance } from 'element-plus'

// 自定义局部刷新组件
import { useRouter } from "vue-router";
// 引入查询所有规则接口
import {selectAllBuildFloorDataAPI} from "../../../http/api/buildFloorAPI.js"
// 引入根据ID删除指定的规则接口
import {deleteBuildFloorByIdAPI} from "../../../http/api/buildFloorAPI.js"
// 引入根据ID删除楼层规则信息接口
import {updateBuildFloorInfByIdAPI} from "../../../http/api/buildFloorAPI.js"
// 引入新增规则接口
import {addNewBuildFloorAPI} from "../../../http/api/buildFloorAPI.js"

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
            allData:[]
        });
        const selectDataValue = reactive({//输入框数据
            id: '',
            buildFloorCode: '',
            communityCode: '',
            buildFloorNum: '',
            communityOccupant: '',
            createdBy: '',
            pageCurrent: 1
        });
        //数据查询
        const queryBuildFloorData = (formEl: FormInstance | undefined) => { //查询按钮
            if (!formEl) return
            formEl.validate((valid) => {
                if (valid) {
                    selectAllBuildFloorDataAPI(selectDataValue).then(res =>{ //动态查询数据并渲染到表格中
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
        const sellectAllPersonnelData = selectAllBuildFloorDataAPI(selectDataValue).then(res =>{ //数据显示
            //console.log("数据："+JSON.stringify(res))
            dataValue.allData = res.data;
            console.log("数据："+JSON.stringify(dataValue.allData));
        });
        const CurrentChange = (currentPage: any) =>{ //分页改变当前页数
            selectDataValue.pageCurrent = currentPage;
            console.log("页数："+selectDataValue.pageCurrent)
            selectAllBuildFloorDataAPI(selectDataValue).then(res =>{
                dataValue.allData = res.data;
            })
        };

        // 操作按键及Dialog页面
        //详情页面弹框（Dialog）
        const dialogFormVisible = ref(false);
        const formLabelWidth = '70px';
        const detailForm = reactive({
            id: '',
            buildFloorCode: '',
            communityCode: '',
            buildFloorNum: '',
            communityOccupant: '',
            createdBy: '',
        });
        const Details = (row: any) =>{//详情页数据展示
            dialogFormVisible.value = true;
            detailForm.id = row.id;
            detailForm.buildFloorCode = row.buildFloorCode;
            detailForm.communityCode = row.communityCode;
            detailForm.buildFloorNum = row.buildFloorNum;
            detailForm.communityOccupant = row.communityOccupant;
            detailForm.createdBy = row.createdBy;
        };

        //修改页面弹框（Dialog）
        const centerDialogVisible = ref(false);
        const detailedForm = reactive({
            id: '',
            buildFloorCode: '',
            communityCode: '',
            buildFloorNum: '',
            communityOccupant: '',
            createdBy: '',
        });
        const selectDisabled = ref(false); //用于禁用el-select
        const DialogVisible = (row: any) =>{//修改页数据展示
            console.log(JSON.stringify(row));
            centerDialogVisible.value = true;
            detailedForm.id = row.id;
            detailedForm.buildFloorCode = row.buildFloorCode;
            detailedForm.communityCode = row.communityCode;
            detailedForm.buildFloorNum = row.buildFloorNum;
            detailedForm.communityOccupant = row.communityOccupant;
            detailedForm.createdBy = row.createdBy;
        };
        const updateBuildInf = (detailedForm: any) =>{// 修改页面的保存按键
            if(detailedForm.buildFloorNum == '' || detailedForm.buildFloorNum == null){
                ElMessage({
                    message: '最大楼层数不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.communityOccupant == '' || detailedForm.communityOccupant == null){
                ElMessage({
                    message: '每层最大住户数不能为空！',
                    type: 'warning',
                })
            }else{
                updateBuildFloorInfByIdAPI(detailedForm).then(res =>{
                    console.log("修改数据："+JSON.stringify(detailedForm));
                    var state =  res.status; //接口状态
                    var result = res.data; //接口返回值
                    if(state == 200){
                        if(result == 1){
                            nextTick(() => { // 刷新页面（局部）
                                router.replace({
                                    path: '/redirect' + '/BlockManageMent',
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
            deleteBuildFloorByIdAPI(row.id).then(res =>{ //执行删除接口
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
                            path: '/redirect' + '/BlockManageMent',
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

        // 新增楼组规则
        const addDialogVisible = ref(false);
        const addBuildFloorDataValue = reactive({//输入框数据
            communityCode: '',
            buildFloorNum: '',
            communityOccupant: '',
        });
        const openAddBuildFloorData = () =>{//打开新增dialog页面
            addDialogVisible.value = true;
        }
        const addNewBuildFloor = (addBuildFloorDataValue: any) =>{//新增页面保存按键
            if(addBuildFloorDataValue.buildFloorNum == '' || addBuildFloorDataValue.buildFloorNum == null){
                ElMessage({
                    message: '最大楼层数不能为空！',
                    type: 'warning',
                })
            }else if(addBuildFloorDataValue.communityOccupant == '' || addBuildFloorDataValue.communityOccupant == null){
                ElMessage({
                    message: '每层最大住户数不能为空！',
                    type: 'warning',
                })
            }else{
                //console.log("数据："+JSON.stringify(addAreaDataValue));
                addNewBuildFloorAPI(addBuildFloorDataValue).then(res =>{//调用保存接口
                    var state =  res.status; //接口状态
                    var result = res.data; //接口返回值

                    console.log("数据："+JSON.stringify(addBuildFloorDataValue));

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
                                    path: '/redirect' + '/BlockManageMent',
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
            dataValue,
            selectDataValue,
            sellectAllPersonnelData,
            dialogFormVisible,
            formLabelWidth,
            detailForm,
            selectDisabled,
            detailedForm,
            centerDialogVisible,
            addDialogVisible,
            addBuildFloorDataValue,
            indexMethod,
            queryBuildFloorData,
            resetForm,
            CurrentChange,
            Details,
            cancelEvent,
            confirmEvent,
            DialogVisible,
            updateBuildInf,
            openAddBuildFloorData,
            addNewBuildFloor
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
.paginStyle{
    background-color: #ffffff;
    border: 1px solid #ebeef5;
    padding: 4px 10px 4px 10px;
    margin-top: 1px;
}
.dialog-footer{ //设置Dialog对话框按钮居中
    display: flex;
    justify-content: center; 
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