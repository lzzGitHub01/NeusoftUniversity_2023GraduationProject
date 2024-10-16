<template>
    <div class="pageStyle">
        <img :src="homeImg" class="imgStyle"/>
    </div>
</template>

<script>
// 引入图片
import homeImg from'../../../assets/homePage.jpg'

export default {
    setup () {
        

        return {
            homeImg
        }
    }
}
</script>

<style lang="scss" scoped>
.pageStyle{
    padding: 0px;
    //margin-bottom: -4px;
}
.imgStyle{
    margin: 0px;
    padding: 0px;
    width: 100%;
    height: calc(100vh - 95px);
}
</style>