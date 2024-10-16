<template>
<!-- ============================主页内容栏组件============================ -->
    <div class="mainStyle">
        <router-view></router-view>
    </div>
</template>

<script>
export default {
    setup () {
        

        return {}
    }
}
</script>

<style lang="scss" scoped>
.mainStyle{
    //background-color: #18222c;
    background-color: #e2e1e4;
    color: #000000;
    height: calc(100vh - 95px);
}
</style>