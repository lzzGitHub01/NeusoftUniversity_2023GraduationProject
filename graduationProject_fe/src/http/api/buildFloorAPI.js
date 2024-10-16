import $axios from '../index.js'
//==========================================楼层规则相关API接口==========================================

/**
 * 查询所有楼层规则（最高权限）
 * @returns 
 */
export const selectAllBuildFloorDataAPI =(data)=>{
    return $axios({
        url:'/buildFloor/selectAllBuildFloorData',
        method:'post',
        data
    })
}

/**
 * 根据ID删除指定的规则
 * @returns 
 */
export const deleteBuildFloorByIdAPI =(data)=>{
    return $axios({
        url:'buildFloor/deleteBuildFloorById',
        method:'get',
        params:{
            id:data
        }
    })
}

/**
 * 根据ID删除楼层规则信息
 * @returns 
 */
export const updateBuildFloorInfByIdAPI =(data)=>{
    return $axios({
        url:'/buildFloor/updateBuildFloorInfById',
        method:'post',
        data
    })
}

/**
 * 新增规则
 * @param {*} data 
 * @returns 
 */
export const addNewBuildFloorAPI =(data)=>{
    return $axios({
        url:'/buildFloor/addNewBuildFloor',
        method:'post',
        data
    })
}
