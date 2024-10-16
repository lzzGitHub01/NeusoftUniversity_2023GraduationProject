<template>
    <div class="pageStyle">
        <el-tabs :tab-position="tabPosition" type="border-card" class="demo-tabs">
            <el-tab-pane>
                <template #label>
                    <span class="custom-tabs-label">
                        <el-icon><User /></el-icon>
                        <span>当前账户信息</span>
                    </span>
                </template>
                <CurrentAccountInf></CurrentAccountInf>
            </el-tab-pane>
        </el-tabs>

        <!-- <el-radio-group v-model="tabPosition">
            <el-radio-button label="top" class="tapsBut">菜单置顶</el-radio-button>
            <el-radio-button label="bottom">菜单置底</el-radio-button>
            <el-radio-button label="left">菜单置左</el-radio-button>
        </el-radio-group> -->
    </div>
</template>

<script>
// vue3相关组件
import { reactive, ref, nextTick} from 'vue'
import {Delete,Postcard,Edit,FolderAdd,User,List,Document} from '@element-plus/icons-vue'
import { ElNotification} from 'element-plus'

//引入子组件
import CurrentAccountInf from '../../components/ELTabs_SYS/CurrentAccountInf.vue'

// 自定义局部刷新组件
import { useRouter } from "vue-router";


export default {
    setup () {
        const tabPosition = ref('top')

        return {
            tabPosition,
        }
    },
    
    components:{ //注册组件
        //页面组件
        CurrentAccountInf,
        //element图标组件
        Delete,
        Postcard,
        Edit,
        FolderAdd,
        User,
        List,
        Document
    }
}
</script>

<style lang="scss" scoped>
.pageStyle{
    padding: 0px;
}
.tapsBut{
    margin-top: 3px;
    margin-bottom: 3px;
    margin-left: 10px;
}
</style>

<style>
/* 标签页样式 */
.demo-tabs > .el-tabs__content {
  padding: 15px 32px 32px 32px;
  color: #6b778c;
  font-weight: 300;
  height: 100%;
  height: calc(100vh - 183px);

}
.demo-tabs .custom-tabs-label .el-icon {
  vertical-align: middle;
}
.demo-tabs .custom-tabs-label span {
  vertical-align: middle;
  margin-left: 4px;
  font-weight: 530;
}
</style>

<style>
.el-radio-group{
    width: 100%;
    height: 100%;
    background-color: #fff;
    
}
</style>