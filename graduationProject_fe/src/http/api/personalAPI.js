import $axios from '../index.js'
//==========================================用户相关API接口==========================================

/**
 * 查询所有人员建筑信息--高级权限
 * @returns 
 */
export const selectAllPersonnelDataAPI =(data)=>{
    return $axios({
        url:'/personnel/selectAllPersonnelData',
        method:'post',
        data
    })
}

/**
 * 查询所有人员建筑信息--中级权限
 * @returns 
 */
export const selectAllPersonnelDataForLMAPI =(data)=>{
    return $axios({
        url:'/personnel/selectAllPersonnelDataForLM',
        method:'post',
        data
    })
}

/**
 * 查询所有人员建筑信息--普通权限
 * @returns 
 */
export const selectAllPersonnelDataForLNAPI =(data)=>{
    return $axios({
        url:'/personnel/selectAllPersonnelDataForLN',
        method:'post',
        data
    })
}


/**
 * 根据ID删除指定的人员
 * @returns 
 */
export const deletePersonalByIdAPI =(data)=>{
    return $axios({
        url:'/personnel/deletePersonalById',
        method:'get',
        params:{
            id:data
        }
    })
}

/**
 * 根据ID修改指定人员信息
 * @returns 
 */
export const updatePersonalInfByIdAPI =(data)=>{
    return $axios({
        url:'/personnel/updatePersonalInfById',
        method:'post',
        data
    })
}

/**
 * 新增人员
 * @param {*} data 
 * @returns 
 */
export const addNewPersonalAPI =(data)=>{
    return $axios({
        url:'/personnel/addNewPersonal',
        method:'post',
        data
    })
}

/**
 * 人员信息（感染情况）统计--最高权限
 * @returns 
 */
export const infectionStatusAPI =()=>{
    return $axios({
        url:'/personnel/infectionStatus',
        method:'post',
    })
}

/**
 * 人员信息（感染情况）统计--中级权限
 * @returns 
 */
export const infectionStatusForLMAPI =()=>{
    return $axios({
        url:'/personnel/infectionStatusForLM',
        method:'post',
    })
}

/**
 * 人员信息（感染情况）统计--普通权限
 * @returns 
 */
export const infectionStatusForLNAPI =()=>{
    return $axios({
        url:'/personnel/infectionStatusForLN',
        method:'post',
    })
}