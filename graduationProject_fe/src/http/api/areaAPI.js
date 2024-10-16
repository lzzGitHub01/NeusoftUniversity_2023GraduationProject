import $axios from '../index.js'
//==========================================区域相关API接口==========================================

/**
 * 查询所有区域(最高权限)
 * @returns 
 */
export const selectAllAreaDataAPI =(data)=>{
    return $axios({
        url:'/area/selectAllAreaData',
        method:'post',
        data
    })
}

/**
 * 分页查询所有区域(最高权限)--接口作废
 * @returns 
 */
export const selectAllAreaDataPageAPI =(data)=>{
    return $axios({
        url:'/area/selectAllAreaDataPage',
        method:'get',
        params:{
            pageCurrent:data
        }
    })
}

/**
 * 根据ID删除指定的区域
 * @returns 
 */
export const deleteAreaByIDAPI =(data)=>{
    return $axios({
        url:'/area/deleteAreaById',
        method:'get',
        params:{
            id:data
        }
    })
}

/**
 * 根据ID修改指定区域信息
 * @returns 
 */
export const changeAreaByIDAPI =(data)=>{
    return $axios({
        url:'/area/changeAreaInf',
        method:'post',
        data
    })
}

/**
 * 根据提供的条件查询所有对应的区域数据（支持模糊查询）--接口作废
 * @param {*} data 
 * @returns 
 */
export const selectAreaDataByConditionAPI =(data)=>{
    return $axios({
        url:'/area/selectAreaDataByCondition',
        method:'post',
        data
    })
}

/**
 * 新增区域
 * @param {*} data 
 * @returns 
 */
export const createNewAreaAPI =(data)=>{
    return $axios({
        url:'/area/createNewArea',
        method:'post',
        data
    })
}

/**
 * 社区感染情况数据展示
 * @returns 
 */
export const selectAreaInfectionSituationAPI =()=>{
    return $axios({
        url:'/area/selectAreaInfectionSituation',
        method:'get',
    })
}

