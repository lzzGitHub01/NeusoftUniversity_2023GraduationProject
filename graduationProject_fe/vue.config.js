const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  //transpileDependencies: true,  
  configureWebpack: {    
    resolve: { extensions: [".ts", ".tsx", ".js", ".json"] },    
    module: {        
      rules: [    
        {    
          test: /\.tsx?$/,    
          loader: 'ts-loader',    
          exclude: /node_modules/,    
          options: {
            appendTsSuffixTo: [/\.vue$/],    
          }    
        }        
      ]    
    }    
  },

  // 配置
  devServer:{
    //配置前端自启浏览器
    open:true,
    host: 'localhost',
    port: 8086,

    //配置后端跨域访问接口
    proxy:{
      '/api':{
        target:'http://localhost:8087',
        ws: true, //支持websocket
        changeOrigin: true, //是否开启跨域
        pathRewrite:{
          '^/api':''
        },
      }
    }
  },

})


