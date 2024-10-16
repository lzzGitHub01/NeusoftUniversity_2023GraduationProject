import $axios from '../index.js'
//==========================================社区相关API接口==========================================

/**
 * 查询所有社区（最高权限）
 * @returns 
 */
export const selectAllCommunityAPI =(data)=>{
    return $axios({
        url:'/community/selectAllCommunity',
        method:'post',
        data
    })
}

/**
 * 新增社区
 * @param {*} data 
 * @returns 
 */
export const addNewCommunityAPI =(data)=>{
    return $axios({
        url:'/community/addNewCommunity',
        method:'post',
        data
    })
}

/**
 * 修改社区
 * @param {*} data 
 * @returns 
 */
export const updateCommunityByIdAPI =(data)=>{
    return $axios({
        url:'/community/updateCommunityById',
        method:'post',
        data
    })
}

/**
 * 根据ID删除指定的社区
 * @returns 
 */
export const deleteCommunityByIdAPI =(data)=>{
    return $axios({
        url:'community/deleteCommunityById',
        method:'get',
        params:{
            id:data
        }
    })
}