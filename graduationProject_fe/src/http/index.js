import axios from 'axios'
import { ElNotification} from 'element-plus'

/**
 * axios.create：创建一个axios实例
 * 我们给这个实例编写配置，后续所有通过实例发送的请求，都会受到当前配置约束
 */
const $axios = axios.create({
    baseURL: '/api',
    timeout: 2000,
    //headers: {'X-Custom-Header': 'foobar'}
  });

// 添加请求拦截器
$axios.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    return config;
  }, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  });

// 添加响应拦截器
$axios.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    //获取data数据：let data = response.data;
    return response;
  }, function (error) {
    // 对响应错误做点什么
    ElNotification({ //后台报错拦截
      title: '系统错误',
      message: '请和后台管理员联系',
      type: 'error',
      position: 'bottom-right',
      duration: 4000,
    });
    return Promise.reject(error);
  });

// 输出实例
export default $axios