<template>
<!-- ============================主页侧边栏组件============================ -->
  <el-menu active-text-color="#ffd04b" background-color="#545c64" class="el-menu-vertical-demo" router :default-active="$route.path" text-color="#fff" @open="handleOpen" @close="handleClose">
    <el-sub-menu index="1">
      <template #title>
        <el-icon><HelpFilled /></el-icon>
        <span>社区疫情监测</span>
      </template>
        <el-menu-item index="/InfectionStatus_LM">监测数据</el-menu-item>
    </el-sub-menu>
    
    <el-sub-menu index="2">
      <template #title>
        <el-icon><Document /></el-icon>
        <span>区域楼组信息管理</span>
      </template>
        <el-menu-item index="/BuildManageMent_LM">楼组明细展示</el-menu-item>
        <!-- <el-menu-item index="/LM-test02">LM-test-02</el-menu-item>
        <el-menu-item index="/LM-test03">LM-test-03</el-menu-item> -->
        <!-- <el-menu-item-group> </el-menu-item-group> -->
    </el-sub-menu>

    <el-sub-menu index="3">
      <template #title>
        <el-icon><Location /></el-icon>
        <span>区域人员管理</span>
      </template>
        <el-menu-item index="PersonalManageMent_LM">人员信息明细</el-menu-item>
        <!-- <el-menu-item index="2-2">LM-item two</el-menu-item>
        <el-menu-item index="2-3">LM-item three</el-menu-item> -->
        <!-- <el-menu-item-group> </el-menu-item-group> -->
    </el-sub-menu>    

    <el-sub-menu index="4">
      <template #title>
        <el-icon><Setting /></el-icon>
        <span>账户管理</span>
      </template>
        <el-menu-item index="/AccountManagement_LM">账号明细管理</el-menu-item>
        <el-menu-item index="/CurrentAccount_LM">高级信息管理</el-menu-item>
        <!-- <el-menu-item-group> </el-menu-item-group> -->
    </el-sub-menu>    

  </el-menu>

</template>

<script lang="ts">
import { ref } from 'vue'
import {Document,Location,Setting,HelpFilled} from '@element-plus/icons-vue'

export default {
    setup () {
        const handleOpen = (key: string, keyPath: string[]) => {
            console.log(key, keyPath)
        }
        const handleClose = (key: string, keyPath: string[]) => {
            console.log(key, keyPath)
        }

        return {
            handleOpen,
            handleClose,  
        }
    },

    components:{ //注册组件
        //element图标组件
        Document,
        Location,
        Setting,
        HelpFilled
    }
}
</script>

<style lang="scss" scoped>
.asideStyle{
    background-color: #1d3043;
    margin: 0px;
    height: calc(100vh - 60px);
    color: #ffffff;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.el-menu{
  border: 0 !important;
  width: 260px !important;
  height: 100%;
}
</style>