<template>
<!-- ============================主页侧边栏组件============================ -->
  <el-menu active-text-color="#ffd04b" background-color="#545c64" class="el-menu-vertical-demo" :default-active="$route.path" text-color="#fff" @open="handleOpen" @close="handleClose" router>
    <el-sub-menu index="1">
      <template #title>
        <el-icon><HelpFilled /></el-icon>
        <span>社区信息管理</span>
      </template>
        <el-menu-item index="/CommunityManageMent">社区明细</el-menu-item>
        <!-- <el-menu-item-group> </el-menu-item-group> -->
    </el-sub-menu>

    <el-sub-menu index="2">
      <template #title>
        <el-icon><Setting /></el-icon>
        <span>账户信息管理</span>
      </template>
        <el-menu-item index="/CurrentAccount_SYS">高级信息管理</el-menu-item>
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