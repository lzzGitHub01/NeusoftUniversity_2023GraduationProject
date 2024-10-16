<template>
    <div class="pageStyle">
        <!-- 查询栏 -->
        <el-form ref="resetFormRef" class="myFormStyle" :model="selectDataValue">
            <el-row style="margin-bottom: 0px" justify="space-around">
                <el-col :span="5">
                    <el-form-item label="人员编号" prop="personnelCode" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.personnelCode" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="5">
                    <el-form-item label="人员姓名" prop="personnelName" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.personnelName" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="5">
                    <el-form-item label="联系电话" prop="phone" label-width="80px" style="margin-bottom: 10px">
                        <el-input class="elInputStyle" v-model="selectDataValue.phone" placeholder="请输入" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="5">
                    <el-form-item label="居住类型" prop="personnelState" label-width="80px" style="margin-bottom: 10px">
                        <el-select v-model="selectDataValue.personnelState" placeholder="请选择" clearable>
                            <el-option label="租户" value="0" />
                            <el-option label="住户" value="1" />
                        </el-select> 
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row style="margin-bottom: 0px" justify="space-around">
                <el-col :span="5">
                    <el-form-item label="核酸结果" prop="natResult" label-width="80px" style="margin-bottom: 10px">
                        <el-select v-model="selectDataValue.natResult" placeholder="请选择" clearable>
                            <el-option label="阴性" value="0" />
                            <el-option label="阳性" value="1" />
                        </el-select> 
                    </el-form-item>
                </el-col>
                <el-col :span="5">
                    <el-form-item label="基础疾病" prop="isUd" label-width="80px" style="margin-bottom: 10px">
                        <el-select v-model="selectDataValue.isUd" placeholder="请选择" clearable>
                            <el-option label="是" value="0" />
                            <el-option label="否" value="1" />
                        </el-select> 
                    </el-form-item>
                </el-col>
                <el-col :span="5">
                    <el-form-item label="所属区域" prop="areaCode" label-width="80px" style="margin-bottom: 10px">
                        <el-select v-model="selectDataValue.areaCode" placeholder="请选择" clearable>
                            <el-option v-for="item in dataValue.areaSelectData" :label="item.areaName" :value="item.areaCode"/>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="5">
                    <el-form-item label="所属楼组" prop="buildCode" label-width="80px" style="margin-bottom: 10px">
                        <el-select v-model="selectDataValue.buildCode" placeholder="请选择" clearable>
                            <el-option v-for="item in dataValue.buildSelectData" :label="item.buildName" :value="item.buildCode"/>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24" style="text-align: center">
                    <el-button type="primary" @click="queryPersonalData(resetFormRef)">查询</el-button>
                    <el-button type="primary" @click="resetForm(resetFormRef)">重置</el-button>
                </el-col>
            </el-row>
        </el-form>

        <!-- 新增数据 -->
        <el-row class="addStyle" style="margin-bottom: 0px">
            <el-col :span="1.5"><div class="addDivStyle">信息列表</div></el-col>
            <el-col :span="1">
                <div class="flexDiv" @click="openAddPersonalData()">
                    <div class="addIconStyle"><el-icon><FolderAdd /></el-icon></div>
                    <div class="addTextStyle">新增</div>
                </div> 
            </el-col>
            <el-col :span="1">
                <el-popconfirm width="200" auto-insert-space confirm-button-text="是" cancel-button-text="否" title="是否确认导出数据" icon-color="#626AEF" @confirm="excelFileDownLoad()" @cancel="cancelEvent">
                    <template #reference>
                        <div class="flexDiv">
                            <div class="addIconStyle"><el-icon><Download /></el-icon></div>
                            <div class="addTextStyle">导出</div>
                        </div>
                    </template>
                </el-popconfirm> 
            </el-col>
            <el-col :span="1.5"></el-col>
        </el-row>

        

        <!-- 数据展示栏 -->
        <el-table :data="dataValue.allData.records" border style="width: 100%" :cell-style="{'text-align':'center'}" :default-sort="{ prop: 'id', order: 'ascending' }">
            <el-table-column type="index" fixed="left" label="序号" :index="indexMethod" width="54"/>
            <el-table-column prop="id" label="人员ID" width="200" />
            <el-table-column prop="personnelCode" label="人员编号" width="180" />
            <el-table-column prop="personnelName" label="人员姓名" width="180" />
            <el-table-column prop="personnelAge" label="人员年龄" width="180" />
            <el-table-column prop="phone" label="联系电话" width="180" />
            <el-table-column prop="areaName" label="所属区域" width="180" />
            <el-table-column prop="buildName" label="所属楼" width="180" />
            <el-table-column prop="houseNumber" label="门牌号" width="180" />
            <el-table-column prop="personnelState" label="居住类型" width="180" />
            <el-table-column prop="isUd" label="是否存在基础病" width="180" />
            <el-table-column prop="inoculate" label="疫苗接种情况" width="180" />
            <el-table-column prop="natResult" label="72小时核酸结果" width="180" />
            <el-table-column prop="sevenDayTrip" label="7日内行程" show-overflow-tooltip width="180" />
            <el-table-column prop="isQuarantine" label="是否处于隔离" width="180" />
            <el-table-column prop="quarantinePolicy" label="隔离政策" width="180" />
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
                <el-col :span="8">
                    <el-form-item label="人员ID" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.id" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="人员编号" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.personnelCode" autocomplete="off" disabled />
                    </el-form-item>                    
                </el-col>
                <el-col :span="8">
                    <el-form-item label="人员姓名" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.personnelName" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>             
            </el-row>
            <el-row justify="space-between">
                <el-col :span="8">
                    <el-form-item label="人员年龄" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.personnelAge" autocomplete="off" disabled />
                    </el-form-item>                    
                </el-col>
                <el-col :span="8">
                    <el-form-item label="联系电话" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.phone" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="所属区域" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.areaName" autocomplete="off" disabled />
                    </el-form-item>                    
                </el-col>                         
            </el-row>
            <el-row justify="space-between">
                <el-col :span="8">
                    <el-form-item label="所属楼组" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.buildName" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="居住类型" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.personnelState" autocomplete="off" disabled />
                    </el-form-item>                    
                </el-col>
                <el-col :span="8">
                    <el-form-item label="基础疾病" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.isUd" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>             
            </el-row>
            <el-row justify="space-between">
                <el-col :span="8">
                    <el-form-item label="疫苗接种" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.inoculate" autocomplete="off" disabled />
                    </el-form-item>                    
                </el-col>
                <el-col :span="8">
                    <el-form-item label="核酸结果" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.natResult" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="是否隔离" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.isQuarantine" autocomplete="off" disabled />
                    </el-form-item>                    
                </el-col>                        
            </el-row>
            <el-row justify="space-between">
                <el-col :span="8">
                    <el-form-item label="隔离政策" :label-width="formLabelWidth">
                        <el-input v-model="detailForm.quarantinePolicy" autocomplete="off" disabled />
                    </el-form-item> 
                </el-col>       
                <el-col :span="8"></el-col>
                <el-col :span="8"></el-col>
            </el-row>
            <el-row justify="space-between">
                <el-col :span="24">
                    <el-form-item label="七日内行程" label-width="83px">
                        <el-input v-model="detailForm.sevenDayTrip" maxlength="100" placeholder="请输入" show-word-limit style="width:686px" type="textarea" disabled/>
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
    <el-dialog v-model="centerDialogVisible" title="人员信息修改" width="54%" :append-to-body=true :draggable=true :modal-append-to-body=false :z-index="1000" align-center>
        <el-form :model="detailedForm">
            <el-row justify="space-between">
                <el-col :span="8">
                    <el-form-item label="人员ID" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.id" autocomplete="off" disabled />
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="人员编号" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.personnelCode" autocomplete="off" disabled />
                    </el-form-item>                    
                </el-col>
                <el-col :span="8">
                    <el-form-item label="人员姓名" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.personnelName" placeholder="请输入" autocomplete="off"/>
                    </el-form-item>
                </el-col>             
            </el-row>
            <el-row justify="space-between">
                <el-col :span="8">
                    <el-form-item label="人员年龄" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.personnelAge" placeholder="请输入" autocomplete="off"/>
                    </el-form-item>                    
                </el-col>
                <el-col :span="8">
                    <el-form-item label="联系电话" :label-width="formLabelWidth">
                        <el-input v-model="detailedForm.phone" placeholder="请输入" autocomplete="off"/>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="核酸结果" :label-width="formLabelWidth">
                        <el-select v-model="detailedForm.natResult" placeholder="请选择" clearable>
                            <el-option label="阴性" value="0" />
                            <el-option label="阳性" value="1" />
                        </el-select> 
                    </el-form-item>
                </el-col>                            
            </el-row>
            <el-row justify="space-between">
                <el-col :span="8">
                    <el-form-item label="所属区域" :label-width="formLabelWidth">
                        <el-select v-model="detailedForm.areaCode" placeholder="请选择" clearable>
                            <el-option v-for="item in dataValue.areaSelectData" :label="item.areaName" :value="item.areaCode"/>
                        </el-select>
                    </el-form-item>                    
                </el-col> 
                <el-col :span="8">
                    <el-form-item label="所属楼组" :label-width="formLabelWidth">
                        <el-select v-model="detailedForm.buildCode" placeholder="请选择" clearable>
                            <el-option v-for="item in dataValue.buildSelectData" :label="item.buildName" :value="item.buildCode"/>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="居住类型" :label-width="formLabelWidth">
                        <el-select v-model="detailedForm.personnelState" placeholder="请选择" clearable>
                            <el-option label="租户" value="0" />
                            <el-option label="住户" value="1" />
                        </el-select>
                    </el-form-item>                    
                </el-col>           
            </el-row>
            <el-row justify="space-between">
                <el-col :span="8">
                    <el-form-item label="基础疾病" :label-width="formLabelWidth">
                        <el-select v-model="detailedForm.isUd" placeholder="请选择" clearable>
                            <el-option label="是" value="0" />
                            <el-option label="否" value="1" />
                        </el-select> 
                    </el-form-item>
                </el-col> 
                <el-col :span="8">
                    <el-form-item label="疫苗接种" :label-width="formLabelWidth">
                        <el-select v-model="detailedForm.inoculate" placeholder="请选择" clearable>
                            <el-option label="一针" value="一针" />
                            <el-option label="两针" value="两针" />
                            <el-option label="三针" value="三针" />
                            <el-option label="四针" value="四针" />
                            <el-option label="未接种" value="未接种" />
                        </el-select> 
                    </el-form-item>                    
                </el-col>
                <el-col :span="8">
                    <el-form-item label="是否隔离" :label-width="formLabelWidth">
                        <el-select v-model="detailedForm.isQuarantine" placeholder="请选择" clearable>
                            <el-option label="是" value="0" />
                            <el-option label="否" value="1" />
                        </el-select> 
                    </el-form-item>                    
                </el-col>                        
            </el-row>
            <el-row justify="space-between">
                <el-col :span="8">
                    <el-form-item label="隔离政策" :label-width="formLabelWidth">
                        <el-select v-model="detailedForm.quarantinePolicy" placeholder="请选择" clearable>
                            <el-option label="暂无" value="暂无" />
                            <el-option label="居家隔离7日" value="居家隔离7日" />
                            <el-option label="集中隔离" value="集中隔离" />
                        </el-select> 
                    </el-form-item>       
                </el-col>       
                <el-col :span="8"></el-col>
                <el-col :span="8"></el-col>
            </el-row>
            <el-row justify="space-between">
                <el-col :span="24">
                    <el-form-item label="七日内行程" label-width="83px">
                        <el-input v-model="detailedForm.sevenDayTrip" maxlength="100" placeholder="请输入" show-word-limit style="width:686px" type="textarea"/>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="updatePersonalInf(detailedForm)">保存</el-button>
                <el-button @click="centerDialogVisible = false">取消</el-button>
            </span>
        </template>
    </el-dialog>

    <!-- 新增按键的Dialog页面 -->
    <el-dialog v-model="addDialogVisible" title="新增人员" width="54%" :append-to-body=true :draggable=true :modal-append-to-body=false :z-index="1000" align-center>
        <el-form :model="addNewDataValue">
            <el-row justify="space-between">
                <el-col :span="8">
                    <el-form-item label="人员姓名" :label-width="formLabelWidth">
                        <el-input v-model="addNewDataValue.personnelName" placeholder="请输入" autocomplete="off"/>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="人员年龄" :label-width="formLabelWidth">
                        <el-input v-model="addNewDataValue.personnelAge" placeholder="请输入" autocomplete="off"/>
                    </el-form-item>                    
                </el-col>
                <el-col :span="8">
                    <el-form-item label="联系电话" :label-width="formLabelWidth">
                        <el-input v-model="addNewDataValue.phone" placeholder="请输入" autocomplete="off"/>
                    </el-form-item>
                </el-col>             
            </el-row>
            <el-row justify="space-between">
                <el-col :span="8">
                    <el-form-item label="所属区域" :label-width="formLabelWidth">
                        <el-select v-model="addNewDataValue.areaCode" placeholder="请选择" clearable>
                            <el-option v-for="item in dataValue.areaSelectData" :label="item.areaName" :value="item.areaCode"/>
                        </el-select>
                    </el-form-item>                    
                </el-col> 
                <el-col :span="8">
                    <el-form-item label="所属楼组" :label-width="formLabelWidth">
                        <el-select v-model="addNewDataValue.buildCode" placeholder="请选择" clearable>
                            <el-option v-for="item in dataValue.buildSelectData" :label="item.buildName" :value="item.buildCode"/>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="居住类型" :label-width="formLabelWidth">
                        <el-select v-model="addNewDataValue.personnelState" placeholder="请选择" clearable>
                            <el-option label="租户" value="0" />
                            <el-option label="住户" value="1" />
                        </el-select>
                    </el-form-item>                    
                </el-col>           
            </el-row>
            <el-row justify="space-between">
                <el-col :span="8">
                    <el-form-item label="基础疾病" :label-width="formLabelWidth">
                        <el-select v-model="addNewDataValue.isUd" placeholder="请选择" clearable>
                            <el-option label="是" value="0" />
                            <el-option label="否" value="1" />
                        </el-select> 
                    </el-form-item>
                </el-col> 
                <el-col :span="8">
                    <el-form-item label="疫苗接种" :label-width="formLabelWidth">
                        <el-select v-model="addNewDataValue.inoculate" placeholder="请选择" clearable>
                            <el-option label="一针" value="一针" />
                            <el-option label="两针" value="两针" />
                            <el-option label="三针" value="三针" />
                            <el-option label="四针" value="四针" />
                            <el-option label="未接种" value="未接种" />
                        </el-select> 
                    </el-form-item>                    
                </el-col>
                <el-col :span="8">
                    <el-form-item label="是否隔离" :label-width="formLabelWidth">
                        <el-select v-model="addNewDataValue.isQuarantine" placeholder="请选择" clearable>
                            <el-option label="是" value="0" />
                            <el-option label="否" value="1" />
                        </el-select> 
                    </el-form-item>                    
                </el-col>                        
            </el-row>
            <el-row justify="space-between">
                <el-col :span="8">
                    <el-form-item label="核酸结果" :label-width="formLabelWidth">
                        <el-select v-model="addNewDataValue.natResult" placeholder="请选择" clearable>
                            <el-option label="阴性" value="0" />
                            <el-option label="阳性" value="1" />
                        </el-select> 
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="隔离政策" :label-width="formLabelWidth">
                        <el-select v-model="addNewDataValue.quarantinePolicy" placeholder="请选择" clearable>
                            <el-option label="暂无" value="暂无" />
                            <el-option label="居家隔离7日" value="居家隔离7日" />
                            <el-option label="集中隔离" value="集中隔离" />
                        </el-select> 
                    </el-form-item>
                </el-col>       
                <el-col :span="8"></el-col>                      
            </el-row>
            <el-row justify="space-between">
                <el-col :span="24">
                    <el-form-item label="七日内行程" label-width="83px">
                        <el-input v-model="addNewDataValue.sevenDayTrip" maxlength="100" placeholder="请输入" show-word-limit style="width:686px" type="textarea"/>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="addNewPersonal(addNewDataValue)">保存</el-button>
                <el-button @click="addDialogVisible = false">取消</el-button>
            </span>
        </template>
    </el-dialog>

</template>

<script lang="ts">
// vue3相关组件
import { reactive, ref, nextTick} from 'vue'
import {Delete,Postcard,Edit,FolderAdd,Download} from '@element-plus/icons-vue'
import { ElMessage,ElNotification} from 'element-plus'
import type { FormInstance } from 'element-plus'

// 自定义局部刷新组件
import { useRouter } from "vue-router";
// 引入查询人员信息接口
import {selectAllPersonnelDataForLMAPI} from '../../../http/api/personalAPI.js'
// 引入区域select下拉选择器数据获取接口
import {selectAreaForSelectAPI} from '../../../http/api/publicAPI.js'
// 引入楼组select下拉选择器数据获取接口
import {selectBuildForSelectAPI} from '../../../http/api/publicAPI.js'
// 引入根据ID删除指定的人员接口
import {deletePersonalByIdAPI} from '../../../http/api/personalAPI.js'
// 引入根据ID修改指定的人员接口
import {updatePersonalInfByIdAPI} from '../../../http/api/personalAPI.js'
// 引入新增人员接口
import {addNewPersonalAPI} from '../../../http/api/personalAPI.js'
// 引入文件导出接口
import {excelFileDownloadAPI} from '../../../http/api/fileDownLoadAPI.js'

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
        const dataValue = reactive({
            allData:[], //所有区域数据
            areaSelectData:[], //区域select下拉框数据
            buildSelectData:[], //建筑select下拉框数据
            getAreaSelect:[], //双向绑定获取当前区域下拉框的数据
        });
        const selectDataValue = reactive({//输入框数据
            id: '',
            personnelCode: '',
            personnelName: '',
            personnelAge: '',
            phone: '',
            areaName: '',
            buildName: '',
            personnelState: null,
            isUd: null,
            inoculate: null,
            natResult: null,
            isQuarantine: null,
            quarantinePolicy: '',
            sevenDayTrip: '',
            pageCurrent: 1
        });

        //区域select下拉框 selectAllPersonnelDataForLMAPI(selectDataValue).then(res =>
        const areaSelect = selectAreaForSelectAPI().then(res =>{
            dataValue.areaSelectData = res.data;
        })

        //建筑select下拉框（联动未实现-待优化）
        const buildSelect = selectBuildForSelectAPI(selectDataValue).then(res =>{
            dataValue.buildSelectData = res.data;
            console.log("下拉框"+JSON.stringify(dataValue.buildSelectData))
        })

        //数据查询
        const queryPersonalData = (formEl: FormInstance | undefined) => { //查询按钮
            if (!formEl) return
            formEl.validate((valid) => {
                if (valid) {
                    selectAllPersonnelDataForLMAPI(selectDataValue).then(res =>{ //动态查询数据并渲染到表格中
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
        const sellectAllPersonnelData = selectAllPersonnelDataForLMAPI(selectDataValue).then(res =>{ //数据显示
            //console.log("数据："+JSON.stringify(res))
            dataValue.allData = res.data;
            console.log("数据："+JSON.stringify(dataValue.allData));
        });
        const CurrentChange = (currentPage: any) =>{ //分页改变当前页数
            selectDataValue.pageCurrent = currentPage;
            console.log("页数："+selectDataValue.pageCurrent)
            selectAllPersonnelDataForLMAPI(selectDataValue).then(res =>{
                dataValue.allData = res.data;
            })
        }

        // 操作按键及Dialog页面
        //详情页面弹框（Dialog）
        const dialogFormVisible = ref(false);
        const formLabelWidth = '70px';
        const detailForm = reactive({
            id: '',
            personnelCode: '',
            personnelName: '',
            personnelAge: '',
            phone: '',
            areaName: '',
            buildName: '',
            personnelState: '',
            isUd: '',
            inoculate: '',
            natResult: '',
            isQuarantine: '',
            quarantinePolicy: '',
            sevenDayTrip: '',
        });
        const Details = (row: any) =>{//详情页数据展示
            dialogFormVisible.value = true;
            detailForm.id = row.id;
            detailForm.personnelCode = row.personnelCode;
            detailForm.personnelName = row.personnelName;
            detailForm.personnelAge = row.personnelAge;
            detailForm.phone = row.phone;
            detailForm.areaName = row.areaName;
            detailForm.buildName = row.buildName;
            detailForm.personnelState = row.personnelState;
            detailForm.isUd = row.isUd;
            detailForm.inoculate = row.inoculate;
            detailForm.natResult = row.natResult;
            detailForm.isQuarantine = row.isQuarantine;
            detailForm.quarantinePolicy = row.quarantinePolicy;
            detailForm.sevenDayTrip = row.sevenDayTrip;
        };

        //修改页面弹框（Dialog）
        const centerDialogVisible = ref(false);
        const detailedForm = reactive({
            id: '',
            personnelCode: '',
            personnelName: '',
            personnelAge: '',
            phone: '',
            areaCode: '',
            areaName: '',
            buildCode: '',
            buildName: '',
            personnelState: '',
            isUd: '',
            inoculate: '',
            natResult: '',
            isQuarantine: '',
            quarantinePolicy: '',
            sevenDayTrip: '',
        });
        const selectDisabled = ref(false); //用于禁用el-select
        const DialogVisible = (row: any) =>{//修改页数据展示
            console.log("修改数据："+JSON.stringify(row));
            centerDialogVisible.value = true;
            detailedForm.id = row.id;
            detailedForm.personnelCode = row.personnelCode;
            detailedForm.personnelName = row.personnelName;
            detailedForm.personnelAge = row.personnelAge;
            detailedForm.phone = row.phone;
            detailedForm.areaCode = row.areaCode;
            detailedForm.areaName = row.areaName;
            detailedForm.buildCode = row.buildCode;
            detailedForm.buildName = row.buildName;
            detailedForm.personnelState = row.personnelState;
            detailedForm.isUd = row.isUd;
            detailedForm.inoculate = row.inoculate;
            detailedForm.natResult = row.natResult;
            detailedForm.isQuarantine = row.isQuarantine;
            detailedForm.quarantinePolicy = row.quarantinePolicy;
            detailedForm.sevenDayTrip = row.sevenDayTrip;
        };

        const updatePersonalInf = (detailedForm: any) =>{// 修改页面的保存按键
            if(detailedForm.personnelName == '' || detailedForm.personnelName == null){
                ElMessage({
                    message: '人员姓名不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.personnelAge == '' || detailedForm.personnelAge == null){
                ElMessage({
                    message: '人员年龄不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.phone == '' || detailedForm.phone == null){
                ElMessage({
                    message: '联系电话不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.areaCode == '' || detailedForm.areaCode == null){
                ElMessage({
                    message: '所属区域不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.buildCode == '' || detailedForm.buildCode == null){
                ElMessage({
                    message: '所属楼组不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.personnelState == '' || detailedForm.personnelState == null){
                ElMessage({
                    message: '居住类型不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.isUd == '' || detailedForm.isUd == null){
                ElMessage({
                    message: '是否存在基础疾病不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.inoculate == '' || detailedForm.inoculate == null){
                ElMessage({
                    message: '疫苗接种数不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.isQuarantine == '' || detailedForm.isQuarantine == null){
                ElMessage({
                    message: '是否隔离不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.natResult == '' || detailedForm.natResult == null){
                ElMessage({
                    message: '核酸结果不能为空！',
                    type: 'warning',
                })
            }else if(detailedForm.sevenDayTrip == '' || detailedForm.sevenDayTrip == null){
                ElMessage({
                    message: '七日内行程不能为空！',
                    type: 'warning',
                })
            }else{
                updatePersonalInfByIdAPI(detailedForm).then(res =>{
                    console.log("修改数据："+JSON.stringify(detailedForm));
                    var state =  res.status; //接口状态
                    var result = res.data; //接口返回值
                    if(state == 200){
                        if(result == 1){
                            nextTick(() => { // 刷新页面（局部）
                                router.replace({
                                    path: '/redirect' + '/PersonalManageMent',
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
            deletePersonalByIdAPI(row.id).then(res =>{ //执行删除接口
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
                            path: '/redirect' + '/PersonalManageMent',
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

        const excelFileDownLoad = () =>{//Excel文件导出
            excelFileDownloadAPI().then(res =>{
                if(res.status == 200){
                    ElNotification({
                        title: '操作成功！',
                        message: '文件导出成功',
                        type: 'success',
                        position: 'bottom-right',
                        duration: 4000,
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
        }

        // 新增区域
        const addDialogVisible = ref(false);
        const addNewDataValue = reactive({//输入框数据
            personnelName: '',
            personnelAge: '',
            phone: '',
            areaCode: '',
            areaName: '',
            buildCode: '',
            buildName: '',
            personnelState: '',
            isUd: '',
            inoculate: '',
            natResult: '',
            isQuarantine: '',
            quarantinePolicy: '',
            sevenDayTrip: '',
        });
        const openAddPersonalData = () =>{//打开新增dialog页面
            addDialogVisible.value = true;
        }
        const addNewPersonal = (addNewDataValue: any) =>{//新增页面保存按键
            if(addNewDataValue.personnelName == '' || addNewDataValue.personnelName == null){
                ElMessage({
                    message: '人员姓名不能为空！',
                    type: 'warning',
                })
            }else if(addNewDataValue.personnelAge == '' || addNewDataValue.personnelAge == null){
                ElMessage({
                    message: '人员年龄不能为空！',
                    type: 'warning',
                })
            }else if(addNewDataValue.phone == '' || addNewDataValue.phone == null){
                ElMessage({
                    message: '联系电话不能为空！',
                    type: 'warning',
                })
            }else if(addNewDataValue.areaCode == '' || addNewDataValue.areaCode == null){
                ElMessage({
                    message: '所属区域不能为空！',
                    type: 'warning',
                })
            }else if(addNewDataValue.buildCode == '' || addNewDataValue.buildCode == null){
                ElMessage({
                    message: '所属楼组不能为空！',
                    type: 'warning',
                })
            }else if(addNewDataValue.personnelState == '' || addNewDataValue.personnelState == null){
                ElMessage({
                    message: '居住类型不能为空！',
                    type: 'warning',
                })
            }else if(addNewDataValue.isUd == '' || addNewDataValue.isUd == null){
                ElMessage({
                    message: '是否存在基础疾病不能为空！',
                    type: 'warning',
                })
            }else if(addNewDataValue.inoculate == '' || addNewDataValue.inoculate == null){
                ElMessage({
                    message: '疫苗接种数不能为空！',
                    type: 'warning',
                })
            }else if(addNewDataValue.isQuarantine == '' || addNewDataValue.isQuarantine == null){
                ElMessage({
                    message: '是否隔离不能为空！',
                    type: 'warning',
                })
            }else if(addNewDataValue.natResult == '' || addNewDataValue.natResult == null){
                ElMessage({
                    message: '核酸结果不能为空！',
                    type: 'warning',
                })
            }else if(addNewDataValue.sevenDayTrip == '' || addNewDataValue.sevenDayTrip == null){
                ElMessage({
                    message: '七日内行程不能为空！',
                    type: 'warning',
                })
            }else{
                //console.log("数据："+JSON.stringify(addAreaDataValue));
                addNewPersonalAPI(addNewDataValue).then(res =>{//调用保存接口
                    var state =  res.status; //接口状态
                    var result = res.data; //接口返回值

                    console.log("数据："+JSON.stringify(addNewDataValue));

                    if(state === 200){
                        if(result == 1){
                            addDialogVisible.value = false;
                            nextTick(() => { // 刷新页面（局部）
                                router.replace({
                                    path: '/redirect' + '/PersonalManageMent',
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

        return {
            router,
            resetFormRef,
            dataValue,
            selectDataValue,
            sellectAllPersonnelData,
            areaSelect,
            dialogFormVisible,
            formLabelWidth,
            detailForm,
            centerDialogVisible,
            selectDisabled,
            detailedForm,
            addDialogVisible,
            addNewDataValue,
            //buildSelect,
            queryPersonalData,
            indexMethod,
            CurrentChange,
            resetForm,
            cancelEvent,
            confirmEvent,
            Details,
            DialogVisible,
            updatePersonalInf,
            openAddPersonalData,
            addNewPersonal,
            excelFileDownLoad,
        }
    },

    components:{ //注册组件
        //element图标组件
        Delete,
        Postcard,
        Edit,
        FolderAdd,
        Download
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
    padding-right: 15px;
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
  width: 174px;
}
.el-input {
  width: 174px;
}
</style>