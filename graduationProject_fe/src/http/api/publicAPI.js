import $axios from '../index.js'
//==========================================公用API接口==========================================

/**
 * 区域下拉框
 * @returns 
 */
export const selectAreaForSelectAPI =()=>{
    return $axios({
        url:'/area/selectAreaForSelect',
        method:'get',
    })
}

/**
 * 楼组下拉框
 * @returns 
 */
export const selectBuildForSelectAPI =(data)=>{
    return $axios({
        url:'/build/selectBuildForSelect',
        method:'post',
        data
    })
}

/**
 * 楼组规则下拉框
 * @returns 
 */
export const selectBuildForFloorSelectAPI =()=>{
    return $axios({
        url:'/buildFloor/selectBuildForFloorSelect',
        method:'get',
    })
}