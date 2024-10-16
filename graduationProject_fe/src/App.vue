<template>
  <!-- 默认vue启动展示页面 -->
  <router-link to="/"/>
  <!-- 显示内容 -->
  <router-view></router-view>
  
</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  // text-align: center;
  color: #2c3e50;
}

html,body,#app {
  width: 100%;
  height:100%;
  margin: 0px;
  padding: 0px;
}
body{
  background-color: #e2e1e4;
}
</style>
