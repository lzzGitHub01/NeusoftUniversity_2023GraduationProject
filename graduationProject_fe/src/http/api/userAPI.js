import $axios from '../index.js'
//==========================================用户相关API接口==========================================

/**
 * 用户登陆接口
 * @returns 
 */
export const userLoginAPI =(data)=>{
    return $axios({
        url:'/systemManager/userLogin',
        method:'post',
        data
    })
}

/**
 * 获取当前账户的等级
 * @returns 
 */
export const getUserLevelAPI =(data)=>{
    return $axios({
        url:'/systemManager/selectUserLevel',
        method:'get',
        params:{
            manageName:data
        }
    })
}

/**
 * 获取当前账户的所属社区
 * @returns 
 */
export const selectUserCommunityAPI =(data)=>{
    return $axios({
        url:'/systemManager/selectUserCommunity',
        method:'get',
        params:{
            manageName:data
        }
    })
}

/**
 * 获取当前账户的所属区域
 * @returns 
 */
export const selectUserAreaAPI =(data)=>{
    return $axios({
        url:'/systemManager/selectUserArea',
        method:'get',
        params:{
            manageName:data
        }
    })
}

/**
 * 获取当前账户的所属楼组
 * @returns 
 */
export const selectUserBuildAPI =(data)=>{
    return $axios({
        url:'/systemManager/selectUserBuild',
        method:'get',
        params:{
            manageName:data
        }
    })
}

/**
 * 根据提供的条件查询所有对应的账户数据（支持模糊查询）--最高权限
 * @param {*} data 
 * @returns 
 */
export const selectAllUserAPI =(data)=>{
    return $axios({
        url:'/systemManager/selectAllUser',
        method:'post',
        data
    })
}

/**
 * 根据提供的条件查询所有对应的账户数据（支持模糊查询）--中级权限
 * @param {*} data 
 * @returns 
 */
export const selectAllUserForLMAPI =(data)=>{
    return $axios({
        url:'/systemManager/selectAllUserForLM',
        method:'post',
        data
    })
}

/**
 * 根据提供的条件查询所有对应的账户数据（支持模糊查询）
 * @param {*} data 
 * @returns 
 */
export const changeUserByIDAPI =(data)=>{
    return $axios({
        url:'/systemManager/changeInf',
        method:'post',
        data
    })
}

/**
 * 根据ID删除指定的用户
 * @returns 
 */
export const deleteUserByIDAPI =(data)=>{
    return $axios({
        url:'/systemManager/deleteUser',
        method:'get',
        params:{
            id:data
        }
    })
}

/**
 * 新增用户
 * @param {*} data 
 * @returns 
 */
export const createNewUserAPI =(data)=>{
    return $axios({
        url:'/systemManager/addNewUser',
        method:'post',
        data
    })
}

/**
 * 获取当前登陆账户的信息
 * @returns 
 */
export const selectCurrentAccountAPI =()=>{
    return $axios({
        url:'/systemManager/selectCurrentAccount',
        method:'get',
    })
}

/**
 * 当前登陆账户修改自己的基础数据
 * @param {*} data 
 * @returns 
 */
export const updateCurrentAccountAPI =(data)=>{
    return $axios({
        url:'/systemManager/updateCurrentAccount',
        method:'post',
        data
    })
}

/**
 * 修改当前账户密码
 * @param {*} data 
 * @returns 
 */
export const changePasswordAPI =(data)=>{
    return $axios({
        url:'/systemManager/changePassword',
        method:'post',
        data
    })
}

/**
 * 修改其他账户名称
 * @param {*} data 
 * @returns 
 */
export const updateOtherManagerNameAPI =(data)=>{
    return $axios({
        url:'/systemManager/updateOtherManagerName',
        method:'post',
        data
    })
}

/**
 * 修改其他账户密码
 * @param {*} data 
 * @returns 
 */
export const updateOtherManagerPasswordAPI =(data)=>{
    return $axios({
        url:'/systemManager/updateOtherManagerPassword',
        method:'post',
        data
    })
}

/**
 * 用户登出接口
 * @returns 
 */
export const userExitAPI =()=>{
    return $axios({
        url:'/systemManager/userExit',
        method:'get',
    })
}