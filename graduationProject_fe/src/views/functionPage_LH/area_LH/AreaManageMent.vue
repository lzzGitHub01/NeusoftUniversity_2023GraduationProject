<template>
    <div class="pageStyle">
        <!-- 查询栏 -->
        <el-form ref="resetFormRef" class="myFormStyle"  :model="selectDataValue">
            <el-row style="margin-bottom: 0px">
                <el-col :span="8">
                    <el-form-item :label="$t('area.area_id')" prop="id" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.id" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="区域编号" prop="areaCode" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.areaCode" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="区域名称" prop="areaName" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.areaName" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row style="margin-bottom: 0px">
                <el-col :span="8">
                    <el-form-item label="风险等级" prop="areaRisk" label-width="80px" style="margin-bottom: 10px">
                        <el-select v-model="selectDataValue.areaRisk" placeholder="请选择" clearable>
                            <el-option label="常态化" value="120021" />
                            <el-option label="低风险" value="120022" />
                            <el-option label="中风险" value="120023" />
                            <el-option label="高风险" value="120024" />
                        </el-select> 
                    </el-form-item>
                </el-col>
                <el-col :span="8"></el-col>
                <el-col :span="8"></el-col>
            </el-row>
            <!-- <el-button type="primary" @click="queryAreaData(selectDataValue)">查询</el-button> -->
            <el-row>
                <el-col :span="24" style="text-align: center">
                    <el-button type="primary" @click="queryAreaData(resetFormRef)">查询</el-button>
                    <el-button type="primary" @click="resetForm(resetFormRef)">重置</el-button>
                </el-col>
            </el-row>
        </el-form>

        <!-- 新增数据 -->
        <el-row class="addStyle" style="margin-bottom: 0px">
            <el-col :span="1.5"><div class="addDivStyle">信息列表</div></el-col>
            <el-col :span="5">
                <div class="flexDiv" @click="openAddAreaData()">
                    <div class="addIconStyle"><el-icon><FolderAdd /></el-icon></div>
                    <div class="addTextStyle">新增</div>
                </div> 
            </el-col>
            <el-col :span="1.5"></el-col>
        </el-row>

        <!-- 数据展示栏 -->
        <el-table :data="dataValue.allAreaData.records" border style="width: 100%" :cell-style="{'text-align':'center'}" :default-sort="{ prop: 'id', order: 'ascending' }">
            <el-table-column type="index" label="序号" :index="indexMethod" width="54"/>
            <el-table-column prop="id" label="区域ID" width="245" />
            <el-table-column prop="areaCode" label="区域编号" width="245" />
            <el-table-column prop="areaName" label="区域名称" width="245" />
            <el-table-column prop="codeName" label="区域风险等级" width="245" />
            <el-table-column fixed="right" prop="" label="操作">
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
                    <el-pagination :small="true" background layout="total, prev, pager, next" :page-size="Number(dataValue.allAreaData.size)" :total="Number(dataValue.allAreaData.total)"
                        :default-page-size="1" :current-page="selectDataValue.pageCurrent" :pager-count="5" @current-change="CurrentChange"/>
                </div>
            </el-col>
        </el-row>
    </div>

    <!-- 详情按键的Dialog页面 -->
    <el-dialog v-model="dialogFormVisible" title="区域信息详情" width="35%" :append-to-body=true :draggable=true :modal-append-to-body=false align-center>
        <el-form :model="form">
            <el-row>
                <el-col :span="24">
                    <el-form-item label="区域ID" :label-width="formLabelWidth">
                        <el-input v-model="form.areaID" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>             
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="区域编号" :label-width="formLabelWidth">
                        <el-input v-model="form.areaCode" autocomplete="off" disabled />
                    </el-form-item>                    
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="区域名称" :label-width="formLabelWidth">
                        <el-input v-model="form.areaName" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="区域风险等级" :label-width="formLabelWidth">
                        <el-input v-model="form.areaRisk" autocomplete="off" disabled />
                    </el-form-item>                 
                </el-col>  
            </el-row>
            <!-- <el-form-item label="Zones" :label-width="formLabelWidth">
                <el-select v-model="form.region" placeholder="Please select a zone">
                    <el-option label="Zone No.1" value="shanghai" />
                    <el-option label="Zone No.2" value="beijing" />
                </el-select>
            </el-form-item> -->
        </el-form>
        <template #footer>
            <span class="dialog-footer"> 
                <el-button type="primary" @click="dialogFormVisible = false">确认</el-button>
            </span>
        </template>
    </el-dialog>

    <!-- 修改按键的Dialog页面 -->
    <el-dialog v-model="centerDialogVisible" title="区域信息修改" width="35%" :append-to-body=true :draggable=true :modal-append-to-body=false :z-index="1000" align-center>
        <el-form :model="detailedForm">
            <el-row>
                <el-col :span="24">
                    <el-form-item label="区域ID" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.id" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>             
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="区域编号" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.areaCode" autocomplete="off" disabled />
                    </el-form-item>                    
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="区域名称" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.areaName" autocomplete="off" />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="区域风险等级" :label-width="formLabelWidth">
                        <el-select v-model="detailedForm.areaRisk" placeholder="请选择" clearable>
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
                <el-button type="primary" @click="updateAreaInf(detailedForm)">保存</el-button>
                <el-button @click="centerDialogVisible = false">取消</el-button>
            </span>
        </template>
    </el-dialog>

    <!-- 新增按键的Dialog页面 -->
    <el-dialog v-model="addDialogVisible" title="新增区域信息" width="35%" :append-to-body=true :draggable=true :modal-append-to-body=false :z-index="1000" align-center>
        <el-form :model="addAreaDataValue">
            <el-row>
                <el-col :span="24">
                    <el-form-item label="区域名称" :label-width="formLabelWidth">
                        <el-input v-model="addAreaDataValue.areaName" placeholder="请输入" />
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-form-item label="区域风险等级" :label-width="formLabelWidth">
                        <!-- <el-input v-model="form.areaRisk" autocomplete="off" /> -->
                        <el-select v-model="addAreaDataValue.areaRisk" placeholder="请选择" clearable>
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
                <el-button type="primary" @click="addNewArea(addAreaDataValue)">保存</el-button>
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

// 自定义局部刷新
import { useRouter } from "vue-router";
// 引入查询所有区域接口
import {selectAllAreaDataAPI} from'../../../http/api/areaAPI.js'
// 引入删除区域接口
import {deleteAreaByIDAPI} from'../../../http/api/areaAPI.js'
// 引入修改区域信息接口
import {changeAreaByIDAPI} from'../../../http/api/areaAPI.js'
// 引入新增区域接口
import {createNewAreaAPI} from'../../../http/api/areaAPI.js'


export default {
    setup () {

        const router = useRouter(); //初始化路由（自定义局部刷新）

        const indexMethod = (index: number) => { //数据序号
            return index + 1;
        }

        const dataValue = reactive({//所有区域数据
            //areaData:[],
            allAreaData:[]
        });

        const selectDataValue = reactive({//查询输入框数据
            id: '',
            areaCode: '',
            areaName: '',
            areaRisk: null,
            pageCurrent: 1
        });
        
        const CurrentChange = (currentPage: any) =>{ //分页改变当前页数
            selectDataValue.pageCurrent = currentPage;
            console.log("页数："+selectDataValue.pageCurrent)
            selectAllAreaDataAPI(selectDataValue).then(res =>{
                dataValue.allAreaData = res.data;
            })
        }

        const sellectAllAreaData = selectAllAreaDataAPI(selectDataValue).then(res =>{
            //console.log("数据："+JSON.stringify(res))
            dataValue.allAreaData = res.data;
            console.log("数据："+JSON.stringify(dataValue.allAreaData));
        });

        const confirmEvent = (row: any, index: any) => {// 删除弹框单机事件
            
            deleteAreaByIDAPI(row.id).then(res =>{ //执行删除接口
                if(res.status == 200){
                    ElNotification({
                        title: '操作成功！',
                        message: '以将目标数据移除',
                        type: 'success',
                        position: 'bottom-right',
                        duration: 4000,
                    });

                    // console.log("索引："+index);
                    // console.log("数据："+dataValue.allAreaData[index]);
                    
                    //dataValue.allAreaData.splice(index,1); //删除页面上的数据

                    nextTick(() => { // 刷新页面（局部）
                        router.replace({
                            path: '/redirect' + '/AreaManagement',
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

        // 页面查询功能
        const resetFormRef = ref<FormInstance>();
        
        const queryAreaData = (formEl: FormInstance | undefined) => { //查询按钮
            if (!formEl) return
            formEl.validate((valid) => {
                if (valid) {
                    selectAllAreaDataAPI(selectDataValue).then(res =>{ //动态查询数据并渲染到表格中
                        var state =  res.status; //接口状态
                        var result = res.data; //接口返回值
                        console.log("查询数据："+JSON.stringify(selectDataValue));

                        console.log(res.request);
                        console.log(typeof(selectDataValue.id))

                        if(state === 200){//实现渲染
                            dataValue.allAreaData = res.data;
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

        // 新增区域
        const addDialogVisible = ref(false);
        const addAreaDataValue = reactive({//输入框数据
            areaName: '',
            areaRisk: '',
        });
        const openAddAreaData = () =>{//打开新增dialog页面
            addDialogVisible.value = true;
        }
        const addNewArea = (addAreaDataValue: any) =>{//新增页面保存按键
            console.log("数据："+JSON.stringify(addAreaDataValue));

            if(addAreaDataValue.areaName == '' || addAreaDataValue.areaName == null){
                ElMessage({
                    message: '区域名称不能为空！',
                    type: 'warning',
                })
            }else if(addAreaDataValue.areaRisk == '' || addAreaDataValue.areaRisk == null){
                ElMessage({
                    message: '风险等级不能为空！',
                    type: 'warning',
                })
            }else{
                createNewAreaAPI(addAreaDataValue).then(res =>{//调用保存接口
                    var state =  res.status; //接口状态
                    var result = res.data; //接口返回值
                    console.log("数据："+JSON.stringify(addAreaDataValue));

                    if(state === 200){
                        if(result == 1){
                            addDialogVisible.value = false;
                            nextTick(() => { // 刷新页面（局部）
                                router.replace({
                                    path: '/redirect' + '/AreaManagement',
                                })
                            });
                            ElNotification({
                                title: '操作成功！',
                                message: '已成功新增数据',
                                type: 'success',
                                position: 'bottom-right',
                                duration: 4000,
                            });
                        }else{
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

        // 操作按键
        const cancelEvent = () => {// 删除弹框单机事件
            console.log('cancel!')
        };

        //详情页面弹框
        const dialogFormVisible = ref(false);
        const formLabelWidth = '140px';
        const form = reactive({
            areaID: '',
            areaName: '',
            areaCode: '',
            areaRisk: '',
        });

        const Details = (row: any) =>{//详情页数据展示
            dialogFormVisible.value = true;
            form.areaID = row.id;
            form.areaName = row.areaName;
            form.areaCode = row.areaCode;
            form.areaRisk = row.codeName;
        };

        //修改页面弹框
        const centerDialogVisible = ref(false);
        const detailedForm = reactive({
            id: '',
            areaName: '',
            areaCode: '',
            areaRisk: '',
        });
        const DialogVisible = (row: any) =>{//修改页数据展示
            console.log(JSON.stringify(row));
            centerDialogVisible.value = true;
            detailedForm.id = row.id;
            detailedForm.areaName = row.areaName;
            detailedForm.areaCode = row.areaCode;
            detailedForm.areaRisk = row.areaRisk;
        };
        const updateAreaInf = (detailedForm: any) =>{// 修改页面的保存按键
            if(detailedForm.areaName == '' || detailedForm.areaName == null){
                ElMessage({
                    message: '区域名称不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.areaRisk == '' || detailedForm.areaRisk == null){
                ElMessage({
                    message: '风险等级不能为空！',
                    type: 'warning',
                })
            }else{
                changeAreaByIDAPI(detailedForm).then(res =>{
                    console.log("修改数据："+JSON.stringify(detailedForm));
                    var state =  res.status; //接口状态
                    var result = res.data; //接口返回值

                    if(state == 200){
                        if(result == 1){
                            nextTick(() => { // 刷新页面（局部）
                                router.replace({
                                    path: '/redirect' + '/AreaManagement',
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

        return {
            resetFormRef,
            sellectAllAreaData,
            dataValue,
            router,
            dialogFormVisible,
            formLabelWidth,
            form,
            centerDialogVisible,
            addDialogVisible,
            detailedForm,
            selectDataValue,
            addAreaDataValue,
            indexMethod,
            CurrentChange,
            resetForm,
            confirmEvent,
            cancelEvent,
            Details,
            DialogVisible,
            updateAreaInf,
            queryAreaData,
            openAddAreaData,
            addNewArea,
            
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
.flexDiv{
    display: flex;
    flex-direction: row;
    cursor:pointer;
}
.paginStyle{
    background-color: #ffffff;
    border: 1px solid #ebeef5;
    padding: 4px 10px 4px 10px;
    margin-top: 1px;
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