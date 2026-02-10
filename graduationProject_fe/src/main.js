import { createApp } from 'vue'
import App from './App.vue'

import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 导入 createI18n
import { createI18n } from 'vue-i18n'
// 导入i18n的翻译文件
import zhCN from './locales/zh-CN.json'
import en from './locales/en.json'

import axios from 'axios'
import VueAxios from 'vue-axios'

const i18n = createI18n({
  legacy: false, // 使用 Composition API 模式 (Vue 3 推荐)
  locale: 'zh-CN', // 默认显示中文
  fallbackLocale: 'en', // 找不到翻译时回退到英文
  messages: {
    'zh-CN': zhCN,
    'en': en
  }
})

createApp(App).use(store).use(router).use(VueAxios, axios).use(ElementPlus).use(i18n).mount('#app');

axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';