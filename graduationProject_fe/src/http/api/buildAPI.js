import $axios from '../index.js'
//==========================================用户相关API接口==========================================

/**
 * 查询所有居民楼建筑信息--最高权限
 * @returns 
 */
export const selectAllBuildDataAPI =(data)=>{
    return $axios({
        url:'/build/selectAllBuildData',
        method:'post',
        data
    })
}

/**
 * 查询所有居民楼建筑信息--中级权限
 * @returns 
 */
export const selectAllBuildDataForLMAPI =(data)=>{
    return $axios({
        url:'/build/selectAllBuildDataForLM',
        method:'post',
        data
    })
}

/**
 * 查询所有居民楼建筑信息--普通权限
 * @returns 
 */
export const selectAllBuildDataForLNAPI =(data)=>{
    return $axios({
        url:'/build/selectAllBuildDataForLN',
        method:'post',
        data
    })
}

/**
 * 根据ID修改指定区域信息
 * @returns 
 */
export const updateBuildInfByIdAPI =(data)=>{
    return $axios({
        url:'/build/updateBuildInfById',
        method:'post',
        data
    })
}

/**
 * 根据ID删除指定的建筑
 * @returns 
 */
export const deleteBuildByIdAPI =(data)=>{
    return $axios({
        url:'/build/deleteBuildById',
        method:'get',
        params:{
            id:data
        }
    })
}

/**
 * 新增区域
 * @param {*} data 
 * @returns 
 */
export const addNewBuildAPI =(data)=>{
    return $axios({
        url:'/build/addNewBuild',
        method:'post',
        data
    })
}
