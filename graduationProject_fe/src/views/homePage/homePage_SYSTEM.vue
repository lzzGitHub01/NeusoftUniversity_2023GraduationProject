<template>
  <div class="common-layout">
    <el-container>

        <!-- 头部 -->
        <el-header class="HeaderRepair">
          <Header></Header>
        </el-header>
        
        <el-container>

          <!-- 侧边栏 -->
          <el-aside class="AsideRepair">
            <Aside></Aside>
          </el-aside>

          <el-container>
              <!-- 内容展示 -->
              <el-main class="MainRepair">
                <Main></Main>
              </el-main>
              <!-- 页脚 -->
              <el-footer class="FooterRepair">
                <Footer></Footer>
              </el-footer>
          </el-container>

        </el-container>
    </el-container>
  </div>
</template>


<script>
//引入子组件
import Header from'../../components/Header.vue'
import Aside from'../../components/Aside_SYS.vue'
import Main from'../../components/Main.vue'
import Footer from'../../components/Footer.vue'

    export default {
        name:'homePage',

        components:{ //注册组件
          Header,
          Aside,
          Main,
          Footer
        }

    }
</script>

<style lang="scss" scoped>
.HeaderRepair{
  padding: 0px;
  width: 100%;
}
.AsideRepair{
  width: 260px;
  padding: 0px;
}
.MainRepair{
  width: 100%;
  padding: 0px;
}
.FooterRepair{
  width: 100%;
  padding: 0px;
  height: auto;
}
.mainStyle{
    //background-color: #18222c;
    background-color: #e2e1e4;
    color: #000000;
    height: calc(100vh - 95px);
    margin: 0px;
}
</style>